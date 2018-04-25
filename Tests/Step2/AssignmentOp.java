package pass.ParserTests;
import java.lang.System;

public class AssignmentOp {

    public static void main(String[] args) {
        int a = 10;

        a += 10;
        a -= 5;
        a *= 2;
        a /= 3;
        a %= 22;

        System.out.println(a);
    }
}
