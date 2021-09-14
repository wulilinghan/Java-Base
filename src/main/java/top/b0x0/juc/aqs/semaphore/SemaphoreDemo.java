package top.b0x0.juc.aqs.semaphore;


import top.b0x0.common.NamedThreadFactory;

import java.util.Vector;
import java.util.concurrent.*;

/**
 * @author ManJiis
 */
public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        //厕所坑位
        int count = 2;
        // 人数
        int peopleNum = 8;
        CountDownLatch countDownLatch = new CountDownLatch(peopleNum);
        Semaphore semaphore = new Semaphore(count);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 60
                , TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>()
                , new NamedThreadFactory("Semaphore-", false));
        Vector<String> vector = new Vector(peopleNum);
        System.out.println("目前有厕所位置：" + count);
        for (int i = 0; i < peopleNum; i++) {
            String name = "编号:" + (i + 1);
            Thread thread = new Thread(new RunTask(name, semaphore, countDownLatch, vector), name);
            vector.add(name);
            threadPoolExecutor.execute(thread);
        }
        countDownLatch.await();

        threadPoolExecutor.shutdown();

    }
}

class RunTask implements Runnable {

    private final String name;
    private final Semaphore semaphore;

    private final CountDownLatch countDownLatch;

    private final Vector<String> vector;

    public RunTask(String name, Semaphore semaphore, CountDownLatch countDownLatch, Vector<String> vector) {
        this.name = name;
        this.semaphore = semaphore;
        this.countDownLatch = countDownLatch;
        this.vector = vector;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " 等待中....");
            semaphore.acquire();
            vector.remove(name);
            System.out.println(name + " 进入厕所，使用中...目前等待有：" + vector.toString());
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println(name + "使用完毕，退出");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }
}
