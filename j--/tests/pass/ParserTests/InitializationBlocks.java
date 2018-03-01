package pass.ParserTests;

public class InitializationBlocks {


    static {
        System.out.println("Static, only done once!");
    }

    {
        System.out.println("Non-static, done always");
    }

    int dummyVariable;

}
