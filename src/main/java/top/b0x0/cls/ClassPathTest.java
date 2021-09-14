package top.b0x0.cls;

import java.net.URL;

public class ClassPathTest {

    public static void main(String[] args) {
        System.out.println(getClassPath());
    }

    /**
     * 获取类路径
     */
    public static String getClassPath() {
        String classPath = "";
        URL resource = getClassLoader().getResource("");
        if (resource != null) {
            classPath = resource.getPath();
        }
        return classPath;
    }

    /**
     * 获取类加载器
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

}
