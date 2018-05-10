package junit;

import junit.framework.TestCase;
import pass.OperatorsInt;


public class OperatorsTest extends TestCase {
	private OperatorsInt opInt;

	protected void setUp() throws Exception {
		super.setUp();
		opInt = new OperatorsInt();

	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}


	public void testIntOps() {
		this.assertEquals(opInt.plusAssign(3, 1), 4);
		this.assertEquals(opInt.minusAssign(3, 1), 2);
		this.assertEquals(opInt.multAssign(5, 3), 15);
		this.assertEquals(opInt.divAssign(14, 5), 2);
		this.assertEquals(opInt.remAssign(14, 5), 4);
		this.assertEquals(opInt.bitleft(16, 1), 32);
		this.assertEquals(opInt.bitright(16, 2), 4);
		this.assertEquals(opInt.bitright(0xFFFFFFFC, 1), 0xFFFFFFFE);//Sign is kept.
		this.assertEquals(opInt.bitleft(0xFFFFFFFF, 4), -16);//Sign is kept.
		this.assertEquals(opInt.bitright(0xFFFFFFFF, 4), -1 >> 4);
		this.assertEquals(opInt.bitright(0xFFFFFFFF, 4), -1);
		this.assertEquals(opInt.land(15, 17), 1);
		this.assertEquals(opInt.lor(15, 17), 31);
		this.assertEquals(opInt.xor(15, 17), 30);
		this.assertEquals(opInt.unaryplus(10, 2), 12);
		this.assertEquals(opInt.unaryminus(10, 2), 8);
		this.assertEquals(opInt.postdec(5), 4);
		this.assertEquals(opInt.predec(14), 12);
		this.assertEquals(opInt.postinc(5), 6);
		this.assertEquals(opInt.preinc(14), 16);
	}
}
