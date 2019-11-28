package cn.rumoss.study.Initialization02;

public class InitialValuesDemo {

    boolean t;
    char c;
    byte b;
    short s;
    int i;
    long l;
    float f;
    double d;
    InitialValuesDemo reference;

    //int jj = g(ii);// illegal forward reference
    int ii = f();
    int f(){ return 11; }
    int g(int n){ return n*10; }

    void printInitialValues() {
        print("Data type Initial value");
        print("boolean " + t);
        print("char [" + c + "]");
        int ic = c;
        print("ichar [" + ic + "]");
        print("byte " + b);
        print("short " + s);
        print("int " + i);
        print("long " + l);
        print("float " + f);
        print("double " + d);
        print("reference " + reference);
        //print("jj " + jj);
    }

    public static void print(String s){
        System.out.println(s);
    }

    public static void main(String[] args) {

        InitialValuesDemo demo = new InitialValuesDemo();
        demo.printInitialValues();

    }

}
