package proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
//猜想内存中代理类
public class UsersServiceProxy extends Proxy implements UserService {

    public UsersServiceProxy(InvocationHandler h) {
        super(h);
    }

    @Override
    public String getUserNameById(Integer userId) {
        Method method = null;
        try {
            method = UserService.class.getMethod("getUserNameById", userId.getClass());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Object[] args = new Object[]{userId};
		Object result = null;
		try {
			result = h.invoke(this, method, args);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return (String) result;
    }

    @Override
    public String get2() {
        try {
            return (String) h.invoke(this, UserService.class.getMethod("get2"),null);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return  null;
    }

}