package pass;

public class OperatorsDouble {

	public static void main (String[] args) {
		System.out.println(20.5/7.2);
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
}
