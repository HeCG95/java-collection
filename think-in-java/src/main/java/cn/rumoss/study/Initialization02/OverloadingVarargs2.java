package cn.rumoss.study.Initialization02;

public class OverloadingVarargs2 {

    static void f(float i, Character... args) {
        System.out.println("first");
    }
    static void f(Character... args) {
        System.out.print("second");
    }

    public static void main(String[] args) {

        /*
        Error:(20, 9) java: 对f的引用不明确
        cn.rumoss.study.Initialization02.OverloadingVarargs2 中的方法 f(float,java.lang.Character...) 和
        cn.rumoss.study.Initialization02.OverloadingVarargs2 中的方法 f(java.lang.Character...) 都匹配
         */
        f(1, 'a');
        //f('a', 'b');// error here

    }

}
