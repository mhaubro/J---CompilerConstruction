package pass.ParserTests;

import java.lang.System;

public class Double {

    public static void main (String[] args) {
        System.out.println(ArithmeticOp(21.3,19.2));
    }

    public static double ArithmeticOp(double x, double y) {



        return x / y * x + y - x;
    }
}
