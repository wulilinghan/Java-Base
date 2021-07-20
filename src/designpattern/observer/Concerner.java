package designpattern.observer;

public abstract class Concerner {
    protected BeautifulGirl girl;
    @SuppressWarnings("unused")
    private Concerner() {}
    protected Concerner(BeautifulGirl girl) {
        this.girl = girl;
        this.girl.addRelatives(this);
        System.out.println(this.getClass().getName());
    }
    //关心方法
    public abstract void care();
}
