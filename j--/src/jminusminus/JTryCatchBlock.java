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
		tryBlock.analyze(context);
		for (JCatchClause catch_clause : catches) {
			catch_clause.analyze(context);
		}
		finalBlock.analyze(context);
		return this;
	}

	public void codegen(CLEmitter output) {
		// Create start label, end label of the try block
		String startLabel = output.createLabel();
		String endLabel = output.createLabel();

		output.addLabel(startLabel);
		tryBlock.codegen(output);
		output.addLabel(endLabel);
		for (JCatchClause cc : catches) {
			// Create a handler label
			String handlerLabel = output.createLabel();
			output.addLabel(handlerLabel);
			String exceptionType = cc.exception_param.type().jvmName();
			output.addExceptionHandler(startLabel, endLabel, handlerLabel, exceptionType);
			cc.codegen(output);
		}

		finalBlock.codegen(output);
	}

	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JTryCatchBlock line=\"%d\">\n", line());
		p.indentRight();
		p.println("<Try>");
		p.indentRight();
		tryBlock.writeToStdOut(p);
		p.indentLeft();
		p.println("</Try>");
		p.println("<Catches>");
		p.indentRight();
		for (JCatchClause cc : catches) {
			cc.writeToStdOut(p);
		}
		p.indentLeft();
		p.println("</Catches>");
		if (finalBlock != null) {
			p.println("<Finally>");
			p.indentRight();
			finalBlock.writeToStdOut(p);
			p.indentLeft();
			p.println("</Finally>");
		}
		p.indentLeft();
		p.printf("</JTryCatchBlock>\n");
	}
}
