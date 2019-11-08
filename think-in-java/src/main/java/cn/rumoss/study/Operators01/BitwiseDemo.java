package cn.rumoss.study.Operators01;

public class BitwiseDemo {

    public static void main(String[] args) {

        System.out.println("1 & 1 = " + (0b1 & 0b1));
        System.out.println("1 | 0 = " + (0b1 | 0b0));
        System.out.println("0 | 0 = " + (0b0 | 0b0));
        System.out.println("1 ^ 0 = " + (0b1 ^ 0b0));
        System.out.println("1 ^ 1 = " + (0b1 ^ 0b1));

        System.out.println("Integer test, not for bit: ");
        System.out.println(Integer.toBinaryString(1));
        System.out.println("~1 = " + (~1));
        System.out.println(Integer.toBinaryString(-2));
        System.out.println(Integer.toBinaryString(0));
        System.out.println("~0 = " + (~0));
        System.out.println(Integer.toBinaryString(~0));

    }

}
