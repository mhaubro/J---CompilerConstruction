package junit;

import junit.framework.TestCase;
import pass.UnaryPlus;

public class UnaryPlusTest extends TestCase {
	UnaryPlus uplus;

	protected void setUp() throws Exception {
		super.setUp();
		uplus = new UnaryPlus();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testUnaryPlus() {
		assertEquals(uplus.unaryPlus(6), 6);
		assertEquals(uplus.unaryPlus(123), 123);
		assertEquals(uplus.unaryPlus(63), 63);
		assertEquals(uplus.unaryPlus(3472), 3472);
	}

}
