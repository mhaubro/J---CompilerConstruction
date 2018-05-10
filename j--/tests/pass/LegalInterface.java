package pass;

import java.lang.System;

public class LegalInterface {
    public static void main(String[] args){
        LegalInterface a = new LegalInterface();
        System.out.println(a.testValues());
    }

    public int testValues(){
        C1 c1 = new C1();
        C2 c2 = new C2();
        C3 c3 = new C3();
        C4 c4 = new C4();

        int sum = 0;
        sum += c1.a();
        sum += c1.c();
        sum += c1.d();

        sum += c2.c();

        sum += c3.a();
        sum += c3.c();

        sum += c4.a();
        sum += c4.b();
        sum += c4.c();
        sum += c4.e();

        return sum;
    }

}


interface Ai {

    int a();

}

interface Bi extends Ai {
    int b();
}

interface Ci {
    int c();
}

interface Di extends Ai, Ci {
    int d();
}

interface Ei extends Bi, Ci {
    int e();
}


class C1 implements Di {
    public int d(){
        return 1+4;
    }
    public int a(){
        return 1+1;
    }
    public int c(){
        return 1+3;
    }


}
class C2 implements Ci {
    public int c(){
        return 2+3;
    }

}
class C3 implements Ai, Ci {

    public int a(){
        return 3+1;
    }
    public int c(){
        return 3+3;
    }


}

class C4 implements Ei {
    public int a(){
        return 4+1;
    }
    public int b(){
        return 4+2;
    }
    public int c(){
        return 4+3;
    }
    public int e(){
        return 4+5;
    }

}


