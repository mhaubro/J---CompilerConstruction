package junit;

import junit.framework.TestCase;
import pass.LegalInterface;


public class InterfaceTest extends TestCase {
    private LegalInterface interfacet;

    protected void setUp() throws Exception {
        super.setUp();
        interfacet = new LegalInterface();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testInterfaceFunctions() {
       assertEquals(interfacet.testValues(), 52);
    }

}