package designpattern.proxy.staticproxy;

/**
 * @author ManJiis
 * @since 2020/6/26 16:51
 **/
public interface IOrderService {
    public Order addOrder(Integer userId, Integer money);
}
