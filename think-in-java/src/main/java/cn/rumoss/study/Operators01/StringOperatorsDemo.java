package cn.rumoss.study.Operators01;

public class StringOperatorsDemo {

    public static void main(String[] args) {

        int x = 0, y = 1, z = 2;
        String s = "x, y, z ";
        System.out.println(s + x + y + z);
        System.out.println(x + " " + s); // Converts x to a String
        System.out.println(x + y + z + " " + s);// by order, add first then concatenate
        s += "(summed) = "; // Concatenation operator
        System.out.println(s + (x + y + z));
        System.out.println("" + x); // Shorthand for Integer.toString()

    }

}
