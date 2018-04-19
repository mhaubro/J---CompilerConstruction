interface A {
    public int f(int x);
}

interface C {
    public int k(int x);
}

public class B implements A,C {
    public int f(int x) {
        return x * x;
    }
}
