package proxy.cglibproxy.example.interceptors;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created By gao_e on 2020/3/18 10:35
 */
public class MyMethodInterceptor2 implements MethodInterceptor {
    private MyMethodInterceptor2(){}
    private static MyMethodInterceptor2 myMethodInterceptor = new MyMethodInterceptor2();
    public static MethodInterceptor getInstance() {
        return myMethodInterceptor;
    }
    @Override
    public Object intercept(Object proxyBean, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
//        System.out.println("before");
        Object result = methodProxy.invokeSuper(proxyBean, args);
//        System.out.println("result is " + result);
//        System.out.println("after");
        return null;
    }
}
