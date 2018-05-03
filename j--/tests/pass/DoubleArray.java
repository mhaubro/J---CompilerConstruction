package pass;

import java.lang.System;

public class DoubleArray {
    static double[] a = {1., 2., 3., 4., 5., 6., 7., 8., 9., 10.};

    public static void main(String[] args) {
        double sum1 = 0.;
        for (int i = 1; i <= 9; i++) {
            sum1 += a[i];
        }
        System.out.println(sum1);
    }
}