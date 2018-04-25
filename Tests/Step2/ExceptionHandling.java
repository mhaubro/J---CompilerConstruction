package pass.ParserTests;

import junit.extensions.ExceptionTestCase;

public class ExceptionHandling {

    public static void main(String[] args){
        try {
            ExceptionTest(1);
            ExceptionTest(0);
        } catch(Exception e) {
            System.out.println("Catched smth");
        }
        finally {
            System.out.println("Finaly block");
        }


    }

    public static void ExceptionTest(int test) throws Exception{
        if (test == 0){
            throw new Exception("Hi!");
        }

        System.out.println("Passed");
    }

}
