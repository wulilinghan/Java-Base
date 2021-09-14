package top.b0x0.juc.thread.threadSafe;

import java.util.concurrent.TimeUnit;

/**
 * @author TANG
 * @since 2021-07-27
 * @since jdk1.8
 */
public class Father {
    public synchronized void sayHello() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("father say hello ");
    }
}
