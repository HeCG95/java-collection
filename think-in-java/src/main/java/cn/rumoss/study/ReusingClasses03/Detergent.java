package cn.rumoss.study.ReusingClasses03;

public class Detergent extends Cleanser {

    String value = "son value";

    public Detergent() {
        super(null);
        System.out.println("I am son - Detergent");
    }

    public Detergent(String s){
        super(null);
    }

    public void scrub() {
        //super.scrub();
        append(" Detergent.scrub()");
        super.scrub(); // Call base-class version
    }

    public void foam() { append(" foam()"); }

    public void father(){
        super.value = "value changed by son";
        value = "value son init";
        //System.out.println("Son value: "+value);
        //System.out.println("Super value: "+super.value);
        System.out.println(this.value == super.value);
    }

    public static void main(String[] args) {

        Detergent x = new Detergent();
        x.dilute();
        x.apply();
        x.scrub();
        x.foam();
        System.out.println(x);

        // test son call father obj
        x.father();

        System.out.println("Testing base class:");
        Cleanser.main(args);

    }

}

class Cleanser {
    private String s = "Cleanser";
    String value = null;

    public Cleanser(String s){
        System.out.println("I am father - Cleanser");
        value = "Value";
        System.out.println("value has been init in father class");
    }

    public void append(String a) { s += a; }
    public void dilute() { append(" dilute()"); }
    public void apply() { append(" apply()"); }
    public void scrub() { append(" scrub()"); }
    public String toString() { return s; }

    public static void main(String[] args) {
        Cleanser x = new Cleanser(null);
        x.dilute(); x.apply(); x.scrub();
        System.out.println(x);
    }
}
