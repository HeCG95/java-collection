package cn.rumoss.study.Initialization02;

public class InitialOrderDemo {

    static void print(String s){
        System.out.println(s);
    }

    public static void main(String[] args) {

        House house = new House();
        System.out.println("Before f()");
        house.f();
        System.out.println("After f()");

    }

}

class Window{
    Window(int maker){
        InitialOrderDemo.print("Window("+maker+")");
    }
}

class House{
    Window win = new Window(1);
    House(){
        InitialOrderDemo.print("House()");
        win3 = new Window(33);
    }
    Window win2 = new Window(2);
    void f(){
        InitialOrderDemo.print("f()");
    }
    Window win3 = new Window(3);
}
