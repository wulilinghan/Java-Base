package top.b0x0.designpattern.policy;

/**
 * Created By gao_e on 2020/5/6 16:05
 */
public class ClientTest {


    private static OrderService orderService = new OrderService();
    // 模拟的controller
    public static void main(String[] args) {
        // 接收一个参数 —— 支付方式(支付策略)
        Integer payType = 2;
//        Pay pay = payMap.get(payType);
//        orderService.setPay(pay);
        orderService.execurePay(payType, new Order());
    }
}
