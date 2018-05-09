package pass;

import java.lang.System;

public class OperatorsDouble {

//	+=, -=, *=, /= %= %, /, +, -, *, (unary: +, -, -- ++, post and pre inc/dde

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
	public static double rem(double x, double y){
		return x % y;
	}
	public static double div(double x, double y){
		return x/y;
	}
	public static double plus(double x, double y){
		return x+y;
	}
	public static double minus(double x, double y){
		return x-y;
	}
	public static double mul(double x, double y){
		return x*y;
	}
	public static double postdec(double x){
		x--;
		return x--;
	}
	public static double predec(double x){
		--x;
		return --x;
	}
	public static double postinc(double x){
		x++;
		return x++;
	}
	public static double preinc(double x){
		++x;
		return ++x;
	}
}
