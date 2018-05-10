package junit;

import junit.framework.TestCase;
import pass.OperatorsDouble;
import pass.OperatorsInt;


public class OperatorsTest extends TestCase {
	private OperatorsInt opInt;
	private OperatorsDouble opDouble;

	protected void setUp() throws Exception {
		super.setUp();
		opInt = new OperatorsInt();
		opDouble = new OperatorsDouble();
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
		this.assertEquals(opInt.bitright(0xFFFFFFFF, 2), 0x807FFFFF);//Sign is kept.
		this.assertEquals(opInt.land(15, 17), 31);
		this.assertEquals(opInt.lor(15, 17), 1);
		this.assertEquals(opInt.xor(15, 17), 30);
	/*	this.assertEquals(opInt.bitleftassign(16, 1), 32);
		this.assertEquals(opInt.bitrightassign(16, 2), 4);
		this.assertEquals(opInt.landassign(15, 17), 31);
		this.assertEquals(opInt.lorassign(15, 17), 1);
		this.assertEquals(opInt.xorassign(15, 17), 30);
		this.assertEquals(opInt.arithrs(0xFFFFFFFF, 2), 0xFFFFFF);
		this.assertEquals(opInt.arithrsassign(0xFFFFFFFF, 2), 0xFFFFFF);
		this.assertEquals(opInt.arithrsassign(0xFFFFFFFF, 2), 0xFFFFFF);
		this.assertEquals(opInt.arithrsassign(0xFFFFFFFF, 2), 0xFFFFFF);
		this.assertEquals(opInt.arithrsassign(0xFFFFFFFF, 2), 0xFFFFFF);
		this.assertEquals(opInt.arithrsassign(0xFFFFFFFF, 2), 0xFFFFFF);
	*/	this.assertEquals(opInt.unaryplus(10, 2), 12);
		this.assertEquals(opInt.unaryminus(10, 2), 8);
		this.assertEquals(opInt.postdec(5), 4);
		this.assertEquals(opInt.predec(14), 12);
		this.assertEquals(opInt.postinc(5), 6);
		this.assertEquals(opInt.preinc(14), 16);
	}

	public void testDoubleOps() {
		this.assertEquals(opDouble.minusAssign(3.5, 1.2), 2.3, 1e-6);
		this.assertEquals(opDouble.multAssign(5.1, 3.1), 15.81, 1e-6);
		this.assertEquals(opDouble.divAssign(5.0, 2.0), 2.5, 1e-6);
		this.assertEquals(opDouble.remAssign(14.3, 2.0), 0.3, 1e-6);
		this.assertEquals(opDouble.plusAssign(3.5, 1.2), 2.3, 1e-6);
		this.assertEquals(opDouble.rem(1.1, .2d), .1, 1e-6);
		this.assertEquals(opDouble.div(5.0, 2.0), 2.5, 1e-6);
		this.assertEquals(opDouble.plus(14.3, 2.0d), 16.3d, 1e-6);
		this.assertEquals(opDouble.minus(3.5, 1.2), 2.3, 1e-6);
		this.assertEquals(opDouble.mul(5.1, 3.1), 15.81, 1e-6);
		this.assertEquals(opDouble.postdec(5.0), 4.0, 1e-6);
		this.assertEquals(opDouble.predec(14.3), 12.3, 1e-6);
		this.assertEquals(opDouble.postinc(5.0), 6, 1e-6);
		this.assertEquals(opDouble.preinc(14.3), 16.3, 1e-6);
	}

}
