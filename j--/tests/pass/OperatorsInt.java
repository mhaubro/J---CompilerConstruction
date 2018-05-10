package pass;

import java.lang.System;

public class OperatorsInt {

	public static void main (String[] args) {
		System.out.println(plusAssign(3, 4));
		System.out.println(minusAssign(3, 4));
	}


	// Integer methods

	public static int plusAssign(int x, int y) {
		x += y;
		return x;
	}
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
	public static int bitleft(int x, int y) {
		return x << y;
	}
	public static int bitright(int x, int y) {
		return x >> y;
	}
	public static int land(int x, int y) {
		return x & y;
	}
	public static int lor(int x, int y) {
		return x | y;
	}
	public static int xor(int x, int y) {
		return x^y;
	}
/*	public static int bitleftassign(int x, int y) {
		x <<= y;
		return x;
	}
	public static int bitrightassign(int x, int y) {
		x >>= y;
		return x;
	}
	public static int landassign(int x, int y) {
		x &= y;
		return x;
	}
	public static int lorassign(int x, int y) {
		x |= y;
		return x;
	}
	public static int xorassign(int x, int y) {
		x ^= y;
		return x;
	}
	public static int arithrs(int x, int y) {
		return x >>> y;
	}
	public static int arithrsassign(int x, int y) {
		x >>>= y;
		return x;
	}*/
	public static int unaryplus(int x, int y) {
		y = +y;
		return x+y;
	}
	public static int unaryminus(int x, int y) {
		y = -y;
		return x + y;
	}
	public static int postdec(int x){
		x--;
		return x--;
	}
	public static int predec(int x){
		--x;
		return --x;
	}
	public static int postinc(int x){
		x++;
		return x++;
	}
	public static int preinc(int x){
		x++;
		return ++x;
	}

}
