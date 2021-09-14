package top.b0x0.classloader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 加载类的方式
 *
 * @author ManJiis
 * @since 2021-07-31
 */
public class ClassLoaderTest2 {

    /**
     * 第一种加载类的方式
     *
     * @throws ClassNotFoundException    /
     * @throws NoSuchMethodException     /
     * @throws InstantiationException    /
     * @throws IllegalAccessException    /
     * @throws InvocationTargetException /
     */
    private static void load1() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<?> newClass = Class.forName("top.b0x0.classloader.URLClassLoaderTestBean");
        System.out.println("load1 newClass.toGenericString() = " + newClass.toGenericString());
        Constructor constructor = newClass.getConstructor();
        Object obj = constructor.newInstance();
        Method method = newClass.getMethod("sayHello");
        method.invoke(obj);
    }

    /**
     * 第二种加载类的方式
     *
     * @throws ClassNotFoundException    /
     * @throws NoSuchMethodException     /
     * @throws InstantiationException    /
     * @throws IllegalAccessException    /
     * @throws InvocationTargetException /
     */
    private static void load2() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println("load2 loader = " + loader);

        Class<?> newClass = loader.loadClass("top.b0x0.classloader.URLClassLoaderTestBean");
        System.out.println("load2 newClass = " + newClass);
        System.out.println("load2 newClass.toGenericString() = " + newClass.toGenericString());

        Constructor constructor = newClass.getConstructor();
        Object obj = constructor.newInstance();
        Method method = newClass.getMethod("sayHello");
        method.invoke(obj);
    }

    public static void main(String[] args) throws Exception {
        load1();
        Thread.sleep(60000);
        load2();
    }


}    