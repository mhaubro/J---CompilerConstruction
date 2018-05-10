package pass;

import java.lang.NullPointerException;
import java.io.IOException;
import java.io.CharConversionException;
import java.lang.System;

public class Exceptions {

    public static void main(String[] args){
        System.out.println(throwAndReturnExceptionMsg(1));
    }

    private static void throwAnException(int exceptionID) throws CharConversionException, NullPointerException, IOException {
        if (exceptionID == 0){
            throw new NullPointerException("This is a null pointer exception");
        }
        else if (exceptionID == 1) {
            throw new CharConversionException ("This is a char conversion exception");
        }
        else if (exceptionID == 2){
            throw new IOException("This is an IO exception");
        }
    }

    public static String throwAndReturnExceptionMsg(int exceptionID) {
        String retString = "No exceptions";
		try {
			throwAnException(exceptionID);
		} catch (NullPointerException | CharConversionException ex) {
			retString = ex.getMessage();
		} catch (IOException ex) {
			retString = ex.getMessage();
		} finally {
			retString = retString + "!";
		}
        return retString;
    }

}
