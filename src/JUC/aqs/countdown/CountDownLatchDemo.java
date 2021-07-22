package JUC.aqs.countdown;

import common.NamedThreadFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ManJiis
 */
public class CountDownLatchDemo {
    /**
     * 赛道个数，一人一道
     */
    static int count = 6;
    /**
     * 指挥官
     */
    static final CountDownLatch MASTER_COUNT_DOWN_LATCH = new CountDownLatch(1);
    /**
     * 闭锁，可实现计数器递减
     */
    static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(count);

    static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) throws InterruptedException {

        System.out.println("校园400米赛跑，即将开始");

        Thread.sleep(1000);

        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(count, count, 60, TimeUnit.SECONDS
                , new LinkedBlockingQueue<Runnable>()
                , new NamedThreadFactory("CountDownLatch-", false));
        for (int i = 0; i < count; i++) {
            executorPool.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "准备好了");

                    //比赛选择准备
                    MASTER_COUNT_DOWN_LATCH.await();

                    System.out.println(Thread.currentThread().getName() + "拼命奔跑中");

                    Thread.sleep((long) (Math.random() * 10000));

                    if (atomicInteger.decrementAndGet() >= 0) {
                        System.out.println("冠军诞生了 " + Thread.currentThread().getName() + "首先到达终点");
                    } else {
                        System.out.println("其次 " + Thread.currentThread().getName() + "到达终点");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //闭锁减一
                    COUNT_DOWN_LATCH.countDown();
                }

            });
        }
        Thread.sleep(5 * 1000);
        System.out.println("预备，开始比赛");
        // 准备结束，开始比赛，处于等待的线程继续执行任务
        MASTER_COUNT_DOWN_LATCH.countDown();

        //线程阻塞，直到闭锁值为0时，阻塞才释放，继续往下执行
        COUNT_DOWN_LATCH.await();

        System.out.println("比赛结束");
        executorPool.shutdown();
    }
}
