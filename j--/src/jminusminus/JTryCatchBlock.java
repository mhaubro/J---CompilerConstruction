package jminusminus;

import java.util.ArrayList;
import jminusminus.CLConstants.*;

public class JTryCatchBlock extends JStatement {
	/* The try block */
	private JBlock tryBlock;

	/* An array of one or more catch clauses */
	private ArrayList<JCatchClause> catches;

	/* The code inside the finally block */
	private JBlock finalBlock;
	private int finallyOffset;

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

	public JTryCatchBlock analyze(Context context) {
		tryBlock.analyze(context);
		ArrayList<Type> caughtTypes = new ArrayList<>();
		for (JCatchClause catch_clause : catches) {
			catch_clause.analyze(context);
			if (caughtTypes.contains(catch_clause.exception_param.type())) {
				JAST.compilationUnit.reportSemanticError(line(),
						"Caught exceptions may not be subclasses of each other");

			}
			caughtTypes.add(catch_clause.exception_param.type());
		}

		finalBlock.analyze(context);
		finallyOffset = ((LocalContext)context).nextOffset();
		return this;
	}

	public void codegen(CLEmitter output) {
		// Create start label, end label of the try block
		String startTryLabel = output.createLabel();
		String endTryLabel = output.createLabel();
		String finallyLabel = output.createLabel();
		String endOfTryCatchLabel = output.createLabel();

		output.addLabel(startTryLabel);
		tryBlock.codegen(output);
		output.addLabel(endTryLabel);
		finalBlock.codegen(output);
		output.addBranchInstruction(CLConstants.GOTO, endOfTryCatchLabel);
		for (JCatchClause cc : catches) {
			// Create labels
			String startCatchLabel = output.createLabel();
			String endCatchLabel = output.createLabel();
			output.addExceptionHandler(startTryLabel, endTryLabel, startCatchLabel, cc.exception_param.type().jvmName());
			output.addLabel(startCatchLabel);
			cc.codegen(output);
			output.addLabel(endCatchLabel);
			output.addExceptionHandler(startCatchLabel, endCatchLabel, finallyLabel, null);
			finalBlock.codegen(output);
			output.addBranchInstruction(CLConstants.GOTO, endOfTryCatchLabel);
		}
		output.addExceptionHandler(startTryLabel, endTryLabel, finallyLabel, null);

		output.addLabel(finallyLabel);
		/* If we get to this final block.. it means we had an exception that wasn't caught elsewhere*/
		/* Therefore we must store the exception to a local variable. Load it to stack. Then throw */
		output.addOneArgInstruction(CLConstants.ASTORE, finallyOffset);
		finalBlock.codegen(output);
		output.addOneArgInstruction(CLConstants.ALOAD, finallyOffset);
		output.addNoArgInstruction(CLConstants.ATHROW);
		output.addLabel(endOfTryCatchLabel);
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
