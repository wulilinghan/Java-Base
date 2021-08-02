package classloader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author ManJiis
 * @since 2021-07-31
 */
public class URLClassLoaderTest {

    public final static String SRC_DIR = System.getProperty("user.dir") + File.separator + "src" + File.separator;

    public static void main(String[] args) throws Exception {
        System.out.println("dir = " + SRC_DIR);

        // Getting the jar URL which contains target class
        URL[] classLoaderUrls = new URL[]{new URL("file:" + SRC_DIR)};

        // Create a new URLClassLoader
        URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);

        // Load the target class
        Class<?> beanClass = urlClassLoader.loadClass("classloader.URLClassLoaderTestBean");

        // Create a new instance from the loaded class
        Constructor<?> constructor = beanClass.getConstructor();
        Object beanObj = constructor.newInstance();

        // Getting a method from the loaded class and invoke it
        Method method = beanClass.getMethod("sayHello");

        method.invoke(beanObj);
    }
}