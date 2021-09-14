package top.b0x0.designpattern.observer;

public class ObserverTest {
    public static void main(String[] args) {
        //被大家观察的目标
        BeautifulGirl girl = new BeautifulGirl();
        new Boyfriend(girl);
        new Brother(girl);
        new Father(girl);
        girl.setFallIll(true);
    }
}
