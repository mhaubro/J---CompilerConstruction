package fail;

import java.lang.System;

public class IncorrectEnhancedFor {
	public static void main () {
		int x = 10;

		for (int y : x) {
			System.out.println(x);
		}
	}
}
