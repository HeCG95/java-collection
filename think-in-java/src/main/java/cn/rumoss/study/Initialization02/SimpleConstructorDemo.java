package cn.rumoss.study.Initialization02;

public class SimpleConstructorDemo {

    public static void main(String[] args) {

        for(int i=0;i<5;i++){
            Rock rock = new Rock(i);
        }

    }

}

class Rock{
    int fun(int i){
        return i;
    }
    void funv(int i){
        System.out.println(i);
    }
    void funvv(int i){
        System.out.println(i);
        return;
    }
    Rock(int i){
        System.out.println(i + " Rock in Constructor ...");
    }
}
