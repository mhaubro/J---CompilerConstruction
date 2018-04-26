package pass;

public class OperatorsInt {

	public static void main (String[] args) {
		System.out.println(20/7);
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
