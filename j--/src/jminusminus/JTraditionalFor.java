package jminusminus;

import java.util.ArrayList;

public class JTraditionalFor extends JForStatement {
	/* Initial set of StatementExpressions in the For loop */
	private JVariableDeclaration init;

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
	protected JTraditionalFor(int line, JVariableDeclaration init, JExpression condition,
							  ArrayList<JStatement> update, JStatement body, boolean isRangeBased) {
		super(line, body, isRangeBased);
		this.init = init;
		this.update = update;
		this.condition = condition;
	}


	@Override
	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JTraditionalFor line=\"%d\">\n", line());
		p.indentRight();
		p.println("<Init>");
		p.indentRight();
		init.writeToStdOut(p);
		p.indentLeft();
		p.println("</Init>");
		p.println("<Condition>");
		p.indentRight();
		condition.writeToStdOut(p);
		p.indentLeft();
		p.println("</Condition>");
		p.println("<Update>");
		p.indentRight();
		for (JStatement i : update) {
			i.writeToStdOut(p);
		}
		p.indentLeft();
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
