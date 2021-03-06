// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package junit;

import java.io.File;
import junit.framework.TestCase;
import junit.framework.Test;
import junit.framework.TestSuite;
import pass.*;

/**
 * JUnit test suite for running the j-- programs in tests/pass.
 */

public class JMinusMinusTestRunner {

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(HelloWorldTest.class);
        suite.addTestSuite(FactorialTest.class);
        suite.addTestSuite(GCDTest.class);
        suite.addTestSuite(SeriesTest.class);
        suite.addTestSuite(ClassesTest.class);
        suite.addTestSuite(DivisionTest.class);
        suite.addTestSuite(OperatorsTest.class);
        suite.addTestSuite(DoubleTest.class);
        suite.addTestSuite(ExceptionTest.class);
        suite.addTestSuite(ForTest.class);
        suite.addTestSuite(InterfaceTest.class);
        suite.addTestSuite(ConditionalExpressionTest.class);
        suite.addTestSuite(InitializersTest.class);
        suite.addTestSuite(RemainderTest.class);
        suite.addTestSuite(ShiftTest.class);
        suite.addTestSuite(BitwiseTest.class);
        suite.addTestSuite(UnaryPlusTest.class);
        return suite;
    }

    /**
     * Runs the test suite using the textual runner.
     */

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

}
