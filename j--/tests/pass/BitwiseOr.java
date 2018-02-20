package pass;

import java.lang.System;

public class BitwiseOr {

	public static void main (String[] args) {
		System.out.println("283 | 0 = " + (283 | 0));
		System.out.println("3 | 4 = " + (3 | 4));
		System.out.println("28 | 10 = " + (28 | 10));
	}

	public int bitwiseOr (int x, int y) {
		return x | y;
	}
}
