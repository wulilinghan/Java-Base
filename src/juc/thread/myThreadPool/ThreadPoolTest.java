package juc.thread.myThreadPool;

/**
 * ThreadPoolTest
 *
 * @author ManJiis
 * @since 2021-07-31
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        // corePoolSize设置为n+1，workSize设置为corePoolSize除单个线程执行时间*2
        MyThreadPool myThreadPool = new MyThreadPool(2, 4, 20);

        for (int i = 0; i < 40; i++) {
            myThreadPool.submit(new MyTask(i));
        }
         myThreadPool.shutdown();
    }
}
