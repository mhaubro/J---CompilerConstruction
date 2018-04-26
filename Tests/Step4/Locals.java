public class Locals {
	public int foo(int t, String u) {
		int v = u.length();
		{
			int w = v + 5, x = w + 7;
			v = w + x;
		}
		{
			int y = 3;
			int z = v + y;
			t = t + y + z;
		}
		return t + v;
	}
}