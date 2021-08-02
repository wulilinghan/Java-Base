package designpattern.proxy.staticproxy;

/**
 * @author ManJiis
 * @since 2020/6/26 17:02
 **/
public class Client {
    public static void main(String[] args) {
        // 委托人
        OrderService orderServiceReal = new OrderService();
        // 代理人
//        IOrderService orderService = new OrderServiceStaticProxy(orderServiceReal);
        IOrderService orderService = new OrderServiceStaticProxy(/*orderServiceReal*/);
        orderService.addOrder(100, 2000000);
    }
}
