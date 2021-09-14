package top.b0x0.designpattern.observer;

public class Boyfriend extends Concerner {
    public Boyfriend(BeautifulGirl girl) {
        super(girl);
    }
    @Override
    public void care() {
        boolean fallIll = this.girl.isFallIll();
        if (fallIll)
            System.out.println("亲爱的，我生病了");
        System.out.println("多喝水呀！宝贝~");
        System.out.println("--------------------");
    }
}
