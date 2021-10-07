package top.b0x0.designpattern.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author ManJiis
 * @since 2020/9/11 19:44
 **/
public class JdkProxyTest {
    /**
     * JDK代理
     * 为什么JDK动态代理只能代理接口，不能直接代理类？
     * <p>
     * 通过分析生成的代理类发现 $Proxy0类继承了Proxy类，同时实现了IUserService接口
     * 到这里，就能解释为什么JDK的动态代理只能基于接口实现，不能基于继承来实现？
     * 因为Java中不支持多继承，而JDK的动态代理在创建代理对象时，默认让代理对象继承了Proxy类，所以JDK只能通过接口去实现动态代理
     * <p>
     * $Proxy0 实现了IUserService接口，所以重写了接口中的方法（$Proxy0 同时还重
     * 写了Object类中的几个方法）。所以当我们调用 sayHello() 方法时，先是调用到$Proxy0.sayHello()方
     * 法，在这个方法中，直接调用了super.h.invoke()方法，父类是Proxy，父类中的h就是我们定义的
     * InvocationHandler，所以这儿会调用到LogInvocationHandler.invoke()方法。因此当我
     * 们通过代理对象去执行目标对象的方法时，会先经过InvocationHandler的invoke()方法，然后在
     * 通过反射method.invoke()去调用目标对象的方法，因此每次都会先调用自定义的方法,打印里面的语句。
     *
     * @param args /
     */
    public static void main(String[] args) {
        // 将代理类输出到磁盘上
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 委托对象
        UserServiceImpl subject = new UserServiceImpl();
        // 构建 代理增强器 对象
        InvocationHandler logHandler = new LogInvocationHandler(subject);
        Class[] interfaces = {IUserService.class};
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

        // 创建动态代理对象
        IUserService userServiceProxy = (IUserService) Proxy.newProxyInstance(
                contextClassLoader,
                interfaces,
                logHandler);
        userServiceProxy.sayHello("manji");
//        userServiceProxy.sayHello("error");
    }
}
