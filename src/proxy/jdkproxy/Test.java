package proxy.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * @program: java-base->Test
 * @description: test
 * @author: G_Y
 * @create: 2019-08-19 18:52
 **/
public class Test {

    public static void main(String[] args) {
        // 被代理对象
        UserService userService = new UserServiceImpl();
        //
        MyInvocationHandler h = new MyInvocationHandler(userService);

        UserService instance = (UserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                userService.getClass().getInterfaces(), h);
//        instance.getUserNameById(1);
        System.out.println(instance);

//        System.out.println(instance.getUserNameById(1222));
//        System.out.println(instance == h.proxyObj);

//        UserService serviceProxy = new UsersServiceProxy(h);
//        serviceProxy.getUserNameById(11);
//        System.out.println(serviceProxy);

//        new proxy.jdkproxy.protectedtest.UserService("1111");
    }
}
class SubUserService extends proxy.jdkproxy.protectedtest.UserService {

    protected SubUserService(String name) {
        super(name);
    }
}
