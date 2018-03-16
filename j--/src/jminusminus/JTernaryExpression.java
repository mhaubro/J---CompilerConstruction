package jminusminus;

public class JTernaryExpression extends JExpression {


	/* The left-hand side of the expression */
	protected JExpression lhs;

	/* The middle of the expression */
	protected JExpression middle;

	/* The right hand side of the expression */
	protected JExpression rhs;

	/**
	 * Construct an AST node for an expression given its line number.
	 *
	 * @param line line in which the expression occurs in the source file.
	 * @param lhs left hand side (left recursion)
	 * @param middle the middle part of the expression
	 * @param rhs right hand side
	 */
	protected JTernaryExpression(int line, JExpression lhs, JExpression middle, JExpression rhs) {
		super(line);
		this.lhs = lhs;
		this.middle = middle;
		this.rhs = rhs;
	}

	/**
	 *
	 * @param context
	 *            context in which names are resolved.
	 * @return the expression in which the contexts are analyzed and type checking
	 * 			of the left hand side is performed
	 */
	public JExpression analyze(Context context) {
		lhs = (JExpression) lhs.analyze(context);
		middle = (JExpression) middle.analyze(context);
		rhs = (JExpression) rhs.analyze(context);

		/* Check that the middle and rhs are the same type */
		middle.type().mustMatchExpected(line, rhs.type());
		this.type = middle.type();

		/* The left hand side must be a boolean */
		lhs.type().mustMatchExpected(line, Type.BOOLEAN);
		return this;
	}

	/**
	 * Generates the code for a ternary expression
	 * TODO: Write the code to implement the code generation of a ternary expression
	 */
	public void codegen(CLEmitter output) {

	}

	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JTernaryExpression line=\"%d\" type=\"%s\"/>\n",
				line(), ((type == null) ? "" : type.toString()));
		p.indentRight();
		p.printf("<ConditionalExpression>\n");
		p.indentRight();
		lhs.writeToStdOut(p);
		p.indentLeft();
		p.printf("</ConditionalExpression>\n");
		p.printf("<TrueResult>\n");
		p.indentRight();
		middle.writeToStdOut(p);
		p.indentLeft();
		p.printf("</TrueResult>\n");
		p.printf("<FalseResult>\n");
		p.indentRight();
		rhs.writeToStdOut(p);
		p.indentLeft();
		p.printf("</FalseResult>\n");
		p.indentLeft();
		p.printf("</JBinaryExpression>\n");
	}
}
