package cn.rumoss.study.Initialization02;

public class Constructor2Demo {

    public static void staticFun(MyObject obj){
        obj.instanceFun();
    }

    public static MyObject staticFun2(MyObject obj){
        obj.instanceFun();
        return obj;
    }

    public static void main(String[] args) {

        MyObject a = new MyObject();
        MyObject b = staticFun2(a);
        System.out.println(a==b);

    }

}

class MyObject{

    public void instanceFun(){
        System.out.println("I am an instance function");
    }

}
