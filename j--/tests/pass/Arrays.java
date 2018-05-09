// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package pass;

import java.lang.System;

public class Arrays {

    int[] ia = { 1, 2, 3, (int) '4' };

    int[] ia2 = { 3, 4, 5, 6, 7 };

    int[][] iaa = { ia, ia2 };

    int[][] iaa2 = { { 1, 2, 3 }, { 1, 2, 3, 4 }, {} };

    double[] a = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0};

    public void test() {
        /*
        for(int i = 0; i <= ia.length-1; i++)
        {
            System.out.println(a[i]);
        }
        */
    }

    public static void main(String[] args) {
        Arrays main = new Arrays();
        main.test();
    }

}
