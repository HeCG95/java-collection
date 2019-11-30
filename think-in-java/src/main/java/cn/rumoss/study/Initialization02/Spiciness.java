package cn.rumoss.study.Initialization02;

public enum Spiciness {

    NOT("N"), MILD("M"), MEDIUM, HOT, FLAMING("F");

    private String abbr;

    private Spiciness(){
    }

    private Spiciness(String abbr){
        this.abbr = abbr;
    }

    @Override
    public String toString() {
//        return "Spicy: "+super.toString();
        if(abbr==null)
            return "Spicy: "+super.toString();
        else
            return "Spicy: "+super.toString()+" abbr: "+getAbbr();
    }

    public String getAbbr(){
        return this.abbr;
    }

    public void setAbbr(String abbr){
        this.abbr = abbr;
    }

}
