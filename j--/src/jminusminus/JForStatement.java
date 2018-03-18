package jminusminus;

import java.util.ArrayList;

public class JForStatement extends JStatement {

	/* Body of the for loop */
	protected JStatement body;

	/* Is the for loop range-based or traditional */
	protected boolean isRangeBased;

	/**
	 * Construct an AST node for a statement given its line number.
	 *
	 * @param line line in which the statement occurs in the source file.
	 */
	protected JForStatement(int line, JStatement body, boolean isRangeBased) {
		super(line);
		this.body = body;
		this.isRangeBased = isRangeBased;
	}

	public JForStatement analyze(Context context) {
		return null;
	}

	public void codegen(CLEmitter output) {

	}

	public void writeToStdOut(PrettyPrinter p) {

	}
}
