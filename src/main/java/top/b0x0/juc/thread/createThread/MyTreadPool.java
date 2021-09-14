package top.b0x0.juc.thread.createThread;

import top.b0x0.common.NamedThreadFactory;

import java.util.concurrent.*;

/**
 * 线程池
 * <p>
 * 1. 单个线程
 * ExecutorService threadPool = Executors.newSingleThreadExecutor();
 * <p>
 * 2. 创建一个固定的线程池的大小
 * ExecutorService threadPool = Executors.newFixedThreadPool(5);
 * <p>
 * 3. 可伸缩的，遇强则强，遇弱则弱
 * ExecutorService threadPool = Executors.newCachedThreadPool();
 * 4. ThreadPoolExecutor （推荐）
 *
 * @author ManJiis
 */
public class MyTreadPool {
    public static void main(String[] args) {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3, 6, 3, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(200),
                new NamedThreadFactory("pool-", false),
                new ThreadPoolExecutor.CallerRunsPolicy());
        pool.allowCoreThreadTimeOut(true);

        Runnable runnable1 = () -> System.out.println("I'm ThreadPool code");
        pool.execute(runnable1);

    }
}
