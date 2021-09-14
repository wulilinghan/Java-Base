package top.b0x0.designpattern.single.simpleFactory;

/**
 * @author ManJiis
 * @since 2020/9/6 20:28
 * @Description: // 彩色打印机工厂
 **/
public class ColorfulPrinterFactory implements IPrinterFactory {
    @Override
    public Printer getPrinter() {
        return new ColorfulPrinter();
    }
    private ColorfulPrinterFactory(){}
    private static IPrinterFactory iPrinterFactory = new ColorfulPrinterFactory();
    public static IPrinterFactory getInstance(){
        return iPrinterFactory;
    }
}
