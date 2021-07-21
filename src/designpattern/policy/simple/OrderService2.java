package designpattern.policy.simple;

/**
 * @author ManJiis
 * @since 2020/9/13 19:33
 * @Description: // TODO
 **/
public class OrderService2 {
    public void payOrder(Pay pay, Order order){
        pay.pay(order);
    }
}
