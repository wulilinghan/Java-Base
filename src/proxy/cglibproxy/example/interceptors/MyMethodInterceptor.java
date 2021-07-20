package proxy.cglibproxy.example.interceptors;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import proxy.cglibproxy.example.ProxyInstanceFactory;

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
        Object result = null;
        if(method.isAnnotationPresent(Transactional.class)) {
//            System.out.println(method.getDeclaringClass());
            Object methodInterceptor1ProxyInstance = ProxyInstanceFactory.getMethodInterceptor1ProxyInstance(method.getDeclaringClass());
            result = methodProxy.invoke(methodInterceptor1ProxyInstance, args);
            return result;
        } else {
            Object methodInterceptor2ProxyInstance = ProxyInstanceFactory.getMethodInterceptor2ProxyInstance(method.getDeclaringClass());
            result = methodProxy.invoke(methodInterceptor2ProxyInstance, args);
            return result;
        }
    }
}
