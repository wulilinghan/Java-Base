package rpc.socket.bserver.rpcServer;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author GY
 * @date 2019年11月18日
 * @说明: 基于注解 加载 具体实现的Service实例
 */
public class ServiceLoader {

    /**
     * key——serviceId，value——serviceBean
     */
    public static ConcurrentHashMap<Integer, Object> registedServiceMap = new ConcurrentHashMap<>(512);

    /**
     * 服务id维护器
     */
    static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void loadService(String serviceBasePackage) {
        // 获取 需要别实例化的具体Service Class对象集合
        Set<Class<?>> classSet = getClassSet(serviceBasePackage);
        // 实例化service对象并且存储
        instanceAndLoadService(classSet);
    }

    public static void instanceAndLoadService(Set<Class<?>> classSet) {
        for (Class<?> cls : classSet) {
            Object o = null;
            try {
                 o = cls.newInstance();
                 // TODO o对象的属性动态注入略...
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            registedServiceMap.put(atomicInteger.getAndAdd(1),o);
        }
    }

    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<>();
        try {
            //加载此包名路径资源(只要此包一个资源)
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()) {//只会遍历一次
                //获取packageName指定的包名的资源路径URL
                URL url = urls.nextElement();
                if (url != null) {
                    //获取此URL的协议名称(包在jvm中对应的也是一个文件)
                    String protocol = url.getProtocol();
                    if ("file".equals(protocol)) {
                        //将路径里面的包含的"%20"空格(JavaBug)去掉!
                        //获取包路径
                        String packagePath = url.getPath().replaceAll("%20", "");//一般不会产生"%20"
                        //根据包路径 加载此包下的所有类文件类信息对象 包中可能还有子包
                        addClassFromThePackage(classSet, packagePath, packageName);
                    }
                    //如果protocol是jar(如需要加载的包是依赖的项目jar)...略
                    if ("jar".equals(protocol)) {
                        // TODO 略...
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return classSet;
    }

    private static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    private static void addClassFromThePackage(Set<Class<?>> classSet, String packagePath, String packageName) {
        //获取当前包下所有子文件(包括子包也是一种file),过滤不是.class并且也不是"包文件"的file,如txt之类的就被直接过滤掉
        //需要根据包urlgetPath的路径获取file下所有file
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                // 只需要 文件并且是.class的文件,或者是目录 都返回true
                return file.isFile() && file.getName().endsWith(".class") || file.isDirectory();
            }
        });
        //遍历所有留下的文件(包括.class跟"包文件")
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) { // 是指定的文件 就获取到全限定类名 然后装载它
                //如果这个file是真的文件类型 则说明它只能是.class文件(上面已经过滤掉)
                String className = fileName.substring(0, fileName.lastIndexOf(".")); // 把.class后最截取掉
                if (StringUtils.isNotBlank(packageName)) {
                    className = packageName + "." + className; // 根据包名 + 文件名 得到这个类的全限定名称,
                }
                //根据class文件全名**.**.**.*,将类信息对象加载进classSet
                doAddClass(classSet, className);
            } else { // 是文件 就递归自己. 获取 文件夹的绝对路径,和 当前文件夹对应的 限定包名.方便 文件里面直接使用
                //这个file不是真的文件类型,是包file,递归
                String subPackagePath = fileName;
                if (StringUtils.isNotBlank(subPackagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath; // 第一次:由基础包名 得到绝对路径,再加上当前文件夹名称 = 当前文件夹的绝对路径
                }
                //subPackagePath = file.getAbsolutePath(); // 该方法获得文件的绝对路径.和上面的代码效果是一致的
                String subPackageName = fileName;
                if (StringUtils.isNotBlank(subPackageName)) {
                    subPackageName = packageName + "." + subPackageName; // 第一次: 基础包名 加文件夹名称 组合成 当前包名 +
                }
                //递归逐层遍历每一个子包file
                addClassFromThePackage(classSet, subPackagePath, subPackageName);
            }
        }
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
        //提高类加载的性能,将是否初始化设置为false,不需要自动初始化(静态代码块)
        Class<?> cls = loadClass(className, false);
        if (cls.isAnnotationPresent(Service.class)) {
            classSet.add(cls);
        }
    }

    private static Class<?> loadClass(String className, boolean isInitialized) {
        //用来保存类型信息的类是Class类
        Class<?> cls;
        try {
            //类名、是否已经初始化(执行类的静态代码块)、类加载器
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //类型信息对象---包含类的信息的一个对象
        return cls;
    }
}
