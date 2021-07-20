package proxy.cglibproxy.filtertest;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;

/**
 * Created By gao_e on 2020/3/20 10:17
 */
public class ProxyInstanceFactory {
    public static <T> T getProxyInstance(Class<T> cls) {
        // 构建增强器
        Enhancer enhancer = new Enhancer();
        // 需要传入具体的 cglib动态代理回调处理类
        enhancer.setCallback(TransactionalMethodInterceptor.getInstance());
        // 指定被代理的类的类信息对象
        enhancer.setSuperclass(cls);
        // 创建具体的代理类对象实例
        return (T) enhancer.create();
    }

    /**
     * 起不到拦截效果
     * TODO 存在疑问
     */
    public static <T> T getProxyInstance2(Class<T> cls) {
        Enhancer enhancer = new Enhancer();
        //2.设置父类
        enhancer.setSuperclass(cls);
        enhancer.setCallbackType(TransactionalMethodInterceptor.class);
        Class proxyClass = enhancer.createClass();
        Enhancer.registerCallbacks(proxyClass, new Callback[]{TransactionalMethodInterceptor.getInstance()});
        //使用objenesis+cglib构造代理对象
        Objenesis objenesis = new ObjenesisStd(true);
        return (T) objenesis.newInstance(proxyClass);
    }

    public static <T> T getProxyInstance3(Class<T> cls) {
        // 构建增强器
        Enhancer enhancer = new Enhancer();
        // 需要传入具体的 cglib动态代理回调处理类
        enhancer.setCallbacks(new Callback[]{NoOp.INSTANCE, CatchAbleMethodInterceptor.getInstance(),
                TransactionalMethodInterceptor.getInstance()});
        // 指定被代理的类的类信息对象
        enhancer.setSuperclass(cls);
        // 设置方法拦截过滤匹配器
        enhancer.setCallbackFilter(new InterceptFilter());
        // 创建具体的代理类对象实例
        return (T) enhancer.create();
    }

    public static <T> T getProxyInstance4(Class<T> cls) {
        Enhancer enhancer = new Enhancer();
        //2.设置父类
        enhancer.setSuperclass(cls);
        enhancer.setCallbackTypes(new Class[]{NoOptMethodInterceptor.class,
                CatchAbleMethodInterceptor.class, TransactionalMethodInterceptor.class});
        enhancer.setCallbackFilter(new InterceptFilter());
        Class proxyClass = enhancer.createClass();
        Enhancer.registerCallbacks(proxyClass, new Callback[]{NoOptMethodInterceptor.getInstance(),
                CatchAbleMethodInterceptor.getInstance(), TransactionalMethodInterceptor.getInstance()});
        //使用objenesis+cglib构造代理对象
        Objenesis objenesis = new ObjenesisStd(true);
        return (T) objenesis.newInstance(proxyClass);
    }


}
