package thread;

import java.util.concurrent.*;

public class MyTreadPool {
    public static void main(String[] args) {
        // 推荐
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 6, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(200),
                new ThreadPoolExecutor.CallerRunsPolicy());
        pool.allowCoreThreadTimeOut(true);
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("I'm ThreadPool code");
            }
        };
        pool.execute(runnable1);
        // 不推荐
        ExecutorService executorService = Executors.newCachedThreadPool();
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("I'm executorService code");
            }
        };
        executorService.execute(runnable2);
    }
}
