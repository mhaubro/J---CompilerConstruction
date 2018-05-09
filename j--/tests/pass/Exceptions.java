package pass;

import junit.extensions.ExceptionTestCase;
import java.lang.NullPointerException;
import java.io.IOException;
import java.lang.System;

public class Exceptions {

    public static void main(String[] args){
        int i = 0;
        try {
            ExceptionTest(1);
            ExceptionTest(0);
        } catch(IOException | NullPointerException e) {
            i = 5;
            System.out.println("Catched smth");
        }
        finally {
            System.out.println("Finaly block");
        }
        if (i == 5)
            System.out.println("hi");


    }

    public static void ExceptionTest(int test) throws NullPointerException, IOException {
        if (test == 0){
            throw new NullPointerException("Hi!");
        } else if (test == 1){
            throw new IOException("Hi");
        }

        System.out.println("Passed");
    }

}
