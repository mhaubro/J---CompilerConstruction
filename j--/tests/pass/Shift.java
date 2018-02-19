package pass;

import java.lang.System;

public class Shift {
	public static void main (String[] args) {
		System.out.println("31 shifted right 2: " + (33 >> 2));
		System.out.println("-30 shifted right 1: " + (-30 >> 1));
		System.out.println("-30 logic shifted right 1: " + (-30 >>> 1));
		System.out.println("47 shifted left 4: " + (47 << 4));
	}

	public int shiftLeft (int x, int y) {
		return x << y;
	}

	public int shiftRight (int x, int y) {
		return x >> y;
	}

	public int logicShiftRight (int x, int y) {
		return x >>> y;
	}

}
