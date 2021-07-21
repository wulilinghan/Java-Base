package designpattern.proxy.staticproxy;

import java.util.concurrent.TimeUnit;

/**
 * @author ManJiis
 * @since 2020/6/26 16:44
 * @Description: // TODO
 **/
public class OrderService implements  IOrderService {

    public Order addOrder(Integer userId, Integer money) {
        // 打印参数

        // 下单逻辑略....
        System.out.println("下单逻辑");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 打印结果
        // 耗时
        return new Order(1,money,userId);
    }
}
class Order {
    public Order(Integer id, Integer money, Integer userId) {
        this.id = id;
        this.money = money;
        this.userId = userId;
    }
    private Integer id;
    private Integer money;
    private Integer userId;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", money=").append(money);
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }
}