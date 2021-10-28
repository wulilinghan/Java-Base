package top.b0x0.design_pattern.observer;

public class Brother extends Concerner {
    public Brother(BeautifulGirl girl) {
        super(girl);
    }
    @Override
    public void care() {
        boolean fallIll = this.girl.isFallIll();
        if (fallIll)
            System.out.println("哥哥，我生病了");
        System.out.println("要紧么？赶紧去医院看病呀！我先给你转1000块钱过去呀~");
        System.out.println("--------------------");
    }
}
