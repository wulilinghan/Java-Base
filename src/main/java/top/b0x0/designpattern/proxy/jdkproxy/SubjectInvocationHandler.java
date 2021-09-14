package top.b0x0.designpattern.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理增强逻辑 实现器   (它是实现JDK动态代理的桥梁)
 *
 * @author ManJiis
 * @since 2020/9/11 19:32
 **/
public class SubjectInvocationHandler implements InvocationHandler {
    /**
     * 委托对象
     */
    private Object subject;

    public SubjectInvocationHandler() {
    }

    public SubjectInvocationHandler(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        // 前置增强
        before();
        try {
            // 执行原目标逻辑
            // 反射执行 委托逻辑
            result = method.invoke(subject, args);
            for (Object arg : args) {
                if ("error".equals(arg.toString())) {
                    throw new RuntimeException("error...");
                }
            }
        } catch (Exception e) {
            throwE();
            return result;
        }
        // 后置增强
        after();
        return result;
    }

    private void before() {
        System.out.println("前置增强....");
    }

    private void after() {
        System.out.println("后置增强....");
    }

    private void throwE() {
        System.out.println("异常....");
    }
}
