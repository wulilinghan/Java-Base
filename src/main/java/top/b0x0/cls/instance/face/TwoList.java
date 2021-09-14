package top.b0x0.cls.instance.face;

import java.util.Arrays;
import java.util.List;

public class TwoList {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(new User(1, "张三", "18877777777"),
                new User(2, "李四", "18866666666"));
        List<Order> orders = Arrays.asList(new Order(1, 10000, 1, null, null),
                new Order(2, 20000, 1, null, null), new Order(3, 100000, 1, null, null));
        // 根据users数据,将orders中的用户姓名、电话补齐 手写代码
    }
}
class User {
    private Integer userId;
    private String userName;
    private String userPhone;
    public User(Integer userId, String userName, String userPhone) {
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
    }
    public User() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}

class Order {
    private Integer orderId;
    private Integer orderPrice;
    private Integer userId;
    private String userName;
    private String userPhone;
    public Order(Integer orderId, Integer orderPrice, Integer userId, String userName,
            String userPhone) {
        this.orderId = orderId;
        this.orderPrice = orderPrice;
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
    }

    public Order() {
        super();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }
}
