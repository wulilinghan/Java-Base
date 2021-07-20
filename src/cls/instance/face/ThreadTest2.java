package cls.instance.face;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadTest2 {
    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 4, 2, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(200), new ThreadPoolExecutor.CallerRunsPolicy());
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new A();
        pool.execute(new Runnable() {
            @Override
            public void run() {
                a1.say1();
                A.say3();
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                a2.say2();
                A.say3();
            }
        });
    }
}
class A {
    public synchronized void say1() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }
    public synchronized void say2() {
        for (int i = 1000; i < 1100; i++) {
            System.out.println(i);
        }
    }
    public static synchronized void say3() {
        for (int i = 2000; i < 2100; i++) {
            System.out.println(i);
        }
    }
}
