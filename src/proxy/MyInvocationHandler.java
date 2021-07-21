package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author GY
 * @since 2019年6月12日
 * @说明: JDK动态代理
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object obj;
    public Object proxy;

    public MyInvocationHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object invoke = method.invoke(obj, args);
        System.out.println("after");
        this.proxy = proxy;
        return invoke;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), this);
    }

    public static void main(String[] args) {
        AService aService = new AServiceImpl();
        MyInvocationHandler hIh = new MyInvocationHandler(aService);
        AService proxyInstance = (AService) hIh.getProxyInstance();
        proxyInstance.sayHello();
        System.out.println(hIh.proxy == proxyInstance); // true
    }

}

interface AService {
    void sayHello();
}

class AServiceImpl implements AService {

    @Override
    public void sayHello() {
        System.out.println("Hello!");
    }

}
