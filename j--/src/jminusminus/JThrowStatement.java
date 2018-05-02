package jminusminus;

import static jminusminus.CLConstants.*;


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
		exception.analyze(context);

		if (!Type.THROWABLE.isJavaAssignableFrom(exception.type())) {
			JAST.compilationUnit.reportSemanticError(line,
					"Type %s does not extend type %s, which is required for" +
							" objects in a throw statement", exception.type(), Type.THROWABLE);
		}
		return this;
	}

	public void codegen(CLEmitter output) {
		exception.codegen(output);
		output.addNoArgInstruction(ATHROW);
	}

	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JThrowStatement line=\"%d\">\n", line());
		p.indentRight();
		exception.writeToStdOut(p);
		p.indentLeft();
		p.printf("</JThrowStatement>\n");
	}
}
