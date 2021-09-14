package top.b0x0.designpattern.proxy.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * @author ManJiis
 * @since 2020/9/11 19:44
 **/
public class JdkProxyTest {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 委托对象
        Subject subject = new Subject();
        // 构建 代理增强器 对象
        SubjectInvocationHandler handler = new SubjectInvocationHandler(subject);
        // 创建动态代理对象
        ISubject subjectProxy = (ISubject) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{ISubject.class}, handler);
//        subjectProxy.sayHello("manji");
        subjectProxy.sayHello("error");
    }
}
