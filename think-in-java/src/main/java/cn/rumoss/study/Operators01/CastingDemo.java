package cn.rumoss.study.Operators01;

public class CastingDemo {

    public static void main(String[] args) {

        int i = 200;
        long lng = (long)i;
        lng = i; // "Widening," so cast not really required
        System.out.println(lng);
        long lng2 = (long)200;
        lng2 = 200;
        // A "narrowing conversion":
        i = (int) lng2; // Cast required
        System.out.println(i);
        boolean b = true;
//        int bi = b;// Incompatible type
        int ic = 'a';
        System.out.println(ic);// 97

    }

}
