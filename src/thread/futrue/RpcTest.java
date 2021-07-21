package thread.futrue;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: java-base->RpcTest
 * @description: rpctest
 * @author: G_Y
 * @since: 2019-09-19 19:50
 **/
public class RpcTest {

    public static void main(String[] args) {
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(new UserService());
        IUserService userService = (IUserService) myInvocationHandler.getProxyInstance();
        userService.add(1, 1);
    }

}

interface IUserService {
    public Integer add(int a, int b);
}

class UserService implements IUserService {

    @Override
    public Integer add(int a, int b) {
        System.out.println(a + "+" + b + "=" + (a + b));
        return a + b;
    }
}

class MyInvocationHandler implements InvocationHandler {
    private Object obj;

    public MyInvocationHandler(Object obj) {
        this.obj = obj;
    }

    public Object getProxyInstance() {
        Object o = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), obj.getClass().getInterfaces(), this);
        return o;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("before");
        Object invoke = method.invoke(obj, objects);
        System.out.println("after");
        return invoke;
    }
}