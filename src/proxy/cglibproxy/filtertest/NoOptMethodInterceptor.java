package proxy.cglibproxy.filtertest;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created By gao_e on 2020/3/20 10:17
 */
public class NoOptMethodInterceptor implements MethodInterceptor {
    private Object target = RealSubject.getInstance();
    private NoOptMethodInterceptor() {}
    private static NoOptMethodInterceptor myMethodInterceptor = new NoOptMethodInterceptor();
    public static NoOptMethodInterceptor getInstance() {
        return myMethodInterceptor;
    }
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // 执行基类的方法(也就是委托者)的具体逻辑
//        Object result = methodProxy.invokeSuper(proxy, args);
        // 根据 method 注解生成调用链，如果方法不需要拦截处理则直接执行methodProxy.invoke(target, args)
        // 如果 有调用链 需要执行，则使用链路执行
        // 从IOC中获取目标对象
//        Object instance = declaringClass.newInstance();
        Object result = methodProxy.invoke(target, args);
        return result;
    }
}
