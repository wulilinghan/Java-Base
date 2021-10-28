package top.b0x0.design_pattern.single.simpleFactory;

/**
 * @author ManJiis
 * @since 2020/9/6 20:11
 * @Description: // 黑白打印机
 **/
public class BlackWhitePrinter implements Printer {
    @Override
    public void print() {
        System.out.println("我打印的是黑白的");
    }
}
