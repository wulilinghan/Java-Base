package designpattern.policy.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author G_Y
 * @Date 2020/9/13 19:34
 * @Description: // TODO
 **/
public class Controller {
    /*static Map<String,Class<? extends Pay>> map = new HashMap<>();
    static {
        map.put("1", WechatPay.class);
        map.put("2", AliPay.class);
    }*/
    static Map<String, Pay> map = new HashMap<>();
    static {
        map.put("1", new WechatPay());
        map.put("2", new AliPay());
//        map.put("3", new ***Pay());
    }
    public static void main(String[] args) {
        Order order = new Order();
        OrderService2 orderService2 = new OrderService2();
        // 前端会传入一个对应的 payType
        String payType = "1";
        Pay pay = map.get(payType);
        if(pay == null)
            throw new IllegalArgumentException();
//        Class<? extends Pay> aClass = map.get(payType);
        // BeanUtils 传入Class对象 从 ioc 根据类型 获取到对应的bean
        orderService2.payOrder(pay, order);
    }
}
