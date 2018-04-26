package fail;
import java.lang.Boolean;
import java.lang.Exception;

public class ExceptionFail2 {

	private static int f(int x) throws Exception {
		if (x == 42) {
			throw new Exception(x + ": The answer to life, the universe and everything!");
		}
		return x;
	}

	public static void main(String[] args) {
		try {
			int x = Integer.parseInt(args[0]);
			int y = f(x);
			System.out.println(y);
		}
		catch (java.lang.Boolean e) {
			System.out.println(e.getMessage());
		}
		finally {
			System.out.println("Done!");
		}
	}
}
