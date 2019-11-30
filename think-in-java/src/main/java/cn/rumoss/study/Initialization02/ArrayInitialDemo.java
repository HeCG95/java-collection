package cn.rumoss.study.Initialization02;

public class ArrayInitialDemo {

    public static void main(String[] args) {

        int[] ia = null;
        int[] ib = {};
        System.out.println(ib.length);
        Object o = null;
        System.out.println(String.valueOf(o));
        //System.out.println(o.toString());
        InitialOrderDemo.main(null);

    }

}
