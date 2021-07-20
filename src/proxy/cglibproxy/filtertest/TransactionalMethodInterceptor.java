package proxy.cglibproxy.filtertest;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created By gao_e on 2020/3/20 10:17
 */
public class TransactionalMethodInterceptor implements MethodInterceptor {
    private Object target = RealSubject.getInstance();
    private TransactionalMethodInterceptor() {}
    private static TransactionalMethodInterceptor myMethodInterceptor = new TransactionalMethodInterceptor();
    public static TransactionalMethodInterceptor getInstance() {
        return myMethodInterceptor;
    }
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("TransactionalMethodInterceptor before");
        // 执行基类的方法(也就是委托者)的具体逻辑
//        Object result = methodProxy.invokeSuper(proxy, args);
        // 从IOC中获取目标对象
        Object result = methodProxy.invoke(target, args);
        System.out.println("TransactionalMethodInterceptor after");
        return result;
    }
}
