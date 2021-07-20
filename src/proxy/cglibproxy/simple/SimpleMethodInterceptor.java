package proxy.cglibproxy.simple;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created By gao_e on 2020/3/20 10:17
 */
public class SimpleMethodInterceptor implements MethodInterceptor {
    private SimpleMethodInterceptor() {
    }
    private static SimpleMethodInterceptor myMethodInterceptor = new SimpleMethodInterceptor();
    public static SimpleMethodInterceptor getInstance() {
        return myMethodInterceptor;
    }
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
            throws Throwable {
        System.out.println("before");
        // 执行基类的方法(也就是委托者)的具体逻辑
        Object result = methodProxy.invokeSuper(proxy, args);
//        Object result = methodProxy.invoke(proxy, args); // 执行子类方法(即代理逻辑即当前)，则无限递归
        System.out.println("after");
        return result;
    }
}
