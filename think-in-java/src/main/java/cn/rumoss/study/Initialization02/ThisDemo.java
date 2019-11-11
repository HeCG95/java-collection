package cn.rumoss.study.Initialization02;

public class ThisDemo {

    public static void main(String[] args) {

        MyObj a = new MyObj();
        MyObj b = new MyObj();
        a.fun();
        b.fun();

    }

}

class MyObj{
    void a(){
        System.out.println("this demo");
    }
    void b(){
        a();
        System.out.println("this demo call a");
    }
    void fun(){
        System.out.println("this demo");
    }

    void fun(int i){
        System.out.println("this demo");
    }

    static void fun(Object o){
        System.out.println("this demo");
    }
}
