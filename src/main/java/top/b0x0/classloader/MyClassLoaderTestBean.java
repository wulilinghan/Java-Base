package top.b0x0.classloader;

/**
 * 该类写在记事本里，在用javac命令行编译成class文件，放在d盘根目录下
 * <p>
 * <p>
 * MyClassLoaderTestBean.java用于被自定义类加载器加载。
 *
 * @author ManJiis
 * @since 2021-08-02
 */
public class MyClassLoaderTestBean {
    public static void main(String[] args) {
        System.out.println("EasyTest.main(String[] args)");
//        System.out.println("EasyTest.main(String[] args)-args: " + Arrays.toString(args));
    }

    public void print() {
        System.out.println("EasyTest.print()-" + Thread.currentThread().getName() + "-EasyTest,you did it!");
    }
}