package top.b0x0.design_pattern.policy.simple;

/**
 * @author ManJiis
 * @since 2020/9/13 19:21
 * @Description: // TODO
 **/
public class OrderService {
    public void pay(String payType, Order order){
        if("1".equals(payType)){
            // 微信支付代码
            wechatPay(order);
            return;
        }
        if("2".equals(payType)){
            // 支付宝支付
            aliPay(order);
            return;
        }
        throw new IllegalArgumentException();
    }
    private void wechatPay(Order order){

    }
    private void aliPay(Order order){

    }
}
class Order{
    // id
    // money
}
