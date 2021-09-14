package top.b0x0.juc.thread;

/**
 * @program: java-base->SyncTest
 * @description:
 * @author: ManJiis
 * @since: 2019-09-18 18:45
 **/
public class SyncTest2 {
    public static void main(String[] args) throws InterruptedException {
        AClass a1 = new AClass();
        AClass a2 = new AClass();
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                a1.f1();
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                AClass.f3();
            }
        };
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
    }
}

class AClass {
    int i=0;
    public void f1() {
        synchronized (String.class) {
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }
    public synchronized void f2() {
        for (int i = 100; i <= 110; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }
    // 静态方法
    public static void f3() {
        synchronized ("") {
            for (int i = 200; i <= 210; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }
}