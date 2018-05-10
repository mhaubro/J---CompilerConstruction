package junit;

import junit.framework.TestCase;
import pass.ForLoops;


public class ForTest extends TestCase {
    private ForLoops loops;

    protected void setUp() throws Exception {
        super.setUp();
        loops = new ForLoops();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }


    public void testForLoops() {
        int[] ints = {2, 3, 4, 5, 6, 7, 8, 9};
        int[] retval = loops.intClassicFor();
        int sum = 0;
        for (int i : retval){
            sum += i;
        }

        this.assertEquals(sum, 24);
        this.assertEquals(loops.doubleFor(2, 3, 5), 12);

        retval = loops.doubleClassicFor();
        sum = 0;
        for (int i : retval){
            sum += i;
        }

        this.assertEquals(sum, 24);
        this.assertEquals(loops.intRangeFor(8), 28);
        this.assertEquals(loops.rangeForObject(8), 56);
        this.assertEquals(loops.rangeForValue(ints), 2+3+4+5+6+7+8+9);

    }
}

