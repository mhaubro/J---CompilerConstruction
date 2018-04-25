package fail;

import java.lang.System;

public class IncorrectForStatements {
	public static void main () {
		for (int i = 0, j = 0; i < 10; i++) {
			System.out.println(i);
		}
		int x,y;

		for (x = 0, y = 0; x <= 10; x + 3) {
			System.out.println(x);
		}

	}
}
