interface C {
    public int k(int x);
}

interface E {
    public int m(int x);
}

interface D {
	public int j(int y);
}

interface A extends D, E {
    public int f(int x);
}


public class B implements A,C {
    public int f(int x) {
        return x * x;
    }
	
	public int k(int x) {
		return x ^ x;
	}
	
	public int j(int y) {
		return y >> 1;
	}
	
	public int m(int x) {
		return x >> 2;
	}
}
