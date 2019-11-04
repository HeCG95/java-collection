package cn.rumoss.study.Operators01;

public class StringPlusDemo {

    public static void main(String[] args) {

        String a = "I am A ";
        String b = "I am B";
        a = a + b;
        System.out.println(a);
        a = "I am A ";
        a += b;
        System.out.println(a);

    }

}
