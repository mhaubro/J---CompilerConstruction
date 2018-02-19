package junit;

import junit.framework.TestCase;
import pass.Shift;

public class ShiftTest extends TestCase {
	private Shift shift;

	protected void setUp() throws Exception {
		super.setUp();
		shift = new Shift();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testShift() {
		this.assertEquals(shift.shiftLeft(18, 2), 72);
		this.assertEquals(shift.shiftRight(-37, 3), -5);
		this.assertEquals(shift.logicShiftRight(5, 1), 2);
		this.assertEquals(shift.logicShiftRight(-5, 30), 3);
	}


}
