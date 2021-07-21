package juc.aqs.countdown;

import juc.aqs.common.NamedThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 允许一个或多个线程等待，直到其他线程中的一组操作完成的同步辅助。
 *
 * @author ManJiis
 * @program java-base->CountDownLatchLearn
 * @since 2019-08-21 13:43
 **/
public class CountDownLatchLearn {

    static ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(2, 4,
                    1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50),
                    new NamedThreadFactory("CountDownLatch-", false),
                    new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        ids.add(5);

        CountDownLatch countDownLatch = new CountDownLatch(ids.size());

        ArrayList<Integer> resultList = new ArrayList<>();

        for (Integer taskId : ids) {
            threadPoolExecutor.execute(new MyTaskRun(countDownLatch, taskId, resultList));
        }

        try {
            // 等待 任务计数器 任务 Down为0 继续往下执行，否则阻塞
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main over");
        System.out.println("resultList = " + resultList);
        threadPoolExecutor.shutdown();
    }
}

class MyTaskRun implements Runnable {
    private final CountDownLatch countDownLatch;
    private final Integer id;
    private final List<Integer> resultList;

    public MyTaskRun(CountDownLatch countDownLatch, Integer id, List<Integer> resultList) {
        this.countDownLatch = countDownLatch;
        this.id = id;
        this.resultList = resultList;
    }

    @Override
    public void run() {
        try {
            System.out.println(id);
            resultList.add(id + 1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }
}
