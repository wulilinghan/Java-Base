package designpattern.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理增强逻辑 实现器   (它是实现JDK动态代理的桥梁)
 *
 * @author ManJiis
 * @since 2020/9/11 19:32
 **/
public class MyInvocationHandler implements InvocationHandler {
    // 委托对象
    private Object subject;

    public MyInvocationHandler() {
    }

    public MyInvocationHandler(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 前置增强
        Object result = null;
        before();
        try {
            // 执行原目标逻辑
            // 反射执行 委托逻辑
            result = method.invoke(subject, args);
        } catch (Exception e) {
            throwE();
            return result;
        }
        // 后置增强
        after();
        return result;
    }

    private void before() {
        System.out.println("before");
    }

    private void after() {
        System.out.println("after");
    }

    private void throwE() {
        System.out.println("after");
    }
}
