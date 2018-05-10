// Copyright 2011 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import java.util.ArrayList;
import static jminusminus.CLConstants.*;

/**
 * The AST node for a method declaration.
 */

class JMethodDeclaration
    extends JAST implements JMember {

    /** Method modifiers. */
    protected ArrayList<String> mods;

    /** Method name. */
    protected String name;

    /** Return type. */
    protected Type returnType;

    /** The formal parameters. */
    protected ArrayList<JFormalParameter> params;

    /** Exceptions thrown by this method
     * This is an array of TypeName, which is j-- for
     * qualifiedIdentifier
     */
    protected ArrayList<Type> exceptions;
    protected ArrayList<String> exJvmForm;

    /** Method body. */
    protected JBlock body;

    /** Built in analyze(). */
    protected MethodContext context;

    /** Computed by preAnalyze(). */
    protected String descriptor;

    /** Is method abstract. */
    protected boolean isAbstract;

    /** Is method static. */
    protected boolean isStatic;

    /** Is method private. */
    protected boolean isPrivate;

    /** Is declared in a interface */
    protected boolean isFromInterface;

    /**
     * Construct an AST node for a method declaration given the
     * line number, method name, return type, formal parameters,
     * and the method body.
     * 
     * @param line
     *                line in which the method declaration occurs
     *                in the source file.
     * @param mods
     *                modifiers.
     * @param name
     *                method name.
     * @param returnType
     *                return type.
     * @param params
     *                the formal parameters.
     * @param body
     *                method body.
     */

    public JMethodDeclaration(int line, ArrayList<String> mods,
        String name, Type returnType,
        ArrayList<JFormalParameter> params, ArrayList<Type> exceptions, JBlock body)

    {
        super(line);
        this.mods = mods;
        this.name = name;
        this.returnType = returnType;
        this.params = params;
        this.exceptions = exceptions;
        this.exJvmForm = null;
        this.body = body;
        this.isAbstract = mods.contains("abstract");
        this.isStatic = mods.contains("static");
        this.isPrivate = mods.contains("private");
        isFromInterface = false;
    }

    /**
     * Declare this method in the parent (class) context.
     * 
     * @param context
     *                the parent (class) context.
     * @param partial
     *                the code emitter (basically an abstraction
     *                for producing the partial class).
     */

    public void preAnalyze(Context context, CLEmitter partial) {
        // Resolve types of the formal parameters
        for (JFormalParameter param : params) {
            param.setType(param.type().resolve(context));
        }

        for (int i = 0; i < exceptions.size(); i++) {
            Type newType = exceptions.get(i).resolve(context);
            exceptions.set(i, newType);
        }

        // Resolve return type
        returnType = returnType.resolve(context);

        // Check proper local use of abstract
        if (isAbstract && body != null) {
            JAST.compilationUnit.reportSemanticError(line(),
                "abstract method cannot have a body");
        } else if (body == null && !(isAbstract || isFromInterface)) {
            JAST.compilationUnit.reportSemanticError(line(),
                "Method with null body must be abstract");
        } else if (isAbstract && isPrivate) {
            JAST.compilationUnit.reportSemanticError(line(),
                "private method cannot be declared abstract");
        } else if (isAbstract && isStatic) {
            JAST.compilationUnit.reportSemanticError(line(),
                "static method cannot be declared abstract");
        } else if(isFromInterface && isPrivate) {
            JAST.compilationUnit.reportSemanticError(line(), 
                "interface method cannot be declared private");
        }

        // Compute descriptor
        descriptor = "(";
        for (JFormalParameter param : params) {
            descriptor += param.type().toDescriptor();
        }
        descriptor += ")" + returnType.toDescriptor();

        // Generate the method with an empty body (for now)
        partialCodegen(context, partial);
    }

    public void makeInterfaceMethod() {
        isFromInterface = true;
    }

    /**
     * Analysis for a method declaration involves (1) creating a
     * new method context (that records the return type; this is
     * used in the analysis of the method body), (2) bumping up
     * the offset (for instance methods), (3) declaring the
     * formal parameters in the method context, and (4) analyzing
     * the method's body.
     * 
     * @param context
     *                context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JAST analyze(Context context) {
        MethodContext methodContext = 
	    new MethodContext(context, isStatic, returnType);
		this.context = methodContext;

		for (Type ex : exceptions) {
            ((MethodContext)this.context).exceptions.add(ex);
        }

        exJvmForm = null;
        if (exceptions.size() > 0) {
            exJvmForm = new ArrayList<>();
            for (Type ex : exceptions) {
                exJvmForm.add(ex.jvmName());
            }
        }


		if (!isStatic) {
			// Offset 0 is used to address "this".
			this.context.nextOffset();
		}

		// Declare the parameters. We consider a formal parameter
		// to be always initialized, via a function call.
		for (JFormalParameter param : params) {
			LocalVariableDefn defn = new LocalVariableDefn(param.type(),
				this.context.nextOffset());
			defn.initialize();
			this.context.addEntry(param.line(), param.name(), defn);
            // For double support, doubles take 2 stack variables of space
            if (defn.type() == Type.DOUBLE) {
                this.context.incrementOffset();
            }
		}
		if (body != null) {
			body = body.analyze(this.context);
			if (returnType!=Type.VOID && ! methodContext.methodHasReturn()){
			JAST.compilationUnit.reportSemanticError(line(),
				"Non-void method must have a return statement");
			}
		}
		return this;
    }

    /**
     * Add this method declaration to the partial class.
     * 
     * @param context
     *                the parent (class) context.
     * @param partial
     *                the code emitter (basically an abstraction
     *                for producing the partial class).
     */

    public void partialCodegen(Context context, CLEmitter partial) {
        // Generate a method with an empty body; need a return to
        // make
        // the class verifier happy.
        if(isFromInterface) {
            mods.add("public");
            mods.add("abstract");
        }

        partial.addMethod(mods, name, descriptor, exJvmForm, false);
        // Add implicit RETURN
        if (returnType == Type.VOID) {
            partial.addNoArgInstruction(RETURN);
        } else if (returnType == Type.INT
            || returnType == Type.BOOLEAN || returnType == Type.CHAR) {
            partial.addNoArgInstruction(ICONST_0);
            partial.addNoArgInstruction(IRETURN);
        }
        else if (returnType == Type.DOUBLE) {
            partial.addNoArgInstruction(DCONST_0);
            partial.addNoArgInstruction(DRETURN);
        }
        else {
            // A reference type.
            partial.addNoArgInstruction(ACONST_NULL);
            partial.addNoArgInstruction(ARETURN);
        }
    }

    /**
     * Generate code for the method declaration.
     * 
     * @param output
     *                the code emitter (basically an abstraction
     *                for producing the .class file).
     */

    public void codegen(CLEmitter output) {
        output.addMethod(mods, name, descriptor, exJvmForm, false);
        if (body != null) {
            body.codegen(output);
        }

        // Add implicit RETURN
        if (returnType == Type.VOID) {
            output.addNoArgInstruction(RETURN);
        }
    }

    public void codegenInterfaceMethod(CLEmitter output, String target) {
        output.addMemberAccessInstruction(INVOKEINTERFACE, target, name, descriptor);
        if (body != null) {
            body.codegen(output);
        }

        // Add implicit RETURN
        if (returnType == Type.VOID) {
            output.addNoArgInstruction(RETURN);
        }
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JMethodDeclaration line=\"%d\" name=\"%s\" "
            + "returnType=\"%s\">\n", line(), name, returnType
            .toString());
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
        if (params != null) {
            p.println("<FormalParameters>");
            for (JFormalParameter param : params) {
                p.indentRight();
                param.writeToStdOut(p);
                p.indentLeft();
            }
            p.println("</FormalParameters>");
        }
        if (exceptions != null) {
            p.println("<Exceptions>");
            p.indentRight();
            for (Type ex : exceptions) {
                p.printf("<Exception name=\"%s\"/>\n", ex.toString());
            }
            p.indentLeft();
            p.println("</Exceptions>");
        }
        if (body != null) {
            p.println("<Body>");
            p.indentRight();
            body.writeToStdOut(p);
            p.indentLeft();
            p.println("</Body>");
        }
        p.indentLeft();
        p.println("</JMethodDeclaration>");
    }
}
