package designpattern.policy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By gao_e on 2020/5/6 16:00
 */
public class OrderService {
//    private Pay pay;
    static Map<Integer,Pay> payMap = new HashMap<>();
    static {
        payMap.put(1,new WxPay());
        payMap.put(2,new ZFBPay());
        payMap.put(3,new OtherPay());
    }
    public OrderService() {
    }

//    public Pay getPay() {
//        return pay;
//    }
//
//    public void setPay(Pay pay) {
//        this.pay = pay;
//    }

    public void execurePay(Integer payType,Order order) {

//        if(payType.equals(1)){
//            // 微信支付方法
//        }
        //

        payMap.get(payType).pay(order.getOrderId(), order.getMoney());
    }
    // 微信支付方法

    // 支付宝支付

    // 其它的支付方式支付
}

class Order{
    private Integer orderId;
    private Integer money;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
