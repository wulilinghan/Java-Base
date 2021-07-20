package designpattern.proxy.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * @Author G_Y
 * @Date 2020/9/11 19:44
 * @Description: // TODO
 **/
public class Client {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        // 委托对象
        Subject subject = new Subject();
        // 构建 代理增强器 对象
        MyInvocationHandler h = new MyInvocationHandler(subject);
        // 创建动态代理对象
        ISubject subjectProxy = (ISubject)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{ISubject.class}, h);
        subjectProxy.sayHello("GY");
    }
}
