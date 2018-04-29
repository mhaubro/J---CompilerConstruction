package jminusminus;

import java.util.ArrayList;

import static jminusminus.CLConstants.*;

public class JTraditionalFor extends JForStatement {
	/* Initial set of StatementExpressions in the For loop */
	private JVariableDeclaration varInit;

	private ArrayList<JStatement> init;

	/* Update set of StatementExpressions in the For loop */
	private ArrayList<JStatement> update;

	/* Condition of the for statement */
	private JExpression condition;

	/* Local context for the For loop */
	private LocalContext context;

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
		this.context =  new LocalContext(context);

		if (varInit == null) {
			for (int i = 0; i < init.size(); i++) {
				init.set(i, (JStatement) init.get(i).analyze(this.context));
			}
		}
		else {
			varInit = (JVariableDeclaration) varInit.analyze(this.context);
		}

		condition = condition.analyze(this.context);
		condition.type().mustMatchExpected(line(), Type.BOOLEAN);

		for (int i = 0; i < update.size(); i++) {
			update.set(i, (JStatement) update.get(i).analyze(this.context));
		}

		body = (JStatement) body.analyze(this.context);
		return this;
	}

	public void codegen(CLEmitter output) {
		String condLabel = output.createLabel();
		String end = output.createLabel();
		// Initialize the codegen
		if (varInit != null) {
			varInit.codegen(output);
		}
		else {
			for (JStatement statement : init) {
				statement.codegen(output);
			}
		}

		// Set the condition and add a label to branch back up to if needed
		output.addLabel(condLabel);
		condition.codegen(output, end, false);

		body.codegen(output);

		// Run the update portion
		for (JStatement statement : update) {
			statement.codegen(output);
		}
		output.addBranchInstruction(GOTO, condLabel);
		// Add the unconditional branch back up to the conditional
		output.addLabel(end);
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
