interface A {
    public int f(int x);
}

interface C {
    public int k(int x);
}

class D {
    public int a;
}

public class B extends D implements A,C {
    public int f(int x) {
        return x * x;
    }
}
