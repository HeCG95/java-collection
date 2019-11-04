package cn.rumoss.study.Operators01;

public class ShortCircuitDemo {

    static boolean test1(int val){
        System.out.println("test1("+val+") result: " + (val < 1) );
        return val < 1;
    }

    static boolean test2(int val){
        System.out.println("test2("+val+") result: " + (val < 2) );
        return val < 2;
    }

    static boolean test3(int val){
        System.out.println("test3("+val+") result: " + (val < 3) );
        return val < 3;
    }

    public static void main(String[] args) {

        boolean result = test1(0) && test2(2) && test3(3);
        System.out.println("Final result: " + result);

    }

}
