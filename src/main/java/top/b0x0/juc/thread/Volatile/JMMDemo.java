package top.b0x0.juc.thread.Volatile;

import java.util.concurrent.TimeUnit;

public class JMMDemo {
    // 不加 volatile 程序就会死循环！
    // 加 volatile 可以保证可见性
    private static int num = 0;
//    private volatile static int num = 0;

    public static void main(String[] args) {

        new Thread(() -> {
            // 线程 1 对主内存的变化不知道的
            while (num == 0) {
                System.out.println(num);
            }
        }, "son").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num = 1;
        System.out.println(num);
    }
}