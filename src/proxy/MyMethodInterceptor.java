package proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * @author GY
 * @date 2019年6月12日
 * @说明: CGlib动态代理
 */
public class MyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
            throws Throwable {
        System.out.println("before");
        System.out.println(proxy.getClass());// class proxy.AServiceImpl$$EnhancerByCGLIB$$8232b52f
        System.out.println(method.getName());// sayHello
        System.out.println(methodProxy.getSuperName());// CGLIB$sayHello$0
        Object object = methodProxy.invokeSuper(proxy, args);
        System.out.println(object);
        System.out.println("after");
        return object;
    }

    public Object getProxy(Class<?> cls) {
        Enhancer eh = new Enhancer();
        eh.setSuperclass(cls);
        eh.setCallback(this);
        return eh.create();
    }

    public static void main(String[] args) {
        AServiceImpl aService = (AServiceImpl) new MyMethodInterceptor()
                .getProxy(AServiceImpl.class);
        aService.sayHello();
    }

}
