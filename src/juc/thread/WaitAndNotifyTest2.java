package juc.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程1，2，3，4分别顺序输出1234
 */
public class WaitAndNotifyTest2 {

    static volatile Integer step = 1;
    private static ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(4, 4, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
                    new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 4; i++) {
            final int obj = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        synchronized ("") {
                            if (step != obj) {
                                try {
                                    "".wait();
                                    continue;
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            System.out.println(obj);
                            step++;
                            "".notifyAll();
                            break;
                        }
                    }
                }
            });
        }
    }
}
