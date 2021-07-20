package proxy.jdkproxy.protectedtest;

import proxy.jdkproxy.ISubject;
import proxy.jdkproxy.ProxyInstanceFactory;
import proxy.jdkproxy.RealSubject;

/**
 * Created By gao_e on 2020/3/16 16:44
 * 使用动态代理的客户端
 */
public class Client1 {
    public static void main(String[] args) {

        // 1、有委托者
        ISubject realSubject = new RealSubject();
        // 使用动态生成代理对象来做具体的业务处理
        ISubject proxyInstance = ProxyInstanceFactory.getProxyInstanceBy(realSubject, ISubject.class);
        proxyInstance.doSomething();
        System.out.println("==================================");

        // 2、没有委托者，直接根据接口实现获取代理对象
        ISubject proxySelfLogicInstance = ProxyInstanceFactory.getProxyInstanceBy(ISubject.class);
        proxySelfLogicInstance.doSomething();
    }
}
