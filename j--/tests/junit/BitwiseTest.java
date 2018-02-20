package junit;

import junit.framework.TestCase;
import pass.BitwiseAnd;
import pass.BitwiseOr;
import pass.BitwiseXor;

public class BitwiseTest extends TestCase {
	BitwiseAnd bwand;
	BitwiseOr bwor;
	BitwiseXor bwxor;

	protected void setUp() throws Exception {
		super.setUp();
		bwand = new BitwiseAnd();
		bwor = new BitwiseOr();
		bwxor = new BitwiseXor();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testBitwise() {
		//Bitwise And tests
		this.assertEquals(bwand.bitwiseAnd(7, 3), 3);
		this.assertEquals(bwand.bitwiseAnd(283, 0), 0);
		this.assertEquals(bwand.bitwiseAnd(3472, -1), 3472);

		// Bitwise Or tests
		this.assertEquals(bwor.bitwiseOr(4382, 2734), 7102);
		this.assertEquals(bwor.bitwiseOr(384, 0), 384);
		this.assertEquals(bwor.bitwiseOr(3, 4), 7);

		// Bitwise Xor tests
		this.assertEquals(bwxor.bitwiseXor(2384, 2384), 0);
		this.assertEquals(bwxor.bitwiseXor(0, -1), -1);
		this.assertEquals(bwxor.bitwiseXor(4382, 2734), 7088);
	}
}
