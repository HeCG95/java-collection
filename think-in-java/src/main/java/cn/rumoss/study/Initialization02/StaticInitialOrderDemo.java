package cn.rumoss.study.Initialization02;

public class StaticInitialOrderDemo {

    static void print(String s){
        System.out.println(s);
    }

    public static void main(String[] args) {

        System.out.println("In main()");
        new Cupboard();// Bowl(3) Cupboard()
        new Cupboard();
        table.f2(1);
        cupboard.f3(1);
        System.out.println("Out main()");

    }

    static {
        Table table = new Table();
        Table table2 = new Table();
    }

    static {
        table = null;
        table = new Table();
    }

    static Table table = new Table();// Bowl(1) Bowl(2) and Table()
    // first static init and then instance init
    static Cupboard cupboard = new Cupboard();// Bowl(4) Bowl(5) and Bowl(3) Cupboard()

    /*static {
        table = null;
        table = new Table();
    }*/

}

class Bowl{
    Bowl(int marker){
        StaticInitialOrderDemo.print("Bowl("+marker+")");
    }
    void f1(int marker){
        StaticInitialOrderDemo.print("f1("+marker+")");
    }
}

class Table{
    static Bowl bow1 = new Bowl(1);
    Table(){
        StaticInitialOrderDemo.print("Table()");
        bow2.f1(1);
    }
    void f2(int marker){
        StaticInitialOrderDemo.print("f2("+marker+")");
    }
    static Bowl bow2 = new Bowl(2);
}

class Cupboard{
    Bowl bow3 = new Bowl(3);
    static Bowl bow4 = new Bowl(4);
    Cupboard(){
        StaticInitialOrderDemo.print("Cupboard()");
        bow4.f1(2);
    }
    void f3(int marker){
        StaticInitialOrderDemo.print("f3("+marker+")");
    }
    static Bowl bow5 = new Bowl(5);
}
