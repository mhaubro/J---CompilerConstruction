package jminusminus;

import java.util.ArrayList;

public class JInterfaceDeclaration extends JAST implements JTypeDecl {

	ArrayList<String> mods;
	String name;
	Type superType;
	ArrayList<JMember> body;
	private ClassContext context;
	/** Static (class) fields of this class. */
	private ArrayList<JFieldDeclaration> staticFieldInitializations;

	/**
	 * Construct an AST node the given its line number in the source file.
	 *
	 * @param line line in which the source for the AST was found.
	 * @param mods the modifiers for the interface
	 * @param name The name of the interface
	 * @param superType the type of the superclass that might be extended by the interface
	 * @param body the interface body
	 */
	protected JInterfaceDeclaration(int line, ArrayList<String> mods, String name, Type superType, ArrayList<JMember> body) {
		super(line);
		this.line = line;
		this.mods = mods;
		this.name = name;
		this.superType = superType;
		this.body = body;
		staticFieldInitializations = new ArrayList<JFieldDeclaration>();
	}

	public JAST analyze(Context context) {
		return null;
	}

	public void codegen(CLEmitter output) {

	}

	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JInterfaceDeclaration line=\"%d\" name=\"%s\""
				+ " super=\"%s\">\n", line(), name, superType.toString());
		p.indentRight();
		if (context != null) {
			context.writeToStdOut(p);
		}
		if (mods != null) {
			p.println("<Modifiers>");
			p.indentRight();
			for (String mod : mods) {
				p.printf("<Modifier name=\"%s\"/>\n", mod);
			}
			p.indentLeft();
			p.println("</Modifiers>");
		}
		if (body != null) {
			p.println("<InterfaceBlock>");
			for (JMember member : body) {
				((JAST) member).writeToStdOut(p);
			}
			p.println("</InterfaceBlock>");
		}
		p.indentLeft();
		p.println("</JInterfaceDeclaration>");
	}

	public void declareThisType(Context context) {

	}

	public void preAnalyze(Context context) {

	}

	public String name() {
		return name;
	}

	public Type superType() {
		return superType;
	}


	public Type thisType() {
		return null;
	}
}
