package junit;

import junit.framework.TestCase;
import pass.LegalInterface;


public class InterfaceTest extends TestCase {
    private LegalInterface interfaceTool;

    protected void setUp() throws Exception {
        super.setUp();
        interfaceTool = new LegalInterface();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testInterfaceFunctions() {
        // This casts Interface Ai to implementing Class C2, where a() = 10
        assertEquals(interfaceTool.testInterfaceCast(1), 10);
        // This casts Interface Ai to implementing Class C3, where a() = 4
        assertEquals(interfaceTool.testInterfaceCast(0), 4);
        assertEquals(interfaceTool.testValues(), 53);
    }

}