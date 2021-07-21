package designpattern.single.simpleFactory;

/**
 * @author ManJiis
 * @since 2020/9/6 20:26
 * @Description: // 黑白打印机工厂
 **/
public class BlackWhitePrinterFactory implements IPrinterFactory {
    @Override
    public Printer getPrinter() {
        return new BlackWhitePrinter();
    }
    private BlackWhitePrinterFactory(){}
    private static IPrinterFactory blackWhitePrinterFactory = new BlackWhitePrinterFactory();
    public static IPrinterFactory getInstance(){
        return blackWhitePrinterFactory;
    }
}
