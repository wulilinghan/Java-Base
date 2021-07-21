package designpattern.policy.simple;

/**
 * @author ManJiis
 * @since 2020/9/13 19:32
 * @Description: // TODO
 **/

public class WechatPay implements Pay {

    @Override
    public void pay(Order order) {
        //
        System.out.println("使用微信支付 支付订单");
    }
}
