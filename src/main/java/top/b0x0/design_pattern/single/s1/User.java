package top.b0x0.design_pattern.single.s1;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ManJiis
 * @since 2020/9/6 18:41
 * @Description: // 整个程序中 只提供一个 User 对象 给 客户端使用
 **/
public class User {
    static {
        System.out.println("User static {}");
    }

    // 构造私有 保证 别人不能创建
    private User(){}

    // 饿汉 - 天生线程安全  类初始化时 创建 bean
    /*private static User user = new User();

    public static User getInstance() {
        return user;
    }*/

    // 懒汉
    /*private static volatile User user = null;
    public static User getInstance() {
        if(user != null) {
            return user;
        }
        synchronized (User.class) {
            if(user != null){
                return user;
            }
            return (user=new User());
        }
    }*/

    // 静态内部类
    /*public static class InnerClass {
        // 外部类被加载初始化时 静态内部类中的static代码块不会执行 保证懒加载
        static {
            System.out.println("InnerClass static {}");
        }
        public static User user = new User();
    }
    public static User getInstance() {
        return InnerClass.user;// 访问内部类资源时才会加载执行内部类static资源
    }*/

    // 基于CAS的AtomicReference来实现
    private static AtomicReference<User> atomicReference = new AtomicReference<>();
    public static User getInstance() {
        while (true) {
            User user = atomicReference.get();
            if(user != null) {
                return user;
            }
            user = new User();
            boolean b = atomicReference.compareAndSet(null, user);
            if(b) {
                return user;
            }
        }
    }


}
