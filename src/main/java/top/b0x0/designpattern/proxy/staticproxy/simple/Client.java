package top.b0x0.designpattern.proxy.staticproxy.simple;

/**
 * 客户端 测试
 *
 * @author ManJiis
 * @since 2020/9/11 18:51
 **/
public class Client {
    public static void main(String[] args) {
//        IUserService userService = new UserService();// 委托对象
//        IUserService userServiceProxy1 = new UserServiceProxy1(userService);
//        userServiceProxy1.regist("zhangsan","123456");
        UserService userServiceProxy2 = new UserServiceProxy2();
        userServiceProxy2.regist("zhangsan", "123456");
    }
}
