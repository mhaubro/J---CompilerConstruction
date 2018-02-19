import java.lang.System;

public class Operators {
    public static void main(String[] args) {
        System.out.println(true && false || true);
        int x = 42;
        x -= 2;
        x *= 2;
        x /= 10;
        x %= 3;
        System.out.println(x++);
        System.out.println(--x);
    }
}
