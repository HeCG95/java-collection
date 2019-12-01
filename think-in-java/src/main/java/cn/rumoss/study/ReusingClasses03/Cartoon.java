package cn.rumoss.study.ReusingClasses03;

public class Cartoon extends Drawing {
    Cartoon() {
        System.out.println("Cartoon Constructor");
    }

    public static void main(String[] args) {
//        new Cartoon();
        Cartoon a = new Cartoon();
    }
}

class Drawing extends Art {
    Drawing(){
        System.out.println("Drawing Constructor");
    }
}

class Art {
    Art(){
        System.out.println("Art Constructor");
    }
}
