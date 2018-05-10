package jminusminus;

import java.util.ArrayList;
import jminusminus.JMethodDeclaration;

public class JInterfaceDeclaration extends JAST implements JTypeDecl {

	ArrayList<String> mods;
	String name;
	ArrayList<TypeName> implType;
	ArrayList<TypeName> superInterfaces;
	ArrayList<String> superInterfacesJvm;
	/** This class type. */
	private Type thisType;
	Type superType;
	ArrayList<JMember> body;
	private ClassContext context;

	/** Instance fields of this class. */
	private ArrayList<JFieldDeclaration> instanceFieldInitializations;

	/** Static (class) fields of this class. */
	private ArrayList<JFieldDeclaration> staticFieldInitializations;

	/**
	 * Construct an AST node the given its line number in the source file.
	 *
	 * @param line line in which the source for the AST was found.
	 * @param mods the modifiers for the interface
	 * @param name The name of the interface
	 * @param implType the type of the superclass that might be extended by the interface
	 * @param body the interface body
	 */
	protected JInterfaceDeclaration(int line, ArrayList<String> mods, String name, ArrayList<TypeName> implType, ArrayList<JMember> body) {
		super(line);
		this.line = line;
		this.mods = mods;
		mods.add("interface");
		mods.add("abstract");
		this.name = name;
		this.superType = null;//the default supertype for JClass is Type.OBJECT, but for interface it's null.
		//If the interface extends more, they'll just have to grab the ArrayList if the list is desired.
		if (implType != null) {
			this.superInterfaces = new ArrayList<>();
			for (TypeName interfaceType : implType) {
				this.superInterfaces.add(interfaceType);
			}
		}
		this.implType = implType;
		this.body = body;
	}

	public JAST analyze(Context context) {
		// Analyze all members
		for (JMember member : body) {
			((JAST) member).analyze(this.context);
		}
		return this;
	}

	public void codegen(CLEmitter output) {
		// The class header
		String qualifiedName = JAST.compilationUnit.packageName() == "" ? name
				: JAST.compilationUnit.packageName() + "/" + name;

		output.addClass(mods, qualifiedName, Type.OBJECT.jvmName(), superInterfacesJvm, false);
		for (JMember member : body) {
			if (member instanceof JMethodDeclaration) {
				JMethodDeclaration mem = (JMethodDeclaration)member;
				output.addMethod(mem.mods, mem.name, mem.descriptor, mem.exJvmForm, false);
			}
		}
	}
	public void writeToStdOut(PrettyPrinter p) {
	    String s = "";
	    if (implType != null){
            for (int i = 0; i < implType.size() - 1; i++){
                s = s + implType.get(i).toString() + ", ";
            }
            if (implType.size() > 0){
                s = s + implType.get(superInterfaces.size()-1).toString();
            }
        }

        p.printf("<JInterfaceDeclaration line=\"%d\" name=\"%s\""
				+ " super=\"%s\">\n", line(), name, s);
		p.indentRight();
		if (context != null) {
			context.writeToStdOut(p);
		}
		if (mods != null) {
			p.println("<Modifiers>");
			p.indentRight();
			for (String mod : mods) {
				if (mod != "interface") {
					p.printf("<Modifier name=\"%s\"/>\n", mod);
				}
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
		String qualifiedName = JAST.compilationUnit.packageName() == "" ? name
				: JAST.compilationUnit.packageName() + "/" + name;
		CLEmitter partial = new CLEmitter(false);
		partial.addClass(mods, qualifiedName, Type.OBJECT.jvmName(), null,
				false);
		thisType = Type.typeFor(partial.toClass());
		context.addType(line, thisType);
	}

	public void preAnalyze(Context context) {
		// Construct a class context
		this.context = new ClassContext(this, context);

		// Create the (partial) class
		CLEmitter partial = new CLEmitter(false);

		// Add the class header to the partial class
		String qualifiedName = JAST.compilationUnit.packageName() == "" ? name
				: JAST.compilationUnit.packageName() + "/" + name;


		if (superInterfaces != null) {
			superInterfacesJvm = new ArrayList<>();
			for (TypeName tn : superInterfaces) {
				Type newType = tn.resolve(context);
				superInterfacesJvm.add(newType.jvmName());
			}
		}
		partial.addClass(mods, qualifiedName, Type.OBJECT.jvmName(), superInterfacesJvm, false);

		for (JMember member : body) {
			if(member instanceof JMethodDeclaration) {
				((JMethodDeclaration) member).makeInterfaceMethod();
				if (!((JMethodDeclaration) member).mods.contains("abstract")) {
					((JMethodDeclaration) member).mods.add("abstract");
				}
				if (!((JMethodDeclaration) member).mods.contains("public")) {
					((JMethodDeclaration) member).mods.add("public");
				}
			}
			member.preAnalyze(context, partial);
		}

		// Get the Class rep for the (partial) class and make it
		// the representation for this type
		Type id = this.context.lookupType(name);
		if (id != null && !JAST.compilationUnit.errorHasOccurred()) {
			id.setClassRep(partial.toClass());
		}
	}

	public String name() {
		return name;
	}

	public Type superType() {
		return superType;
	}


	public Type thisType() {
		return thisType;
	}
}
