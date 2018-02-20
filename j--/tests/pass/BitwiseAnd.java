package pass;

import java.lang.System;

public class BitwiseAnd {

	public static void main (String[] args) {
		System.out.println("283 & 0 = " + (283 & 0));
		System.out.println("3 & 13 = " + (3 & 13));
		System.out.println("29 & 10 = " + (29 & 10));
	}

	public int bitwiseAnd (int x, int y) {
		return x & y;
	}
}
