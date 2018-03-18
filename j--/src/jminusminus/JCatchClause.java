package jminusminus;

public class JCatchClause extends JAST {

	/* The type of exception being caught */
	private TypeName catchType;

	/* Name of the exception being caught */
	private String catchName;

	/* Block portion of the catch */
	private JBlock catchBlock;

	/**
	 * Construct an AST node the given its line number in the source file.
	 *
	 * @param line line in which the source for the AST was found.
	 * @param catchType specifies the type of exception being caught
	 * @param catchName the name of the exception
	 * @param catchBlock the code in the block of the exception
	 */
	protected JCatchClause(int line, TypeName catchType, String catchName, JBlock catchBlock) {
		super(line);
		this.catchType = catchType;
		this.catchName = catchName;
		this.catchBlock = catchBlock;
	}

	public JAST analyze(Context context) {
		return null;
	}

	public void codegen(CLEmitter output) {

	}

	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JCatchClause line=\"%d\" type=\"%s\" name=\"%s\">\n",
				line(), this.catchType.toString(), this.catchName);
		p.indentRight();
		this.catchBlock.writeToStdOut(p);
		p.indentLeft();
		p.printf("</JCatchClause>\n");
	}
}
