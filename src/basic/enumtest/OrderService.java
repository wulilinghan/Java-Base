package basic.enumtest;

import java.util.List;

public class OrderService {
    // 根据订单状态查询订单数据集合
    // 订单状态 在 数据库表中  1:待支付，2：已支付，3：已退款，4：支付超时....

    public List<Order> getOrdersBy(Integer status) {
        // 比如说这个地方，调用这个方法的人，它把status传入0
        // 我们还有必要去查询数据库吗?
        // 查询数据库
        // 如果不写额外的逻辑判断，这里就会执行一次 无效的查询
        return null;
    }


    public List<Order> getOrderByStatus(EOrderStatus eOrderStatus) {

        eOrderStatus.f();

        Integer status = EOrderStatus.PLACE_ORDER.status();

        // 访问 mysql mapper 接受的是一个 int
        return null;
    }

}
class Order {}