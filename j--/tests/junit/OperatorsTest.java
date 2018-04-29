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
		this.assertEquals(opInt.minusAssign(3, 1), 2);
		this.assertEquals(opInt.multAssign(5, 3), 15);
		this.assertEquals(opInt.divAssign(14, 5), 2);
		this.assertEquals(opInt.remAssign(14, 5), 4);
	}

	public void testDoubleOps() {
		this.assertEquals(opDouble.minusAssign(3.5, 1.2), 2.3, 1e-6);
		this.assertEquals(opDouble.multAssign(5.1, 3.1), 15.81, 1e-6);
		this.assertEquals(opDouble.divAssign(5.0, 2.0), 2.5, 1e-6);
		this.assertEquals(opDouble.remAssign(14.3, 2.0), 0.3, 1e-6);
	}

}
