package cls.loadclasstest;

import java.util.ArrayList;

public class UserService {
    static {
        System.out.println("UserService 静态代码块被执行");
        OrderService.names = new ArrayList<>();
        OrderService.names.add("GY");
        OrderService.names.add("TLX");
    }
}
