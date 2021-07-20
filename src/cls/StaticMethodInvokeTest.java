package cls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created By gao_e on 2020/3/29 15:17
 */
public class StaticMethodInvokeTest {
    public static String s;
    public static void setS(String s) {
        StaticMethodInvokeTest.s = s;
    }

    public static void main(String[] args) {
        Class<StaticMethodInvokeTest> staticMethodInvokeTestClass = StaticMethodInvokeTest.class;
        try {
            Method setS = staticMethodInvokeTestClass.getDeclaredMethod("setS", String.class);
            setS.invoke(staticMethodInvokeTestClass, "卧槽");
            System.out.println(StaticMethodInvokeTest.s);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
