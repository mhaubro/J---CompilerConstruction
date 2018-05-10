/**
 * Interface.java
 */

import java.lang.System;
import java.lang.Integer;

interface A {
    public int f(int x);
}

class B implements A {
    public int f(int x) {
        return x * x;
    }
}

class C implements A {
    public int f(int x) {
        return x * x * x;
    }
	public int g(int x) {
		return x * x * x;
	}
}

public class Interface {
    public static void main(String[] args) {
        int x = 3;
        B b = new B();
		A d = (A)(new B());
        C c = new C();
        System.out.println(b.f(x));        
		System.out.println(d.f(x));
        System.out.println(c.f(x));
    }
}
