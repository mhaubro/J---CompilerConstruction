package jminusminus;

import java.util.ArrayList;

public class JCatchClause extends JAST {

	private ArrayList<String> mods;

	/* The type of exception being caught */
	private JFormalParameter exception_param;

	/* Block portion of the catch */
	private JBlock catchBlock;


	/** Built in analyze(). Exceptions have their own context */
	protected LocalContext context;

	/*
	 * Construct an AST node the given its line number in the source file.
	 *
	 * @param line line in which the source for the AST was found.
	 * @param catchType specifies the type of exception being caught
	 * @param catchName the name of the exception
	 * @param catchBlock the code in the block of the exception
	 */
	protected JCatchClause(int line, ArrayList<String> mods, JFormalParameter exception_param, JBlock catchBlock) {
		super(line);
		this.mods = mods;
		this.exception_param = exception_param;
		this.catchBlock = catchBlock;
	}

	public JAST analyze(Context context) {
		LocalContext localContext =
				new LocalContext(context);
		this.context = localContext;

		for (String mod : mods) {
			if (!mod.equals("final")) {
				JAST.compilationUnit.reportSemanticError(line,
						"Modifier %s may not be used to modify a catchtype", mod);
			}
		}

		LocalVariableDefn defn = new LocalVariableDefn(exception_param.type(), this.context.nextOffset());
		defn.initialize();
		this.context.addEntry(exception_param.line(), exception_param.name(), defn);
		/*if (!Type.THROWABLE.isJavaAssignableFrom(exception_param.type())) {
			JAST.compilationUnit.reportSemanticError(line,
					"Type %s does not extend type %s, which is required for" +
							" objects in a throw statement", exception_param.type(), Type.THROWABLE);

		}*/

		catchBlock.analyze(context);

		return this;
	}

	public void codegen(CLEmitter output) {

	}

	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JCatchClause line=\"%d\">\n",
				line());
		if (mods != null) {
			p.println("<Modifiers>");
			p.indentRight();
			for (String mod : mods) {
				p.printf("<Modifier name=\"%s\"/>\n", mod);
			}
			p.indentLeft();
			p.println("</Modifiers>");
		}
		p.indentRight();
		exception_param.writeToStdOut(p);
		p.indentLeft();
		p.indentRight();
		this.catchBlock.writeToStdOut(p);
		p.indentLeft();
		p.printf("</JCatchClause>\n");
	}
}
