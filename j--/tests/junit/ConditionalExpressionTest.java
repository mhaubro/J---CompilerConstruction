package junit;

import junit.framework.TestCase;
import pass.ConditionalExpression;

public class ConditionalExpressionTest extends TestCase {
	private ConditionalExpression condExpr;

	protected void setUp() throws Exception {
		super.setUp();
		condExpr = new ConditionalExpression();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}


	public void testCondExpr() {
		this.assertEquals(condExpr.intConditionalExpr(true, 34, 15), 34);
		this.assertEquals(condExpr.intConditionalExpr(false, 34, 15), 15);

		this.assertEquals(condExpr.dblConditionalExpr(true, 34.5, 15.1), 34.5, 1e-6);
		this.assertEquals(condExpr.dblConditionalExpr(false, 34.5, 15.1), 15.1, 1e-6);

	}
}
