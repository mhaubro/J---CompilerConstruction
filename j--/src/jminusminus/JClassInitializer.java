package jminusminus;

import java.util.ArrayList;

import static jminusminus.CLConstants.RETURN;

/* This class is the AST node for class initializers
 *
 */
public class JClassInitializer extends JMethodDeclaration implements JMember {

	/* Modifiers */
	protected ArrayList<String> mods;

	/* Body of the initializer */
	protected JBlock body;

	/* Is the initializer static */
	protected boolean isStatic;

	/* Define the initializers context */
	protected MethodContext context;

	/**
	 * Construct an AST node the given its line number in the source file.
	 *
	 * @param line line in which the source for the AST was found.
	 * @param mods modifies for the AST
	 * @param body body of the initializer
	 */
	protected JClassInitializer(int line, ArrayList<String> mods, JBlock body) {
		super(line, mods, "", Type.VOID, null, null, body);
		this.mods = mods;
		this.isStatic = mods.contains("static");
		this.body = body;
	}

	/* The initializer does not have a return type, nor does it have
	 * a function name or parameters. Also does not have a descriptor,
	 * so we only check that the modifiers are valid. Must be here though because otherwise
	 * the JClassInitializer does not properly implement JMember
	 */
	public void preAnalyze(Context context, CLEmitter partial) {
		for (String mod : mods) {
			if (!mod.equals("static")) {
				JMethodDeclaration.compilationUnit.reportSemanticError(line(),
						String.format("Initializer may not be declared %s", mod));
			}
		}
	}

	public JMethodDeclaration analyze(Context context) {
		if (body != null) {
			body = body.analyze(context);
		}
		return this;
	}


	/**
	 *
	 * @param output
	 *            the code emitter (basically an abstraction for producing the code of the body
	 */
	public void codegen(CLEmitter output) {
		body.codegen(output);
	}

	/*
	 * Pretty printing support for static and non-static initializers
	 * We first print the line number and then the modifiers (only static is viable)
	 * We then print the body part. We then wrap up the print
	 */
	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JClassInitializer line=\"%d\">\n", line());
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
			p.println("<Body>");
			p.indentRight();
			body.writeToStdOut(p);
			p.indentLeft();
			p.println("</Body>");
		}
		p.indentLeft();
		p.println("</JClassInitializer>");
	}
}
