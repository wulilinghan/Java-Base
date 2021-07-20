package proxy.cglibproxy.cglibf1f2test;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;

/**
 * Created By gao_e on 2020/3/20 10:17
 */
public class SimpleProxyInstanceFactory {
    public static <T> T getProxyInstance(Class<T> cls) {
        // 构建增强器
        Enhancer enhancer = new Enhancer();
        // 需要传入具体的 cglib动态代理回调处理类
        enhancer.setCallback(SimpleMethodInterceptor.getInstance());
        // 指定被代理的类的类信息对象
        enhancer.setSuperclass(cls);
        // 创建具体的代理类对象实例
        return (T) enhancer.create();
    }

    /**
     * 起不到拦截效果
     * TODO 存在疑问
     * Enhancer.registerCallbacks 已经解决
     */
    public static <T> T getProxyInstance2(Class<T> cls) {
        Enhancer enhancer = new Enhancer();
        //2.设置父类
        enhancer.setSuperclass(cls);
        // 设置拦截回调类型
        enhancer.setCallbackType(SimpleMethodInterceptor.class);
        // 创建代理类Class
        Class proxyClass = enhancer.createClass();
        // 给类中注册拦截回调
        Enhancer.registerCallbacks(proxyClass,
                new Callback[]{SimpleMethodInterceptor.getInstance()});
        // 针对构造不做拦截
        enhancer.setInterceptDuringConstruction(false);
        //使用objenesis+cglib构造代理对象
        Objenesis objenesis = new ObjenesisStd(true);
        return (T) objenesis.newInstance(proxyClass);
    }
}
