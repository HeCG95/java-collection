package cn.rumoss.study.Operators01;

public class CastingNumbersDemo {

    public static void main(String[] args) {

        double above = 0.7, below = 0.4;
        float fabove = 0.7f, fbelow = 0.4f;
        System.out.println("(int)above: " + (int)above);
        System.out.println("(int)below: " + (int)below);
        System.out.println("(int)fabove: " + (int)fabove);
        System.out.println("(int)fbelow: " + (int)fbelow);

        System.out.println("********************");

        System.out.println("Math.round(above): " + Math.round(above));
        System.out.println("Math.round(below): " + Math.round(below));
        System.out.println("Math.round(fabove): " + Math.round(fabove));
        System.out.println("Math.round(fbelow): " + Math.round(fbelow));

        System.out.println("********************");

        char c = 65;
        System.out.println("c = " + c);
        System.out.println(++c);
        System.out.println("c = " + c);
        System.out.println(c+=1);
        System.out.println("c = " + c);
//        c = c + 1;// Incompatible type
        System.out.println("c = " + (int)c);

        System.out.println("********************");
        System.out.println((byte)127);
        System.out.println(" 127: " + Integer.toBinaryString(127));
        System.out.println(" 128: " + Integer.toBinaryString(128));
        System.out.println(" 129: " + Integer.toBinaryString(129));
        System.out.println("-128: " + Integer.toBinaryString(-128));
        System.out.println("-127: " + Integer.toBinaryString(-127));
        System.out.println((byte)128);
        System.out.println((byte)129);

        System.out.println("********************");
        byte b127 = 0b1111111;// 符号位默认不显示，使用+-表示
        System.out.println("b +127: " + b127);
        byte b_127 = -0b1111111;
        System.out.println("b -127: " + b_127);
        byte b_128 = -128;
        System.out.println("b -128: " + b_128);

    }

}
