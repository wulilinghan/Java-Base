package top.b0x0.design_pattern.policy.simple;

/**
 * @author ManJiis
 * @since 2020/9/13 19:32
 * @Description: // TODO
 **/

public class AliPay implements Pay {
    @Override
    public void pay(Order order) {
        System.out.println("使用支付宝支付 支付订单");
    }
}
