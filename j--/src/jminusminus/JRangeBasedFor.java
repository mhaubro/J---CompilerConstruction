package jminusminus;


import static jminusminus.CLConstants.*;
import java.util.ArrayList;

public class JRangeBasedFor extends JForStatement {

	/* Type of the iterating variable */
	private JFormalParameter formalParam;

	/* For the range-based for loop, what is the object */
	private JExpression range;

	/* Implicit loop variable and length condition variable */
	private JVariableDeclaration varDecls;
	private JVariable loopVar;
	private JVariable condVar;
	private JVariable formalParamVar;

	/* Implicit update */
	private JStatement formalParamUpdate;
	private JStatement update;

	/* Implicit condition */
	private JExpression condition;

	private LocalContext context;

	/**
	 * Construct an AST node for a statement given its line number.
	 *
	 * @param line         line in which the statement occurs in the source file.
	 * @param body
	 * @param isRangeBased
	 */
	protected JRangeBasedFor(int line, ArrayList<String> mods, JFormalParameter formalParam,
							 JExpression range, JStatement body, boolean isRangeBased) {
		super(line, body, isRangeBased);
		this.mods = mods;
		this.formalParam = formalParam;
		this.range = range;

		this.loopVar = new JVariable(line, " loopVar");
		this.condVar = new JVariable(line, " condVar");
		this.formalParamVar = new JVariable(line, formalParam.name());

		ArrayList<JVariableDeclarator> decls = new ArrayList<>();
		JExpression lhsAssign = new JAssignOp(line, this.loopVar, new JLiteralInt(line, "0"));
		decls.add(new JVariableDeclarator(line," loopVar", Type.INT, lhsAssign));
		decls.add(new JVariableDeclarator(line," condVar", Type.INT, null));
		this.varDecls = new JVariableDeclaration(line, new ArrayList<String>(), decls);

		formalParamUpdate = new JAssignOp(line, formalParamVar, new JArrayExpression(line, range, loopVar));

		update = new JPostIncrementOp(line(), loopVar);
		((JPostIncrementOp) update).isStatementExpression = true;
		condition = new JLessEqualOp(line(), loopVar, condVar);

	}


	public JForStatement analyze(Context context) {
	    this.context = new LocalContext(context);

        formalParam.setType(formalParam.type().resolve(this.context));
        LocalVariableDefn defn = new LocalVariableDefn(formalParam.type(),
                this.context.nextOffset());
        defn.initialize();
		// For double support, doubles take 2 stack variables of space
		if (defn.type() == Type.DOUBLE) {
			this.context.incrementOffset();
		}
        this.context.addEntry(formalParam.line(), formalParam.name(), defn);

		this.varDecls.analyze(this.context);
		condVar = (JVariable) ((JLhs) condVar).analyzeLhs(this.context);
		loopVar = (JVariable) ((JLhs) loopVar).analyzeLhs(this.context);

		range = range.analyze(this.context);
		if (!Type.ITERABLE.isJavaAssignableFrom(range.type()) && !range.type().isArray()) {
			JAST.compilationUnit.reportSemanticError(line(), "Range object must be iterable");
		}

		formalParam.type().mustMatchExpected(line(), range.type().componentType());
		formalParamUpdate.analyze(this.context);
		condition = condition.analyze(this.context);
		condition.type().mustMatchExpected(line(), Type.BOOLEAN);
		update = (JStatement) update.analyze(this.context);

		body = (JStatement) body.analyze(this.context);
		return this;
	}

	public void codegen(CLEmitter output) {
		String condLabel = output.createLabel();
		String endLabel = output.createLabel();


		// Transform into a traditional for by first
		// fetching the array and calculating array length
		range.codegen(output);
		output.addNoArgInstruction(ARRAYLENGTH);


		// Store array length to our condition variable
		((JLhs) condVar).codegenStore(output);
		// Subtract 1 since we don't have less than or equal
		int offset = ((LocalVariableDefn) (condVar).iDefn())
				.offset();
		output.addIINCInstruction(offset, -1);


		// Initialize our implicit loop variable
		output.addNoArgInstruction(ICONST_0);
		((JLhs) loopVar).codegenStore(output);

		// Create label for condition code
		output.addLabel(condLabel);

		// Generate condition code
		condition.codegen(output, endLabel, false);

		// Set the formal parameter to proper index of array
		/*
		 * We can do this with the following instructions
		 * ALOAD (This is the looped array)
		 * ILOAD (This is the index)
		 * IALOAD (Put the value of A[i] on the stack (for ints)
		 * STORE (Get the value and put it in the formalParamVar)
		 */
		// Using iastore; requires arrayref ---> index
		range.codegen(output);
		loopVar.codegenLoadLhsRvalue(output);
		Type formalParamType = formalParamVar.iDefn().type();
		if (formalParamType.isReference()) {
			output.addNoArgInstruction(AALOAD);
		}
		else if (formalParamType == Type.DOUBLE) {
			output.addNoArgInstruction(DALOAD);
		}
		else {
			output.addNoArgInstruction(IALOAD);
		}
		formalParamVar.codegenStore(output);


		// Generate loop body
		body.codegen(output);

		// Update our implicit loop variable
		update.codegen(output);

		output.addBranchInstruction(GOTO, condLabel);
		output.addLabel(endLabel);

	}

	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JRangeBasedFor line=\"%d\">\n", line());
		p.indentRight();
		p.println("<RangeVar>");
		p.indentRight();
		formalParam.writeToStdOut(p);
		p.indentLeft();
		p.println("</RangeVar>");
		p.println("<RangeObject>");
		range.writeToStdOut(p);
		p.println("</RangeObject>");
		p.indentLeft();
		p.println("</RangeVar>");
		p.println("<Body>");
		p.indentRight();
		body.writeToStdOut(p);
		p.indentLeft();
		p.println("</Body>");
		p.indentLeft();
		p.printf("</JRangeBasedFor>\n", line());
	}
}
