package proxy.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * Created By gao_e on 2020/3/16 15:52
 * 获取代理对象的工厂
 */
public class ProxyInstanceFactory {
    /**
     * 有委托者
     * 根据委托者获取代理对象
     */
    public static <T> T getProxyInstanceBy(Object realObject, Class<? extends T> cls) {
        // 参数校验
        if(realObject == null || cls == null || !cls.isInterface())
            throw new IllegalArgumentException("realObject must implements a interface");
        // 构建获取代理对象
        T proxyInstance = (T) Proxy.newProxyInstance(ProxyInstanceFactory.class.getClassLoader(),
                new Class[]{cls}, new MyInvocationHandler(realObject));
        return proxyInstance;
    }
    /**
     * 没有委托者
     * 根据接口类获取这个接口的代理实现类对象
     */
    public static <T> T getProxyInstanceBy(Class<? extends T> cls) {
        // 参数校验
        if(cls == null || !cls.isInterface())
            throw new IllegalArgumentException("cls need interface");
        // 构建获取接口代理实现类对象
        T proxyInstance = (T) Proxy.newProxyInstance(ProxyInstanceFactory.class.getClassLoader(),
                new Class[]{cls}, MyInvocationHandler2.getInvocationHandler());
        return proxyInstance;
    }
}
