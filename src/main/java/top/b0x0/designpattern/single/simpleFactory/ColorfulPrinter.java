package top.b0x0.designpattern.single.simpleFactory;

/**
 * @author ManJiis
 * @since 2020/9/6 20:12
 * @Description: // 彩色打印机
 **/
public class ColorfulPrinter implements Printer {
    @Override
    public void print() {
        System.out.println("我打印的是彩色的");
    }
}
