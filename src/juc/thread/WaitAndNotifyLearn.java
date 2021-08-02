package juc.thread;

/**
 * 两个线程
 * 一个线程用于输出i的值，但是要求输出前必须保证i已经赋值
 * 另外一个线程用于给i初始化赋值
 */
public class WaitAndNotifyLearn {
    volatile static Integer i = null;
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized ("i") {
                    while (i == null) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            "i".wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println(i);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                i = 1;
                synchronized ("i") {
                    "i".notifyAll();
                }
            }
        }).start();
    }
}

