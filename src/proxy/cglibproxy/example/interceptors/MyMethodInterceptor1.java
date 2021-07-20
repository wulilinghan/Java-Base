package proxy.cglibproxy.example.interceptors;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created By gao_e on 2020/3/18 10:35
 */
public class MyMethodInterceptor1 implements MethodInterceptor {
    private MyMethodInterceptor1(){}
    private static MyMethodInterceptor1 myMethodInterceptor = new MyMethodInterceptor1();
    public static MethodInterceptor getInstance() {
        return myMethodInterceptor;
    }
    @Override
    public Object intercept(Object proxyBean, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("这个是需要开启事务的");
        Object result = methodProxy.invokeSuper(proxyBean, args);
//        System.out.println("result is " + result);
        System.out.println("提交或回滚事务");
        return null;
    }
}
