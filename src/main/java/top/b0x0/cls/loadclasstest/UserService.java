package top.b0x0.cls.loadclasstest;

import java.util.ArrayList;

public class UserService {
    static {
        System.out.println("UserService 静态代码块被执行");
        OrderService.names = new ArrayList<>();
        OrderService.names.add("ManJiis");
        OrderService.names.add("TLX");
    }
}
