package pass;

import java.lang.System;

public class ForLoops {
    public static void main(String[] args) {

        String sourceChar = "ABCDEFG";
        int[] numbers = {1,2,3,4,5,6,7,8};
	    double d = .5;

        for(int i = 0; i <= 6; i++) {
            System.out.print(sourceChar.charAt(i));
        }
        System.out.println();

        for(int item: numbers) {
            System.out.print(item);
        }



    }

    public int intRangeFor(int ite){
        int sum = 0;
        for (int i = 0; ite > i; i++){
            sum+=i;
        }
        return sum;
    }

    public int[] intClassicFor(){
        int[] darr = new int[8];
        for (int i = 0; darr.length > i; i++) {
            darr[i] = 3;
        }
        return darr;
    }

    public int[] doubleClassicFor(){
        int[] darr = new int[8];
        for (double d = 0.1; 7.5 > d; d++) {
            darr[(int) d] = 3;
        }
        return darr;

    }

    public int rangeForValue(int[] vals){
        int sum = 0;
        for (int i : vals){
            sum+=i;
        }
        return sum;


    }
    public int rangeForObject(int ite){

        simpleClass[] objs = new simpleClass[ite];

        for (int i = 0; objs.length > i; i++){
            objs[i] = new simpleClass();
            objs[i].val = i*2;
        }

        int sum = 0;
        for (simpleClass s : objs){
            sum+=s.val;
        }
        return sum;
    }

    public int doubleFor(int x, int y, int ite){
        int sum = 0;
        int i;
        int j;
        for (i = x, j = y;  ite > i && ite > j; i++,j++){
            sum += i + j;
        }
        return sum;
    }

}

class simpleClass{
    int val;
}
