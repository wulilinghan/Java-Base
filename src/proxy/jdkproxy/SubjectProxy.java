package proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created By gao_e on 2020/3/17 21:23
 */
public class SubjectProxy extends Proxy implements ISubject {
    protected SubjectProxy(InvocationHandler h) {
        super(h);
    }
    @Override
    public void doSomething() {
        try {
            super.h.invoke(this,
                    Class.forName("proxy.jdkproxy.ISubject").getMethod("sayName"), null);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new RuntimeException("代理逻辑执行异常");
        }
    }
    @Override
    public String sayName(String name) {
        try {
            return (String) super.h.invoke(this,
                    Class.forName("proxy.jdkproxy.ISubject").getMethod("sayName",
                            Class.forName("java.lang.String")),
                    new Object[]{name});
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new RuntimeException("代理逻辑执行异常");
        }
    }
}
