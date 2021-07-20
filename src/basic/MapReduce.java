package basic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MapReduce {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User().setName("gaoyue").setId(1));
        users.add(new User().setName("tlx").setId(2));

        List<Integer> ids =
                users.stream().map(User::getId).collect(Collectors.toList());
        ids.forEach(System.out::println);
        // 调用服务 传入 List<Integer> userIds, 返回 Map<userId,UserInfo> map
        // 调用服务 传入 List<Integer> productIds, 返回 Map<productIds,ProductInfo> map
        List<Order> orders;
        List<OrderListVO> vos;
    }
}

class OrderListVO {
    Integer orderId;
    Integer userId;
    Integer productId;
    String userName;
    double price;
    String productName;
    // ...
}
class Product {
    Integer productId;
    String productName;
}
class Order {
    Integer orderId;
    double price;
    Integer productId;
    Integer userId;
}
class User {
    Integer id;
    String name;
    public Integer getId() {
        return id;
    }
    public User setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }
    public User setName(String name) {
        this.name = name;
        return this;
    }
}
