package juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: java-base->CountDownLatchLearn
 * @description:
 * @author: G_Y
 * @create: 2019-08-21 13:43
 **/
public class CountDownLatchLearn {
    static ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(2,4,
                    1, TimeUnit.SECONDS,new ArrayBlockingQueue<>(50),
                    new ThreadPoolExecutor.CallerRunsPolicy());
    public static void main(String[] args) {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        ids.add(5);
        CountDownLatch countDownLatch = new CountDownLatch(ids.size());
        for (Integer id : ids) {
            final Integer taskId = id;
            threadPoolExecutor.execute(new MyTaskRun(countDownLatch,taskId));
        }
        try {
            // 等待 任务计数器 任务 Down为0 继续往下执行，否则阻塞
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main over");
    }
}
class MyTaskRun implements Runnable {
    private CountDownLatch countDownLatch;
    private Integer id;

    public MyTaskRun(CountDownLatch countDownLatch, Integer id) {
        this.countDownLatch = countDownLatch;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            System.out.println(id);
            Thread.sleep(100);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }
}
