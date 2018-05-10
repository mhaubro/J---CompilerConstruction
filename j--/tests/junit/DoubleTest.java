package junit;

import junit.framework.TestCase;
import pass.OperatorsDouble;
import pass.OperatorsInt;


public class DoubleTest extends TestCase {
    private OperatorsDouble opDouble;

    protected void setUp() throws Exception {
        super.setUp();
        opDouble = new OperatorsDouble();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }



    public void testDoubleOps() {
		this.assertEquals(opDouble.minusAssign(3.5, 1.2), 2.3, 1e-6);
		this.assertEquals(opDouble.multAssign(5.1, 3.1), 15.81, 1e-6);
		this.assertEquals(opDouble.divAssign(5.0, 2.0), 2.5, 1e-6);
		this.assertEquals(opDouble.remAssign(14.3, 2.0), 0.3, 1e-6);
		this.assertEquals(opDouble.plusAssign(3.5, 1.2), 4.7, 1e-6);
		this.assertEquals(opDouble.rem(1.1, .2d), .1, 1e-6);
        this.assertEquals(opDouble.div(5.0, 2.0), 2.5, 1e-6);
        this.assertEquals(opDouble.plus(14.3, 2.0d), 16.3d, 1e-6);
        this.assertEquals(opDouble.minus(3.5, 1.2), 2.3, 1e-6);
        this.assertEquals(opDouble.mul(5.1, 3.1), 15.81, 1e-6);
		this.assertEquals(opDouble.postdec(5.0), 4.0, 1e-6);
		this.assertEquals(opDouble.predec(14.3), 12.3, 1e-6);
		this.assertEquals(opDouble.postinc(5.0), 6, 1e-6);
        this.assertEquals(opDouble.preinc(14.3), 16.3, 1e-6);
        this.assertEquals(opDouble.cmp(3.0, 4.0), 0.1, 1e-6);
        this.assertEquals(opDouble.cmp(4.0, 3.0), 2.0, 1e-6);

    }
    public void testDoubleArrays(){
        double[] darr;
        darr = opDouble.initClassicfor();
        double d = 0.0;
        for (double ddub : darr){
            d+=ddub;
        }
        this.assertEquals(d, 24.0, 1e-6);
        darr = opDouble.initClassicfordouble();
        d = 0.0;
        for (double ddub : darr){
            d+=ddub;
        }
        this.assertEquals(d, 24.0, 1e-6);
        darr = opDouble.initClassiclist();
        d = 0.0;
        for (double ddub : darr){
            d+=ddub;
        }
        this.assertEquals(d, 24.0, 1e-6);
    }

    public void testCast(){
        this.assertEquals(opDouble.cast(5.5), 5);
        this.assertEquals(opDouble.castToDouble(5), 5.0);
    }
}