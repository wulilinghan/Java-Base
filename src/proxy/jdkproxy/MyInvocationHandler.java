package proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: java-base->MyInvocation
 * @description: invocation
 * @author: G_Y
 * @create: 2019-08-19 18:48
 **/
public class MyInvocationHandler implements InvocationHandler {
    // 委托者对象
    private Object real;
    public MyInvocationHandler(Object real) {
        this.real = real;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.doSomethingBefore();
        // 打印一下
//        System.out.println(proxy.getClass().getName());
//        System.out.println(method.getDeclaringClass().getName());
//        System.out.println(method.getName());
//        for (Object arg : args) {
//            System.out.println(arg.getClass());
//            System.out.println(arg);
//        }
        Object result = method.invoke(real, args);
//        System.out.println(result.getClass().getName());
//        System.out.println(result);
        this.doSomethingAfter();
        return result;
    }
    private void doSomethingBefore() {
        System.out.println("Before...");
    }
    private void doSomethingAfter() {
        System.out.println("After...");
    }
}
