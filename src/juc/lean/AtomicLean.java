package juc.lean;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author G_Y
 * @Date 2020/7/21 20:53
 * @Description: // 需求：10000 个线程 去对 共享资源做 修改操作，如何保证操作的原子性 例如 对int i 值做 ++ 操作
 **/
public class AtomicLean {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10000, 10000, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(200));
        for (int i = 0; i < 10000; i++) {
            threadPoolExecutor.execute(new MyAddTask());
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(MyAddTask.count);
        System.out.println(MyAddTask.atomicInteger);
        threadPoolExecutor.shutdown();
    }
}

class MyAddTask implements Runnable {
//    public static int count = 0;
    public static volatile int count = 0;
    public static AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void run() {
        count++;
//        synchronized ("") {
//            count++;
//        }
        atomicInteger.addAndGet(1);
    }
}