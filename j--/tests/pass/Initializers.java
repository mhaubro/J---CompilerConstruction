package pass;

import com.sun.org.apache.xml.internal.security.Init;

public class Initializers {
	public double a,b,d;
	static public double c;
	{
		a = 1.2;
	}
	{
		b = 1.5;
	}

	static {
		c = 2.1;
	}

	public Initializers() {
		d = c + b;
	}

	public static void main (String[] args) {
		return;
	}


}
