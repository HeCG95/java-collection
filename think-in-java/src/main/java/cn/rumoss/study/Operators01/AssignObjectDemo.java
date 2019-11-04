package cn.rumoss.study.Operators01;

import lombok.Data;

public class AssignObjectDemo {

    public static void main(String[] args) {

        ObjectValue a = new ObjectValue();
        a.setValue(5);
        System.out.println("a: " + a);
        ObjectValue b = new ObjectValue();
        b.setValue(55);
        System.out.println("b: " + b);

        a = b;
        System.out.println("a: " + a);
        a.setValue(555);
        System.out.println("b: " + b);

        b = new ObjectValue();
        b.setValue(5555);
        a.value = b.value;// different
        b.setValue(55555);
        System.out.println("a: " + a);
        System.out.println("b: " + b);


    }

}

@Data
class ObjectValue {
    int value;
}
