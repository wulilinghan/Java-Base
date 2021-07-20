package proxy.cglibproxy.example;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created By gao_e on 2020/3/18 10:35
 */
public class MyMethodInterceptor implements MethodInterceptor {
    private MyMethodInterceptor(){}
    private static MyMethodInterceptor myMethodInterceptor = new MyMethodInterceptor();
    public static MethodInterceptor getInstance() {
        return myMethodInterceptor;
    }
    @Override
    public Object intercept(Object proxyBean, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("before");
        Object result = methodProxy.invokeSuper(proxyBean, args);
        System.out.println("result is " + result);
        System.out.println("after");
        return null;
    }
}
