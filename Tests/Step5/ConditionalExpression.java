/**
 * ConditionalExpression.java
 */

import java.lang.Integer;
import java.lang.System;

public class ConditionalExpression {
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        System.out.println(x + (x > k ? " is greater than "  :
                                " is less than or equal to ") + k);
    }
}
