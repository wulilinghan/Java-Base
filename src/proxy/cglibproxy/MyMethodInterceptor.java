package proxy.cglibproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program: java-base->MyMethodInterceptor
 * @description: cglib动态代理回调处理类
 * @author: G_Y
 * @create: 2019-08-21 17:58
 * 跟jdk动态代理最大的区别是，cglib代理没有被代理对象
 * 所有执行方法的对象，都是被代理类的子类的对象
 **/
public class MyMethodInterceptor implements MethodInterceptor {
    private MyMethodInterceptor(){}
    private static MyMethodInterceptor myMethodInterceptor = new MyMethodInterceptor();
    /**
     * 具体代理过程执行得回调方法
     * proxyInstance:代理对象
     * method:被代理的原方法对象
     * args:方法执行的参数值
     * methodProxy:进一步封装后的代理方法对象
     */
    @Override
    public Object intercept(Object proxyInstance,
                            Method method, Object[] args,
                            MethodProxy methodProxy) throws Throwable {
//        System.out.println(method.getName());
//        System.out.println(methodProxy.getSuperName());
//        System.out.println("----------------------------");
        if(method.getName().equals("sayAge")) {
            Object invoke = methodProxy.invokeSuper(proxyInstance, args);
            return invoke;
        }
        System.out.println("before");
        // 这里实则是用代理对象执行代理方法，也就是父类的方法，也就是被代理类的方法
//        Object invoke = methodProxy.invokeSuper(proxyInstance, args);
        Object result = null;
        System.out.println("after");
        return result;
//        return invoke;
    }

    /**
     * @param cls 被代理类
     * @param <T> 被代理类的类型
     * @return 获取代理对象
     */
    public static <T> T getProxyInstance(Class<T> cls) {
        Enhancer enhancer = new Enhancer();
        // 需要传入具体的 cglib动态代理回调处理类
        enhancer.setCallback(myMethodInterceptor);
        // 指定被代理的类的类信息对象
        enhancer.setSuperclass(cls);
        // 创建具体的代理类对象实例
        return (T) enhancer.create();
    }
}
