package cn.rumoss.study.Operators01.practice;

public class BitwisePrivilegeControl {

    public static int DELETE_A = 1 << 0;
    public static int MODIFY_A = 1 << 1;
    public static int ADD_A = 1 << 2;
    public static int DELETE_B = 1 << 3;
    public static int MODIFY_B = 1 << 4;
    public static int ADD_B = 1 << 5;

    public static int addPrivilege(int old, int more){
        return old | more;
    }

    public static int removePrivilege(int old, int less){
        return old & ~less;
    }

    public static boolean hasPrivilege(int cur, int prv){
        return (cur&prv)==prv;
    }

    public static void main(String[] args) {

        int p = DELETE_A;
        System.out.println(Integer.toBinaryString(p));
        p = addPrivilege(p, MODIFY_A);
        System.out.println(Integer.toBinaryString(p));
        p = addPrivilege(p, ADD_A);
        System.out.println(Integer.toBinaryString(p));
        System.out.println(hasPrivilege(p, ADD_A));
        p = removePrivilege(p, DELETE_A);
        System.out.println(hasPrivilege(p, DELETE_A));
        System.out.println(Integer.toBinaryString(p));
        p = addPrivilege(p, DELETE_B);
        p = addPrivilege(p, MODIFY_B);
        System.out.println(hasPrivilege(p, DELETE_B));
        System.out.println(hasPrivilege(p, MODIFY_B));
        System.out.println(hasPrivilege(p, ADD_B));
        System.out.println(Integer.toBinaryString(p));
        p = addPrivilege(p, ADD_B);
        System.out.println(hasPrivilege(p, ADD_B));
        System.out.println(Integer.toBinaryString(p));
        System.out.println("**********************");
        System.out.println(hasPrivilege(p,ADD_A|ADD_B));
        System.out.println(hasPrivilege(p,DELETE_A|ADD_B));
        System.out.println(hasPrivilege(p,ADD_A|MODIFY_A|DELETE_B|ADD_B));
        System.out.println(hasPrivilege(p,ADD_A|MODIFY_A|DELETE_B|MODIFY_B|ADD_B));

    }

}
