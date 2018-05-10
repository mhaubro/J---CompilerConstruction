package junit;


import junit.framework.TestCase;
import pass.Initializers;

public class InitializersTest extends TestCase {
	Initializers init;


	protected void setUp() throws Exception {
		super.setUp();
		init = new Initializers();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}


	public void testInitializers() {
		assertEquals(init.a, 1.2, 1e-6);
		assertEquals(init.b, 1.5, 1e-6);
		assertEquals(Initializers.c, 2.1, 1e-6);
		assertEquals(init.d, 3.6, 1e-6);
	}
}
