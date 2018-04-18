package jminusminus;

import java.util.ArrayList;

public class JCatchClause extends JAST {

	private ArrayList<String> mods;

	/* The type of exception being caught */
	private TypeName catchType;

	/* Name of the exception being caught */
	private String catchName;

	/* Block portion of the catch */
	private JBlock catchBlock;

	/*
	 * Construct an AST node the given its line number in the source file.
	 *
	 * @param line line in which the source for the AST was found.
	 * @param catchType specifies the type of exception being caught
	 * @param catchName the name of the exception
	 * @param catchBlock the code in the block of the exception
	 */
	protected JCatchClause(int line, ArrayList<String> mods, TypeName catchType, String catchName, JBlock catchBlock) {
		super(line);
		this.mods = mods;
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
		this.catchBlock.writeToStdOut(p);
		p.indentLeft();
		p.printf("</JCatchClause>\n");
	}
}
