package cn.rumoss.study.Initialization02;

public class FinalizeUseDemo {

    public static void main(String[] args) {

        Obj obj = new Obj(true);
        obj.setFlagFalse();// Proper cleanup, can do clean-up ops

        new Obj(true);// Forget to clean up, then will throw a error in finalize()
        System.gc();

    }

}

class Obj{
    boolean flag = true;// Indicate the one obj if already do clean-up work
    Obj(boolean flag){
        this.flag = flag;
    }
    void setFlagFalse(){
        this.flag = false;
    }

    @Override
    protected void finalize() throws Throwable {
        if(flag){
            System.out.println("Error: flag is true");
            super.finalize();
        }
    }
}
