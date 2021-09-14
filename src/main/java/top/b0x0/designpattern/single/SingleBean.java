package top.b0x0.designpattern.single;


import java.util.concurrent.atomic.AtomicReference;

/**
 * 单例类
 *
 * @author ManJiis
 * @since 2020/6/21 19:57
 **/
public class SingleBean {
    private Integer a = 0; // 多线程并发访问，修改同一个资源，则存在线程安全问题

    public synchronized int f() {
//        int a = 100;
        a++;
        return a;
    }

    // 多线程开发过程中 的 有序性的 问题
    /*static volatile boolean a;//volatile避免指令重排，解决内存可见性
    public static void main(String[] args) {
        // 主线程执行如下代码
        a = false;
        User user = new User();
        a = true;
        // 线程1 中 循环判断 a的值，当a为true 时，调用 user.f();
    }*/
    static {
        System.out.println("SingleBean static");
    }

    // 构造私有化
    private SingleBean() {
    }
    // 提供入口获取当前类-单例

    // 饿汉式
    /*private static SingleBean singleBean = new SingleBean();
    public static SingleBean getInstance() {
        return singleBean;
    }*/

    // 懒汉式
    /*private static v·olatile SingleBean singleBean = null;//懒汉式
    // DoubleCheckLock  DCL
    public static SingleBean getInstance() {
        // 懒汉式初始化
        if(singleBean != null) {
            return singleBean;
        }
        synchronized (SingleBean.class) {
            if(singleBean != null) {
                return singleBean;
            }
            // 暂时为null
            singleBean = new SingleBean();
        }
        return singleBean;
    }*/

    // 静态内部类 天生线程安全，懒加载
    /*private static class InnerClass {
        private static SingleBean singleBean;
        static {
            System.out.println("InnerClass static");
            singleBean = new SingleBean();
        }
    }
    public static SingleBean getInstance() {
        return InnerClass.singleBean;
    }*/

    // 第四种 枚举 枚举类中 只 提供一个枚举实例

    // 第五种 CAS 自旋锁的方式创建单例
    //基于CAS的单例模式
    private static final AtomicReference<SingleBean> INSTANCE = new AtomicReference<SingleBean>();

    public static final SingleBean getInstance() {
        for (; ; ) {
            SingleBean current = INSTANCE.get();
            if (current != null) {
                return current;
            }
            current = new SingleBean();//高并发的场景下，可能会创建多个对象，但是最终只有一个会被使用，其它的会被丢弃
            System.out.println(current);
            if (INSTANCE.compareAndSet(null, current)) {
                return current;
            }
        }
    }
}
