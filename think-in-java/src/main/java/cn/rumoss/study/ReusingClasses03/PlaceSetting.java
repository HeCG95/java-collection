package cn.rumoss.study.ReusingClasses03;

public class PlaceSetting extends Custom {

    private PrintSon ps = new PrintSon();
//    private Spoon sp;
    private Spoon sp = new Spoon(2);
//private static Spoon ssp = new Spoon(2);
    private Fork frk;
    private Knife kn;
    private DinnerPlate pl;

    /*{
        sp = new Spoon(2);
    }*/

    PlaceSetting(int i) {
        super(i + 1);
        System.out.println("Begin constructor init");
        sp = new Spoon(i + 2);
        frk = new Fork(i + 3);
        kn = new Knife(i + 4);
        pl = new DinnerPlate(i + 5);
        System.out.println("PlaceSetting constructor");
    }

    {
        System.out.println("Before PlaceSetting constructor");
    }

    //static PrintSon ps = new PrintSon();
    static {
        //System.out.println(">>>>>>>>> Son static block");
    }

    public static void main(String[] args) {

        PlaceSetting x = new PlaceSetting(9);

    }
}

class Plate {
    Plate(int i) {
        System.out.println("Plate constructor");
    }
}

class DinnerPlate extends Plate {
    DinnerPlate(int i) {
        super(i);
        System.out.println("DinnerPlate constructor");
    }
}

class Utensil {
    Utensil(int i) {
        System.out.println("Utensil constructor");
    }
}

class Spoon extends Utensil {
    Spoon(int i) {
        super(i);
        System.out.println("Spoon constructor");
    }
}

class Fork extends Utensil {
    Fork(int i) {
        super(i);
        System.out.println("Fork constructor");
    }
}

class Knife extends Utensil {
    Knife(int i) {
        super(i);
        System.out.println("Knife constructor");
    }
}

// A cultural way of doing something:
class Custom {
    private Print p = new Print();
    static {
        //System.out.println(">>>>>>>>> Father static block");
    }
    //static Print ps = new Print();
    Custom(int i) {
        System.out.println("Custom constructor");
    }
    {
        System.out.println("Before Custom constructor");
    }
}

class Print{
    Print(){
        System.out.println(">>>>>>>>> Print constructor");
    }
}

class PrintSon{
    PrintSon(){
        System.out.println(">>>>>>>>> PrintSon constructor");
    }
}
