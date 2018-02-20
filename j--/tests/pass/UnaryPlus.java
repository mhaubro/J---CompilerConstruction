package pass;

import java.lang.System;

public class UnaryPlus {

	public static void main(String[] args) {
		System.out.println("+3 = " + (+3));
		System.out.println("+8 = " + (+8));
		System.out.println("+12345 = " + (+12345));
	}

	public int unaryPlus(int x) {
		return +x;
	}
}
