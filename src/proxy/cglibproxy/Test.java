package proxy.cglibproxy;

import org.springframework.cglib.core.DebuggingClassWriter;
import proxy.jdkproxy.ISubject;

/**
 * @program: java-base->Test
 * @description: test cglib
 * @author: ManJiis
 * @since: 2019-08-21 18:05
 **/
public class Test {

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\class");
        // 获取代理实例
//        User proxyInstance = MyMethodInterceptor.getProxyInstance(User.class);
//        String s = proxyInstance.sayAge(29);
//        System.out.println("---------------------");
//        System.out.println(s);

        ISubject subject = MyMethodInterceptor.getProxyInstance(ISubject.class);
//        subject.doSomething();
        String result = subject.sayName("黑马程序员");
        System.out.println(result);

//        System.out.println("==========================");
        // 使用模拟的cglib代理类对象
//        UserProxy userProxy = new UserProxy(new MyMethodInterceptor());
//        String s1 = userProxy.sayAge(18);
//        System.out.println(s1);

    }
}
