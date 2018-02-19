/**
 * Operators.java
 */

import java.lang.Integer;
import java.lang.System;

public class Operators {
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        x++;
        x -= 1;
        x *= 3;
        x /= 5;
        x %= 7;
        System.out.println(--x);
        System.out.println(x == k || false);
    }
}
