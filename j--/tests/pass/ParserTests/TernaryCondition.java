package pass.ParserTests;

import java.lang.System;

public class TernaryCondition {
    public static void main(String[] args) {
        System.out.println( ternaryExpression(3) );
    }

    public static String ternaryExpression(int a) {
        return a > 0 ? "Success !" : "Failed";
    }
}
