package cn.rumoss.study.ClassFileDeep031;

public class A extends B {
    public A(){
        System.out.println(this.hashCode());
        System.out.println(super.equals(this));
    }

    public static void main(String[] args) {
        A a = new A();
        System.out.println(a);
    }
}

class B{
    public B(){
        System.out.println(this.hashCode());
    }
}
