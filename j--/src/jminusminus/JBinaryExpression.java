// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.*;


/**
 * The AST node for a binary expression. A binary expression has an operator and
 * two operands: a lhs and a rhs.
 */

abstract class JBinaryExpression extends JExpression {

    /** The binary operator. */
    protected String operator;

    /** The lhs operand. */
    protected JExpression lhs;

    /** The rhs operand. */
    protected JExpression rhs;

    /**
     * Construct an AST node for a binary expression given its line number, the
     * binary operator, and lhs and rhs operands.
     * 
     * @param line
     *            line in which the binary expression occurs in the source file.
     * @param operator
     *            the binary operator.
     * @param lhs
     *            the lhs operand.
     * @param rhs
     *            the rhs operand.
     */

    protected JBinaryExpression(int line, String operator, JExpression lhs,
            JExpression rhs) {
        super(line);
        this.operator = operator;
        this.lhs = lhs;
        this.rhs = rhs;
    }


    /*
        Helper for analyzing functions with two of the same type
     */
    public JExpression analyze(Context context, Type typeArg) {
        lhs.type().mustMatchExpected(line(), typeArg);
        rhs.type().mustMatchExpected(line(), typeArg);
        type = typeArg;
        return this;
    }

    public JExpression analyze(Context context, Type typeArg, Type typeArg2) {
        lhs = (JExpression) lhs.analyze(context);
        rhs = (JExpression) rhs.analyze(context);
        lhs.type().mustMatchOneOf(line, typeArg, typeArg2);
        if (lhs.type() == typeArg) {
            return analyze (context, typeArg);
        }
        else if (lhs.type() == typeArg2) {
            return analyze(context, typeArg2);
        }
        type = Type.NULLTYPE;
        return this;
    }
    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JBinaryExpression line=\"%d\" type=\"%s\" "
                + "operator=\"%s\">\n", line(), ((type == null) ? "" : type
                .toString()), Util.escapeSpecialXMLChars(operator));
        p.indentRight();
        p.printf("<Lhs>\n");
        p.indentRight();
        lhs.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Lhs>\n");
        p.printf("<Rhs>\n");
        p.indentRight();
        rhs.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Rhs>\n");
        p.indentLeft();
        p.printf("</JBinaryExpression>\n");
    }

}

class JBitwiseOrOp extends JBinaryExpression {
    public JBitwiseOrOp(int line, JExpression lhs, JExpression rhs) {
        super (line, "|", lhs, rhs);
    }

    public void codegen(CLEmitter output) {
        lhs.codegen(output);
        rhs.codegen(output);
        output.addNoArgInstruction(IOR);
    }

    public JExpression analyze(Context context) {
        return analyze(context, Type.INT, Type.DOUBLE);
    }
}

class JBitwiseXorOp extends JBinaryExpression {
    public JBitwiseXorOp(int line, JExpression lhs, JExpression rhs) {
        super (line, "^", lhs, rhs);
    }

    public void codegen(CLEmitter output) {
        lhs.codegen(output);
        rhs.codegen(output);
        output.addNoArgInstruction(IXOR);
    }

    public JExpression analyze(Context context) {
        return analyze(context, Type.INT, Type.DOUBLE);
    }
}

class JBitwiseAndOp extends JBinaryExpression {
    public JBitwiseAndOp(int line, JExpression lhs, JExpression rhs) {
        super (line, "&", lhs, rhs);
    }

    public void codegen(CLEmitter output) {
        lhs.codegen(output);
        rhs.codegen(output);
        output.addNoArgInstruction(IAND);
    }

    public JExpression analyze(Context context) {
        return analyze(context, Type.INT, Type.DOUBLE);
    }
}


class JShiftLeftOp extends JBinaryExpression {
    public JShiftLeftOp(int line, JExpression lhs, JExpression rhs) {
        super (line, "<<", lhs, rhs);
    }

    public void codegen(CLEmitter output) {
        lhs.codegen(output);
        rhs.codegen(output);
        output.addNoArgInstruction(ISHL);
    }

    public JExpression analyze(Context context) {
        return analyze(context, Type.INT, Type.DOUBLE);
    }
}

class JShiftRightOp extends JBinaryExpression {
    public JShiftRightOp(int line, JExpression lhs, JExpression rhs) {
        super (line, ">>", lhs, rhs);
    }

    public void codegen(CLEmitter output) {
        lhs.codegen(output);
        rhs.codegen(output);
        output.addNoArgInstruction(ISHR);
    }

    public JExpression analyze(Context context) {
        return analyze(context, Type.INT, Type.DOUBLE);
    }
}

class JLogicShiftRightOp extends JBinaryExpression {
    public JLogicShiftRightOp(int line, JExpression lhs, JExpression rhs) {
        super (line, ">>>", lhs, rhs);
    }

    public void codegen(CLEmitter output) {
        lhs.codegen(output);
        rhs.codegen(output);
        output.addNoArgInstruction(IUSHR);
    }

    public JExpression analyze(Context context) {
        return analyze(context, Type.INT, Type.DOUBLE);
    }
}

class JRemainderOp extends JBinaryExpression {
    public JRemainderOp(int line, JExpression lhs, JExpression rhs) {
        super(line, "%", lhs, rhs);
    }
    public void codegen(CLEmitter output) {
        lhs.codegen(output);
        rhs.codegen(output);
        output.addNoArgInstruction(IREM);
    }


    public JExpression analyze(Context context) {
        return analyze(context, Type.INT, Type.DOUBLE);
    }
}

class JDivideOp extends JBinaryExpression {
	public JDivideOp(int line, JExpression lhs, JExpression rhs) {
		super(line, "/", lhs, rhs);
	}
	public void codegen(CLEmitter output) {
		lhs.codegen(output);
		rhs.codegen(output);
		output.addNoArgInstruction(IDIV);
	}


	public JExpression analyze(Context context) {
        return analyze(context, Type.INT, Type.DOUBLE);
    }
}

/**
 * The AST node for a plus (+) expression. In j--, as in Java, + is overloaded
 * to denote addition for numbers and concatenation for Strings.
 */

class JPlusOp extends JBinaryExpression {

    /**
     * Construct an AST node for an addition expression given its line number,
     * and the lhs and rhs operands.
     * 
     * @param line
     *            line in which the addition expression occurs in the source
     *            file.
     * @param lhs
     *            the lhs operand.
     * @param rhs
     *            the rhs operand.
     */

    public JPlusOp(int line, JExpression lhs, JExpression rhs) {
        super(line, "+", lhs, rhs);
    }

    /**
     * Analysis involves first analyzing the operands. If this is a string
     * concatenation, we rewrite the subtree to make that explicit (and analyze
     * that). Otherwise we check the types of the addition operands and compute
     * the result type.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JExpression analyze(Context context) {
        lhs = (JExpression) lhs.analyze(context);
        rhs = (JExpression) rhs.analyze(context);
        if (lhs.type() == Type.STRING || rhs.type() == Type.STRING) {
            return (new JStringConcatenationOp(line, lhs, rhs))
                    .analyze(context);
        } else if (lhs.type() == Type.INT && rhs.type() == Type.INT) {
            type = Type.INT;
        } else if (lhs.type() == Type.DOUBLE && rhs.type() == Type.DOUBLE) {
            type = Type.DOUBLE;
        } else {
            type = Type.ANY;
            JAST.compilationUnit.reportSemanticError(line(),
                    "Invalid operand types for +");
        }
        return this;
    }
    /**
     * Any string concatenation has been rewritten as a JStringConcatenationOp
     * (in analyze()), so code generation here involves simply generating code
     * for loading the operands onto the stack and then generating the
     * appropriate add instruction.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
        if (type == Type.INT) {
            lhs.codegen(output);
            rhs.codegen(output);
            output.addNoArgInstruction(IADD);
        }
    }

}

/**
 * The AST node for a subtraction (-) expression.
 */

class JSubtractOp extends JBinaryExpression {

    /**
     * Construct an AST node for a subtraction expression given its line number,
     * and lhs and rhs operands.
     * 
     * @param line
     *            line in which the subtraction expression occurs in the source
     *            file.
     * @param lhs
     *            the lhs operand.
     * @param rhs
     *            the rhs operand.
     */

    public JSubtractOp(int line, JExpression lhs, JExpression rhs) {
        super(line, "-", lhs, rhs);
    }

    /**
     * Analyzing the - operation involves analyzing its operands, checking
     * types, and determining the result type.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JExpression analyze(Context context) {
        return analyze(context, Type.INT, Type.DOUBLE);
    }

    /**
     * Generating code for the - operation involves generating code for the two
     * operands, and then the subtraction instruction.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
        lhs.codegen(output);
        rhs.codegen(output);
        output.addNoArgInstruction(ISUB);
    }

}

/**
 * The AST node for a multiplication (*) expression.
 */

class JMultiplyOp extends JBinaryExpression {

    /**
     * Construct an AST for a multiplication expression given its line number,
     * and the lhs and rhs operands.
     * 
     * @param line
     *            line in which the multiplication expression occurs in the
     *            source file.
     * @param lhs
     *            the lhs operand.
     * @param rhs
     *            the rhs operand.
     */

    public JMultiplyOp(int line, JExpression lhs, JExpression rhs) {
        super(line, "*", lhs, rhs);
    }

    /**
     * Analyzing the * operation involves analyzing its operands, checking
     * types, and determining the result type.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JExpression analyze(Context context) {
        return analyze(context, Type.INT, Type.DOUBLE);
    }

    /**
     * Generating code for the * operation involves generating code for the two
     * operands, and then the multiplication instruction.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
        lhs.codegen(output);
        rhs.codegen(output);
        output.addNoArgInstruction(IMUL);
    }

}
