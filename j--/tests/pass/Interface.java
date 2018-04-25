interface A {
    public int f(int x);
}

interface C {
    public int k(int x);
}

class D {
    public int a;
}

class B extends D implements A,C {
    public int f(int x) {
        return x * x;
    }
    public int k(int x) {
        return x * x;
    }

}
