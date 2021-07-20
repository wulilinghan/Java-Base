package proxy.cglibproxy.filtertest;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created By gao_e on 2020/3/20 10:17
 */
public class CatchAbleMethodInterceptor implements MethodInterceptor {
    private Object target = RealSubject.getInstance();
    private CatchAbleMethodInterceptor() {
    }

    private static CatchAbleMethodInterceptor myMethodInterceptor = new CatchAbleMethodInterceptor();

    public static CatchAbleMethodInterceptor getInstance() {
        return myMethodInterceptor;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("CatchAbleMethodInterceptor before");
        // 执行基类的方法(也就是委托者)的具体逻辑
//        Object result = methodProxy.invokeSuper(proxy, args);
        // 从IOC中获取目标对象
        Object result = methodProxy.invoke(target, args);
        System.out.println("CatchAbleMethodInterceptor after");
        return result;
    }
}
