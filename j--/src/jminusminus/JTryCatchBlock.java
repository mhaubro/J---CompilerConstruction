package jminusminus;

import java.util.ArrayList;

public class JTryCatchBlock extends JStatement {
	/* The try block */
	private JBlock tryBlock;

	/* An array of one or more catch clauses */
	private ArrayList<JCatchClause> catches;

	/* The code inside the finally block */
	private JBlock finalBlock;

	/**
	 * Construct an AST node for a statement given its line number.
	 *
	 * @param line line in which the statement occurs in the source file.
	 * @param tryBlock the block inside the try block
	 * @param catches the catches following the try block
	 * @param finalBlock the block that follows the finally if it exists
	 */
	protected JTryCatchBlock(int line, JBlock tryBlock, ArrayList<JCatchClause> catches, JBlock finalBlock) {
		super(line);
		this.tryBlock = tryBlock;
		this.catches = catches;
		this.finalBlock = finalBlock;
	}

	public JAST analyze(Context context) {
		return null;
	}

	public void codegen(CLEmitter output) {

	}

	public void writeToStdOut(PrettyPrinter p) {

	}
}
