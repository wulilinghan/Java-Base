package proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: java-base->MyInvocation
 * @description: invocation
 * @author: ManJiis
 * @since: 2019-08-19 18:48
 **/
public class MyInvocationHandler2 implements InvocationHandler {
    private MyInvocationHandler2() {}
    private static MyInvocationHandler2 myInvocation2 = new MyInvocationHandler2();
    public static InvocationHandler getInvocationHandler() {
        return myInvocation2;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.doSomethingBefore();
        Object result = null;
        // 这里需要干嘛呢？
        // 这个地方从狭义来看，应该不能称为代理了，因为我们不存在委托者，我们是自定义了接口方法的具体实现逻辑
        // result = ... 如下，返回一个方法执行结果类型的空对象
//        Class<?> returnType = method.getReturnType();
//        result = returnType.newInstance();
        result = doSomething();
        this.doSomethingAfter();
        return result;
    }
    private void doSomethingBefore() {
        System.out.println("Before...");
    }
    private void doSomethingAfter() {
        System.out.println("After...");
    }
    private Object doSomething() {
        System.out.println("This is proxy self edit doSomething");
        return null;
    }
}
