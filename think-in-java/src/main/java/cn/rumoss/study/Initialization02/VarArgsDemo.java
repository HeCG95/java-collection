package cn.rumoss.study.Initialization02;

public class VarArgsDemo {

    static void printArray(Object... args) {
        System.out.println(args.length);
        for(Object obj : args)
            System.out.print(obj + " ");
        System.out.println();
    }

    public static void main(String... args) {

        // Can take individual elements:
        printArray(new Integer(47), new Float(3.14),
                new Double(11.11));
        printArray(47, 3.14F, 11.11);
        printArray("one", "two", "three");
        printArray(new A(), new A(), new A());
        // Or an array:
        printArray((Object[])new Integer[]{ 1, 2, 3, 4 });
        printArray(); // Empty list is OK

    }

}

class A{}
