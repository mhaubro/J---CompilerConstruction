package pass;

import java.lang.System;

public class OperatorsDouble {

	public static void main(String[] args) {
		System.out.println(20.5 / 7.2);
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

	public static double rem(double x, double y) {
		return x % y;
	}

	public static double div(double x, double y) {
		return x / y;
	}

	public static double plus(double x, double y) {
		return x + y;
	}

	public static double minus(double x, double y) {
		return x - y;
	}

	public static double mul(double x, double y) {
		return x * y;
	}

	public static double postdec(double x) {
		x--;
		return x--;
	}

	public static double predec(double x) {
		--x;
		return --x;
	}

	public static double postinc(double x) {
		x++;
		return x++;
	}

	public static double preinc(double x) {
		++x;
		return ++x;
	}

	public static double cmp(double x, double y) {
		if (y > x) {
			return .01d;
		} else {
			return 2.0;
		}
	}

	public static double[] initClassicfordouble() {
		double[] darr = new double[8];
		for (double d = 0.1; 7.5 > d; d++) {
			darr[(int) d] = 3.0;
		}
		return darr;
	}

	public static double[] initClassicfor() {
		double[] darr = new double[8];
		for (int i = 0; darr.length > i; i++) {
			darr[i] = 3.0;
		}
		return darr;
	}

	public static double[] initClassiclist() {
		double[] darr = {3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0};
		return darr;
	}

	public static int cast(double x) {
		return (int) x;
	}

}