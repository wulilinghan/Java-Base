package top.b0x0.cls.instance.face;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadTest {
    private static ExecutorService executorService = Executors.newFixedThreadPool(6);
    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 4, 2, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(200), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        final Obj obj = new Obj();
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    obj.addId();
                }
            });
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
        obj.say();
    }
}

class Obj {
    private static Integer id = 0;

    public synchronized void addId() {
        id++;
    }

    public void say() {
        System.out.println(id);
    }
}
