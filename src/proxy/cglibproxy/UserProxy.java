package proxy.cglibproxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program: java-base->UserProxy
 * @description:
 * @author: G_Y
 * @create: 2019-08-22 12:33
 * 模拟内存中的cglib代理类——User类的子类
 **/
public class UserProxy extends User {
    // 自定义的cglib动态代理回调处理类对象
    private MethodInterceptor methodInterceptor;

    public UserProxy(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }
    public UserProxy(){}

    // sayAge方法对象
    static Method sayAge;
    static MethodProxy sayAgeProxy;

    static {
        try {
            //初始化方法对象
            sayAge = User.class.getMethod("sayAge", Integer.class);
            sayAgeProxy = MethodProxy.create(User.class, UserProxy.class,
                    "(Ljava/lang/String;)Ljava/lang/String;",
                    "sayAge", "sayAgeProxy");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    // 代理方法，实则执行的是 cglib动态代理回调处理类 的 回调方法
    public String sayAge(Integer age) {
        try {
            // 执行回调方法(实现代理增强逻辑)
            Object intercept = methodInterceptor.intercept(this, sayAge, new Object[]{age}, sayAgeProxy);
            return (String) intercept;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    // 会生产一个新的方法，里面调用的父类方法，也就是被代理的方法的实际逻辑
    public String sayAgeProxy(Integer age) {
        return super.sayAge(age);
    }

}
