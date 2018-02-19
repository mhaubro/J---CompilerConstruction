/**
 * Interface.java
 */

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
}

public class Interface {
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        B b = new B();
        C c = new C();
        System.out.println(b.f(x));
        System.out.println(c.f(x));
    }
}
