package pass;

import java.lang.System;

public class Complement {

	public static void main(String[] args) {
		System.out.println("The complement of 0:" + ~0);
		System.out.println("The complement of 5:" + ~5);
	}

	public int complement(int x) {
		return ~x;
	}
}
