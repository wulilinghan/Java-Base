package top.b0x0.classloader;


/**
 * @author ManJiis
 * @since 2021-07-31
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoader loader = ClassLoaderTest.class.getClassLoader();
        int count = 0;
        while (loader != null) {
            count++;
            System.out.println(count + " - top.b0x0.classloader: " + loader.toString());
            loader = loader.getParent();
        }
        //1 - top.b0x0.classloader: sun.misc.Launcher$AppClassLoader@18b4aac2
        //2 - top.b0x0.classloader: sun.misc.Launcher$ExtClassLoader@3a71f4dd
    }

}