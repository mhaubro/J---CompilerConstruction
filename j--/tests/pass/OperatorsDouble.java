package pass;

import java.lang.System;

public class OperatorsDouble {

	public static void main (String[] args) {
		System.out.println(20.5/7.2);
	}

	public static double plusAssign(double x, double y) {
		x += y;
		return x;
	}
	public static double minusAssign(double x, double y) {
		x -= y;
		return x;
	}
	public static double multAssign(double x, double y) {
		x *= y;
		return x;
	}
	public static double divAssign(double x, double y) {
		x /= y;
		return x;
	}

	public static double remAssign(double x, double y) {
		x %= y;
		return x;
	}
}
