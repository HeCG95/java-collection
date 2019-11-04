package cn.rumoss.study.Operators01;

import lombok.Data;

public class AssignPassObjectDemo {

    static void change(CharObject charObject) {
        charObject.c = 'Z';
    }

    public static void main(String[] args) {

        CharObject a = new CharObject();
        a.c = 'A';
        System.out.println("1: a.c = "+a.c);
        change(a);// reference
        System.out.println("2: a.c = "+a.c);

    }

}

@Data
class CharObject {
    char c;
}