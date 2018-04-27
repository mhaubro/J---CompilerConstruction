package pass;

import java.lang.System;

public class OperatorsInt {

	public static void main (String[] args) {
		System.out.println(multAssign(3, 4));
	}
	// Integer methods
	public static int minusAssign(int x, int y) {
		x -= y;
		return x;
	}
	public static int multAssign(int x, int y) {
		x *= y;
		return x;
	}
	public static int divAssign(int x, int y) {
		x /= y;
		return x;
	}
	public static int remAssign(int x, int y) {
		x %= y;
		return x;
	}



}
