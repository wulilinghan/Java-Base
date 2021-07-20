package designpattern.observer;

public class ObserverTest {
    public static void main(String[] args) {
        BeautifulGirl girl = new BeautifulGirl();//被大家观察的目标
        new Boyfriend(girl);
        new Brother(girl);
        new Father(girl);
        girl.setFallIll(true);
    }
}
