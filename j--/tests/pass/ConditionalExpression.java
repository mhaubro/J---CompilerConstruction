package pass;

import java.lang.System;

public class ConditionalExpression {
	public static void main (String[] arg) {
		boolean trueBool = true;
		System.out.println(trueBool ? 42 : 32);
	}

	public static double dblConditionalExpr(boolean cond, double x1, double x2) {
		return cond ? x1 : x2;
	}

	public static int intConditionalExpr(boolean cond, int x1, int x2) {
		return cond ? x1 : x2;
	}

	public static String stringConditionalExpr(boolean cond, String x1, String x2) {
		return cond ? x1 : x2;
	}

}
