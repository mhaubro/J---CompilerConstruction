package jminusminus;

public class JRangeBasedFor extends JForStatement {
	private J

	/* For the range-based for loop, what is the object */
	private JExpression range;

	/**
	 * Construct an AST node for a statement given its line number.
	 *
	 * @param line         line in which the statement occurs in the source file.
	 * @param body
	 * @param isRangeBased
	 */
	protected JRangeBasedFor(int line, JStatement body, boolean isRangeBased) {
		super(line, body, isRangeBased);
	}


	@Override
	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JRangeBasedFor line=\"%d\">\n", line());
		p.printf("</JRangeBasedFor>\n", line());
	}
}
