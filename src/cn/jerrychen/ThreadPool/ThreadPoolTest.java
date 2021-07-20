package cn.jerrychen.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
    public static void main(String[] args) {
        //corePoolSize设置为n+1，workSize设置为corePoolSize除单个线程执行时间*2
        MyThreadPool myThreadPool = new MyThreadPool(2, 4, 20);
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 40; i++) {
            myThreadPool.submit(new MyTask2(i));
        }
        //myThreadPool.shutdown();
    }
}
