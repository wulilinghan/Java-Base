package proxy.jdkproxy.method;

import proxy.jdkproxy.ISubject;
import proxy.jdkproxy.RealSubject;
import proxy.jdkproxy.SubRealSubject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created By gao_e on 2020/3/16 17:16
 */
public class MethodTest {
    public static void main(String[] args) {
        Method m1 = null;
        Method[] declaredMethods = ISubject.class.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            boolean isDoSomething = declaredMethod.getName().equals("doSomething");
            if(isDoSomething) {
                m1 = declaredMethod;
                break;
            }
        }
        Method m2 = null;
        declaredMethods = RealSubject.class.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            boolean isDoSomething = declaredMethod.getName().equals("doSomething");
            if(isDoSomething) {
                m2 = declaredMethod;
                break;
            }
        }
        Method m3 = null;
        declaredMethods = SubRealSubject.class.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            boolean isDoSomething = declaredMethod.getName().equals("doSomething");
            if(isDoSomething) {
                m3 = declaredMethod;
                break;
            }
        }
        System.out.println(m1.getDeclaringClass().getName());
        System.out.println(m2.getDeclaringClass().getName());
        System.out.println(m3.getDeclaringClass().getName());
        System.out.println(m1 == m2);
        System.out.println(m2 == m3);
        try {
            m1.invoke(new RealSubject(),null);
            m2.invoke(new RealSubject(),null);
            m1.invoke(new SubRealSubject(),null);
            m2.invoke(new SubRealSubject(),null);
            m3.invoke(new SubRealSubject(),null);
            m3.invoke(new RealSubject(),null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
