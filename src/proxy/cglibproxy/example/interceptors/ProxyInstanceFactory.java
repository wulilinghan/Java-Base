package proxy.cglibproxy.example.interceptors;

import org.springframework.cglib.proxy.Enhancer;

/**
 * Created By gao_e on 2020/3/18 10:39
 */
public class ProxyInstanceFactory {
    public static <T> T getProxyInstance(Class<T> cls) {
        Enhancer enhancer = new Enhancer();
        // 需要传入具体的 cglib动态代理回调处理类
        enhancer.setCallback(MyMethodInterceptor.getInstance());
        // 指定被代理的类的类信息对象
        enhancer.setSuperclass(cls);
        // 创建具体的代理类对象实例
        return (T) enhancer.create();
    }
    public static <T> T getMethodInterceptor1ProxyInstance(Class<T> cls) {
        Enhancer enhancer = new Enhancer();
        // 需要传入具体的 cglib动态代理回调处理类
        enhancer.setCallback(MyMethodInterceptor1.getInstance());
        // 指定被代理的类的类信息对象
        enhancer.setSuperclass(cls);
        // 创建具体的代理类对象实例
        return (T) enhancer.create();
    }
    public static <T> T getMethodInterceptor2ProxyInstance(Class<T> cls) {
        Enhancer enhancer = new Enhancer();
        // 需要传入具体的 cglib动态代理回调处理类
        enhancer.setCallback(MyMethodInterceptor2.getInstance());
        // 指定被代理的类的类信息对象
        enhancer.setSuperclass(cls);
        // 创建具体的代理类对象实例
        return (T) enhancer.create();
    }
}
