package top.b0x0.designpattern.single;

import java.util.concurrent.atomic.AtomicReference;

public class Service {
    //枚举实现单例——略

    static {
        System.out.println("This is Service static");
    }

    private Service() {
    }

    // 第一种饿汉式
    private static Service service1 = new Service();

    public static final Service getInstance1() {
        return service1;
    }

    // 第二种懒汉式
    private volatile static Service service2;

    public static final Service getInstance2() { //DCL
        // 提前判断
        if (service2 != null) {
            return service2;
        }
        // 线程2有可能执行到这里让出cpu时间片，然后线程1创建线程成功
        synchronized (Service.class) {
            // 所以这里需要再一次判断不为null
            if (service2 != null) {
                return service2;
            }
            System.out.println("开始初始化instance2");
            return (service2 = new Service());
        }
    }

    // 第三种 静态内部类 式
    private static class InnerClass {
        static {
            System.out.println("This is Service InnerClass static");
        }
        private static Service service3 = new Service();
    }

    public static Service getInstance3() {
        System.out.println("1111111");
        return InnerClass.service3;
    }

    /*public static void main(String[] args) {
        Class.forName("top.b0x0.designpattern.single.Service", true, Thread.currentThread().getContextClassLoader());
        Service instance1 = Service.getInstance1();
        Service instance2 = Service.getInstance2();
        Service instance3 = Service.getInstance3();
    }*/

    /*public static void main(String[] args) throws ClassNotFoundException {
        for (int i = 1; i <= 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Service instance = Service.getInstance();
                }
            }).start();
        }
    }*/

    //基于CAS的单例模式
    private static final AtomicReference<Service> INSTANCE = new AtomicReference<Service>();

    public static final Service getInstance() {
        for (; ; ) {
            Service current = INSTANCE.get();
            if (current != null) {
                return current;
            }
            current = new Service();//高并发的场景下，可能会创建多个对象，但是最终只有一个会被使用，其它的会被丢弃
            System.out.println(current);
            if (INSTANCE.compareAndSet(null, current)) {
                return current;
            }
        }
    }

}
