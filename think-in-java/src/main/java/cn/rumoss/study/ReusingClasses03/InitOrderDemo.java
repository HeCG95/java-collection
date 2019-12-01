package cn.rumoss.study.ReusingClasses03;


public class InitOrderDemo extends Father {
    private PObject p = new PObject("子类 - 实例变量");

    static {
        sp = new PObject("子类静态代码块 - 静态变量");
    }
    static PObject sp = new PObject("子类 - 静态变量");

    InitOrderDemo(){
        System.out.println("子类构造函数开始执行 ...");
    }

    public static void main(String[] args) {

        new InitOrderDemo();

    }
}

class Father{
    private PObject p = new PObject("父类 - 实例变量");

    private static PObject sp = new PObject("父类 - 静态变量");

    Father(){
        System.out.println("父类构造函数开始执行 ...");
    }

    {
        p = new PObject("父类实例代码块 - 实例变量");
    }

    static {
        sp = new PObject("父类静态代码块 - 静态变量");
    }
}

class PObject{
    PObject(String type){
        System.out.println("作为 "+type+" 成员的 PObject 构造函数执行 ...");
    }
}