package cn.rumoss.study.Initialization02;

public class Mugs {

    Mugs(){
        print("Mugs()");
    }
    Mugs(int i){
        print("Mug(int)");
    }

    Mug mug1;
    Mug mug2;
    {
        mug1 = new Mug(1);
        mug2 = new Mug(2);
        print("mug1 & mug2 initialized2");
    }

    static void print(String s){
        System.out.println(s);
    }

    public static void main(String[] args) {

        System.out.println("In main()");
        new Mugs();// Mug(1) Mug(2) and mug1 & mug2 initialized | Mugs()
        print("new Mugs() completed");
        new Mugs(1);// Mug(1) Mug(2) and mug1 & mug2 initialized | Mugs(int)
        print("new Mugs(1) completed");
        System.out.println("Out main()");

    }

}

class Mug{
    Mug(int marker){
        print("Mug("+marker+")");
    }
    void f(int marker){
        print("f("+marker+")");
    }

    static void print(String s){
        System.out.println(s);
    }
}
