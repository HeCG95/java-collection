package cn.rumoss.study.ClassFileDeep031;

public class C extends D {

    private String value;

    public static void main(String[] args) {

        C c = new C();
        c.setValue("father value");
        c.value = "son value";
        System.out.println(c.getValue());
        D d = c;// actually equals super in a class
        d.setValue("refer D changed value");
        System.out.println(c.getValue());

    }

}

class D {
    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
