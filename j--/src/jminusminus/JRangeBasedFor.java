package jminusminus;

import java.util.ArrayList;

public class JRangeBasedFor extends JForStatement {

	/* Type of the iterating variable */
	private JFormalParameter formalParam;

	/* For the range-based for loop, what is the object */
	private JExpression range;

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
	}


	public JForStatement analyze(Context context) {
	    this.context = new LocalContext(context);

        formalParam.setType(formalParam.type().resolve(context));
        LocalVariableDefn defn = new LocalVariableDefn(formalParam.type(),
                this.context.nextOffset());
        defn.initialize();
		// For double support, doubles take 2 stack variables of space
		if (defn.type() == Type.DOUBLE) {
			this.context.incrementOffset();
		}
        this.context.addEntry(formalParam.line(), formalParam.name(), defn);

		range = range.analyze(this.context);

		formalParam.type().mustMatchExpected(line(), range.type().componentType());

		body = (JStatement) body.analyze(this.context);
		return this;
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
