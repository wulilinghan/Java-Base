package designpattern.policy.simple;

/**
 * @Author G_Y
 * @Date 2020/9/13 19:32
 * @Description: // TODO
 **/

public class AliPay implements Pay {
    @Override
    public void pay(Order order) {
        System.out.println("使用支付宝支付 支付订单");
    }
}
