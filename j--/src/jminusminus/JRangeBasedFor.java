package jminusminus;

public class JRangeBasedFor extends JForStatement {

	/* Type of the iterating variable */
	private Type type;

	/* Name of the iterating variable */
	private String name;

	/* For the range-based for loop, what is the object */
	private JExpression range;

	/**
	 * Construct an AST node for a statement given its line number.
	 *
	 * @param line         line in which the statement occurs in the source file.
	 * @param body
	 * @param isRangeBased
	 */
	protected JRangeBasedFor(int line, Type type, String name,
							 JExpression range, JStatement body, boolean isRangeBased) {
		super(line, body, isRangeBased);
		this.type = type;
		this.name = name;
		this.range = range;
	}


	@Override
	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JRangeBasedFor line=\"%d\">\n", line());
		p.indentRight();
		p.printf("<RangeVar type=\"%s\" name=\"%s\">\n", type.toString(), name);
		p.indentRight();
		p.println("<RangeObject>");
		range.writeToStdOut(p);
		p.println("</RangeObject");
		p.indentLeft();
		p.println("</RangeVar>");
		p.println("<Body>");
		p.indentRight();
		body.writeToStdOut(p);
		p.indentLeft();
		p.println("</Body>");
		p.indentLeft();
		p.printf("</JRangeBasedFor>\n", line());
	}
}
