package designpattern.single.simpleFactory;

/**
 * @author ManJiis
 * @since 2020/9/6 20:14
 * @Description: // 静态工厂类
 **/
public class PrinterFactory {
    public static Printer getPrinter(String type) {
        // 公共逻辑
        if("1".equals(type)){
            return new BlackWhitePrinter();
        }
        if("2".equals(type)){
            return new ColorfulPrinter();
        }
        //
        throw new IllegalArgumentException();
    }
}
