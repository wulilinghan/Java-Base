package top.b0x0.classloader;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author ManJiis
 * @since 2021-07-31
 */
public class URLClassLoaderTest2 {
    public final static String SRC_DIR = System.getProperty("user.dir") + File.separator + "webroot" + File.separator;

    public static void main(String[] args) {
        System.out.println("dir = " + SRC_DIR);

        try {
            File file = new File(SRC_DIR);
            // attend this line
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};
            URLClassLoader loader = new URLClassLoader(urls, null, null);

            System.out.println("test = " + loader.getURLs()[0].toString());

            Class<?> beanClass = loader.loadClass("top.b0x0.classloader.URLClassLoaderTestBean");
            Object obj = beanClass.newInstance();
            Method method = beanClass.getMethod("sayHello");
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}