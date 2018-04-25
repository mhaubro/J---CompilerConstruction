package jminusminus;

import java.util.ArrayList;

public class JTraditionalFor extends JForStatement {
	/* Initial set of StatementExpressions in the For loop */
	private JVariableDeclaration varInit;

	private ArrayList<JStatement> init;

	/* Update set of StatementExpressions in the For loop */
	private ArrayList<JStatement> update;

	/* Condition of the for statement */
	private JExpression condition;

	/**
	 * Construct an AST node for a statement given its line number.
	 *
	 * @param line         line in which the statement occurs in the source file.
	 * @param body
	 * @param isRangeBased
	 */
	protected JTraditionalFor(int line, ArrayList<String> mods, JVariableDeclaration varInit, ArrayList<JStatement> init, JExpression condition,
							  ArrayList<JStatement> update, JStatement body, boolean isRangeBased) {
		super(line, body, isRangeBased);
		this.mods = mods;
		this.varInit = varInit;
		this.init = init;
		this.update = update;
		this.condition = condition;
	}


	public JForStatement analyze(Context context) {
		if (varInit == null) {
			for (int i = 0; i < init.size(); i++) {
				init.set(i, (JStatement) init.get(i).analyze(context));
			}
		}
		else {
			varInit = (JVariableDeclaration) varInit.analyze(context);
		}

		condition = condition.analyze(context);
		condition.type().mustMatchExpected(line(), Type.BOOLEAN);

		for (int i = 0; i < update.size(); i++) {
			update.set(i, (JStatement) update.get(i).analyze(context));
		}

		body = (JStatement) body.analyze(context);
		return this;
	}

	public void codegen(CLEmitter output) {

	}

	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JTraditionalFor line=\"%d\">\n", line());
		p.indentRight();
		p.println("<Init>");
		p.indentRight();
		if (mods != null) {
			p.println("<VariableModifiers>");
			p.indentRight();
			for (String mod : mods) {
				p.printf("<VariableModifier name=\"%s\"/>\n", mod);
			}
			p.indentLeft();
			p.println("</VariableModifiers>");
		}

		if (init != null) {
			for (JStatement i : init) {
				i.writeToStdOut(p);
			}
		}
		else if (varInit != null) {
			varInit.writeToStdOut(p);
		}
		p.indentLeft();
		p.println("</Init>");
		p.println("<Condition>");
		if (condition != null) {
			p.indentRight();
			condition.writeToStdOut(p);
			p.indentLeft();
		}
		p.println("</Condition>");
		p.println("<Update>");
		if (update != null) {
			p.indentRight();
			for (JStatement i : update) {
				i.writeToStdOut(p);
			}
			p.indentLeft();
		}
		p.println("</Update>");
		p.println("<Body>");
		p.indentRight();
		body.writeToStdOut(p);
		p.indentLeft();
		p.println("</Body>");
		p.indentLeft();
		p.printf("</JTraditionalFor>\n", line());
	}
}
