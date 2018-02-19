/**
 * Blocks.java
 */

import java.lang.Integer;
import java.lang.System;

public class Blocks {
    private int A;
    private int B;
    private static int C;
    
    {
        B = 28;
    }

    static {
        C = 42;
    }

    public Blocks(int A) {
        this.A = A;
    }

    public int f() {
        return A + B * C;
    }

    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        Blocks blocks = new Blocks(x);
        System.out.println(blocks.f());
    }
}
