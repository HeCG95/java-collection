package cn.rumoss.study.Operators01;

public class ShiftOpsDemo {

    public static void main(String[] args) {

        int a = 0b1;
        System.out.println(Integer.toBinaryString(a));
        a = a << 2;
        System.out.println(Integer.toBinaryString(a));
        a = a >> 2;
        System.out.println(Integer.toBinaryString(a));
        System.out.println("*************************");
        a = -0b1;
        System.out.println(Integer.toBinaryString(a));
        a = a << 2;
        System.out.println(Integer.toBinaryString(a));
        a = a >> 2;
        System.out.println(Integer.toBinaryString(a));
        System.out.println("*************************");
        System.out.println(a);
        a = a >>> 2;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(a);
        System.out.println("*************************");
        System.out.println(Integer.toBinaryString(33));// 100001
        System.out.println("50 << 33 = " + (50 << 33));// 实际对于33，只有右边5位才有作用 其实就等效于 00001 即 50 << 1
        System.out.println("50 << 1 = " + (50 << 1));
        System.out.println("*************************");
        System.out.println(Integer.toBinaryString(65));// 1000001
        long l = 50;
        System.out.println("50 << 65 = " + (l << 65));
        l = 50;
        System.out.println("50 << 1 = " + (l << 1));

        System.out.println("*************************");
        System.out.println("*************************");
        int i = -1;
        System.out.println("-1 = " + Integer.toBinaryString(i));
        i >>>= 10;
        System.out.println("i >>>= 10 -> " + Integer.toBinaryString(i));
        System.out.println("i >>>= 10 -> " + i);
        l = -1;
        System.out.println("-1 = " + Long.toBinaryString(l));
        l >>>= 10;
        System.out.println("l >>>= 10 -> " + Long.toBinaryString(l));
        short s = -1;
        System.out.println("short: -1 = " + Integer.toBinaryString(s));
        s >>>= 10;
        System.out.println("s >>>= 10 -> " + Integer.toBinaryString(s));
        byte b = -1;
        System.out.println("byte: -1 = " + Integer.toBinaryString(b));
        b >>>= 10;// 先被转换成int类型，再进行右移操作，然后被截断,赋值给原来的类型，可能得到-1的结果
        System.out.println("b >>>= 10 -> " + Integer.toBinaryString(b));
        System.out.println("b >>>= 10 -> " + b);// -1
        b = -1;
        System.out.println("byte: -1 = " + Integer.toBinaryString(b));
        System.out.println("b >>>= 10 -> " + Integer.toBinaryString(b>>>10));
        b = -1;
        System.out.println("b >>>= 10 -> " + (b>>>10));

    }

}
