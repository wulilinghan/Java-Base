package top.b0x0.design_pattern.observer;

public class Father extends Concerner {
    public Father(BeautifulGirl girl) {
        super(girl);
    }
    @Override
    public void care() {
        boolean fallIll = this.girl.isFallIll();
        if (fallIll)
            System.out.println("爸爸，我生病了");
        System.out.println("你现在在哪？我马上赶过来！");
        System.out.println("--------------------");
    }
}
