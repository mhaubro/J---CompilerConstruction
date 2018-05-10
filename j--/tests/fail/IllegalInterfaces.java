package fail;

public class IllegalInterfaces {




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



