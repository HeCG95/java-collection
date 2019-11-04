package cn.rumoss.study.Operators01;

import java.util.Random;

public class MathOpsDemo {

    public static void main(String[] args) {

        Random random = new Random(8);
//        Random random = new Random(47);
        int i,j,k;
        j = random.nextInt(100)+1;// from 1 to 100
        System.out.println("j: " + j);
        k = random.nextInt(100)+1;
        System.out.println("k: " + k);

        i = j + k;
        System.out.println("j + k = " + i);
        i = j - k;
        System.out.println("j - k = " + i);
        i = j / k;
        System.out.println("j / k = " + i);
        i = j * k;
        System.out.println("j * k = " + i);
        i = j % k;
        System.out.println("j % k = " + i);
        k %= j;
        System.out.println("k %= j : " + k);

        System.out.println("****** Floating-point test ******");
        float u,v,w;
        v = random.nextFloat();
        System.out.println("v: " + v);
        w = random.nextFloat();
        System.out.println("w: " + w);

        u = v + w;
        System.out.println("v + w = " + u);
        u = v - w;
        System.out.println("v - w = " + u);
        u = v / w;
        System.out.println("v / w " + u);
        u = v * w;
        System.out.println("v * w " + u);

        /**
         u: 0.009014167
         v: 0.5968916
         u += v : 0.6059058
         u -= v : 0.009014189
         u *= v : 0.0053804936
         u /= v : 0.009014189
         */

        System.out.println("u: " + u);
        System.out.println("v: " + v);
        u += v;
        System.out.println("u += v : " + u);// 会自动截断 0.605905767 -> 0.6059058
        u -= v;
        System.out.println("u -= v : " + u);// 0.0090142 -> 0.009014189
        u *= v;
        System.out.println("u *= v : " + u);
        u /= v;
        System.out.println("u /= v : " + u);

        char a = +1;
        a = 0+1;// ok
        a = +1;// ok

        //a = 0-1;// incompatible types
        //a = -1;// incompatible types
        long b = 1;
        b = +1;
        b = -1;

    }

}
