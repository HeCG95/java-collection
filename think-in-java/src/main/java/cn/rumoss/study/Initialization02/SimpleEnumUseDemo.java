package cn.rumoss.study.Initialization02;

public class SimpleEnumUseDemo {

    public static void main(String[] args) {

        Spiciness howHot = Spiciness.MEDIUM;
        System.out.println(howHot);
        howHot = Spiciness.NOT;
        System.out.println(howHot);

        Spiciness hot = Enum.valueOf(Spiciness.class, "FLAMING");
        System.out.println(hot);

        Spiciness[] values = Spiciness.values();
        System.out.println(values);

        Spiciness hot2 = Enum.valueOf(Spiciness.class, "FLAMING");
        System.out.println(hot==hot2);
        System.out.println("hot -> "+hot);
        hot2.setAbbr("NF");// change hot2, actually it will effect hot's abbr
        System.out.println("hot -> "+hot);

        switch (howHot){
            case NOT:
                System.out.println("Not spicy at all.");
                break;
            case MILD:
            case MEDIUM:
                System.out.println("A little hot.");
                break;
            case HOT:
            case FLAMING:
            default:
                System.out.println("Maybe too hot.");
        }

    }

}
