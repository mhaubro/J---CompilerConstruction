package jminusminus;

public class JThrowStatement extends JStatement {

	private JExpression exception;

	/**
	 * Construct an AST node for a statement given its line number.
	 *
	 * @param line line in which the statement occurs in the source file.
	 */
	protected JThrowStatement(int line, JExpression ex) {
		super(line);
		this.exception = ex;
	}

	public JStatement analyze(Context context) {
		return null;
	}

	public void codegen(CLEmitter output) {

	}

	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JThrowStatement line=\"%d\">\n", line());
		p.indentRight();
		exception.writeToStdOut(p);
		p.indentLeft();
		p.printf("</JThrowStatement>\n");
	}
}
