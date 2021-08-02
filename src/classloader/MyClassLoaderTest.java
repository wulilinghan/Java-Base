package classloader;

import javax.crypto.KeyGenerator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.security.Key;

/**
 * 1. MyClassLoaderTestBean.java 用于被自定义类加载器加载。
 * 2. Mycipher.java 用于加密EasyTest.class。
 * 3. MyClassLoader.java 自定义类加载器，用于解密被加密后的class文件。
 *
 * <p>
 * JAVA有三个类加载器：
 * 1.bootstrap class loader，负责加载系统类，比如jdk的rt.jar包里的类。
 * 2.extension class loader，负责加载jre/lib/ext目录下的所有类。
 * 3.system class loader，负责加载环境变量classpath指向目录下的所有类。
 * 他们三个依次是“父子关系”，因为 bootstrap class loader一般是使用C语言编写的，所以用户无法获取它的对象。
 * 当在classpath环境变量中的一个class文件被加载时，system class loader会将class交给父类extension class loader加载，
 * extension class loader会将class交给bootstrap class loader加载。
 * 如果bootstrap class loader加载不了，则返回给extension class loader加载，如果extension class loader加载不了，则返回给system class loader加载。
 * 被其中一个加载器成功加载后，便解析class文件。如果class文件中有使用到其他类对象，则继续调用类加载器加载。
 * 有些情况下不希望被用户看到Class文件的明文，比如为了保护软件的安全、防止破解等。
 *
 * <p>
 * 《JAVA核心技术第2卷》也有提到编写自己的类加载器，实现加载被加了密的class文件。
 *
 * <p>
 * 类加载器三个机制：委托、单一性、可见性
 * 委托：指加载一个类的请求交给父类加载器，若父类加载器不可以找到或者加载到，再加载这个类
 * 单一性：指子类加载器不会再次加载父类加载器已经加载过的类
 * 可见性：子类加载器可以看见父类加载器加载的所有类，而父类加载器不可以看到子类加载器加载的类
 *
 * <p>
 *
 * @author ManJiis
 * @since 2021-08-02
 */
public class MyClassLoaderTest {
    public final static String SRC_DIR = System.getProperty("user.dir") + File.separator + "src" + File.separator;

    private static final String SRC_CLASS_PATH = "D:/MyClassLoaderTestBean.class";
    private static final String OUT_CLASS_PATH = "D:/test/MyClassLoaderTestBean.class";

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        KeyGenerator keyGen;
        try {
            // 创建密钥
            keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            Key key = keyGen.generateKey();
            MyCipher mc = new MyCipher(key, "AES/ECB/PKCS5Padding");
            // 加密源class
            mc.cipher(new FileInputStream(new File(SRC_CLASS_PATH)),
                    new FileOutputStream(new File(OUT_CLASS_PATH)));

            MyClassLoader mcl = new MyClassLoader(key, "AES/ECB/PKCS5Padding");

            Class clazz = mcl.loadClass(OUT_CLASS_PATH);
            Object obj = clazz.newInstance();

            // EasyTest.print()
            Method methodPrint = clazz.getMethod("print", null);
            methodPrint.invoke(obj, null);

            // EasyTest.main(String[] args)
            Method methodMain = clazz.getMethod("main", String[].class);
            methodMain.invoke(obj, (Object) new String[]{"a", "b", "c"});

            System.out.println(obj);

            // 打印出自定义类加载器
            ClassLoader classLoader = obj.getClass().getClassLoader();
            int count = 0;
            while (classLoader != null) {
                count++;
                System.out.println(count + " - classloader: " + classLoader.toString());
                classLoader = classLoader.getParent();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}