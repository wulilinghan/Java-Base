package designpattern.proxy.staticproxy;

/**
 * @Author G_Y
 * @Date 2020/6/26 17:02
 * @Description: // TODO
 **/
public class Client {
    public static void main(String[] args) {
//        OrderService orderServiceReal = new OrderService();// 委托人
        IOrderService orderService = new OrderServiceStaticProxy(/*orderServiceReal*/);// 代理人
        orderService.addOrder(100,2000000);
    }
}
