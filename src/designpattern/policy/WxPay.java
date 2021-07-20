package designpattern.policy;

/**
 * Created By gao_e on 2020/5/6 15:57
 */
public class WxPay implements Pay{
    @Override
    public void pay(Integer orderId, Integer money) {
        // 访问的接口？ wx
    }
}
