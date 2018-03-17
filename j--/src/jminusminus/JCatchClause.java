package jminusminus;

public class JCatchClause extends JAST {




	/**
	 * Construct an AST node the given its line number in the source file.
	 *
	 * @param line line in which the source for the AST was found.
	 */
	protected JCatchClause(int line) {
		super(line);
	}

	public JAST analyze(Context context) {
		return null;
	}

	public void codegen(CLEmitter output) {

	}

	public void writeToStdOut(PrettyPrinter p) {

	}
}
