package junit;
import junit.framework.TestCase;
import pass.Exceptions;


public class ExceptionTest extends TestCase {
	private Exceptions exTool;

	protected void setUp() throws Exception {
		super.setUp();
		exTool = new Exceptions();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}


	public void testException() {
		assertEquals(exTool.throwAndReturnExceptionMsg(0),
					"This is a null pointer exception!");
		assertEquals(exTool.throwAndReturnExceptionMsg(1),
				"This is a char conversion exception!");
		assertEquals(exTool.throwAndReturnExceptionMsg(2),
				"This is an IO exception!");
		assertEquals(exTool.throwAndReturnExceptionMsg(3),
				"No exceptions!");
	}
}
