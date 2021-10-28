package top.b0x0.design_pattern.proxy.staticproxy;

/**
 * 代理类
 *
 * @author ManJiis
 * @since 2020/6/26 16:49
 **/
public class OrderServiceStaticProxy extends OrderService {

    // 委托者
//    private OrderService orderService;

//    public OrderServiceStaticProxy(OrderService orderService) {
//        this.orderService = orderService;
//    }

    @Override
    public Order addOrder(Integer userId, Integer money) {
        // 前置增强 打印参数
        System.out.println(userId + "," + money);
        long time = System.currentTimeMillis();

        Order order = null;
        try {
            order = super.addOrder(userId, money);
        } catch (Exception e) {
            // 这里的逻辑，就是 异常 增强 操作
        }

        // 后置增强
        System.out.println(System.currentTimeMillis() - time);
        System.out.println(order);
        return order;
    }
}
