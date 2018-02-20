package pass;

import java.lang.System;

public class BitwiseXor {

	public static void main (String[] args) {
		System.out.println("283 ^ 283 = " + (283 ^ 283));
		System.out.println("7 ^ 4 = " + (7 ^ 4));
		System.out.println("321 ^ 123 = " + (321 ^ 123));
	}

	public int bitwiseXor (int x, int y) {
		return x ^ y;
	}
}
