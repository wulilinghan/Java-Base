package designpattern.single.simpleFactory;

/**
 * @Author G_Y
 * @Date 2020/9/6 20:13
 * @Description: // TODO
 **/
public class Client {

    public static void main(String[] args) {
        /*Printer printer = PrinterFactory.getPrinter("1");
        printer.print();*/
        IPrinterFactory factory = BlackWhitePrinterFactory.getInstance();
        factory.getPrinter().print();

    }
}
