package JUC.aqs.cyclicbarrier;

import common.NamedThreadFactory;

import java.util.concurrent.*;

/**
 * @author ManJiis
 */
public class CyclicBarrierDemo {
    static int count = 10;
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(count, count, 60,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>()
            , new NamedThreadFactory("CyclicBarrier-", false));

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(count, () -> {
            System.out.println("所有人到齐了，开始本次会议！！！");
        });
        MyTask myTask = new MyTask(cyclicBarrier);
        for (int i = 0; i < count; i++) {
            threadPoolExecutor.execute(myTask);
        }

        threadPoolExecutor.shutdown();
    }
}

class MyTask implements Runnable {

    private final CyclicBarrier cyclicBarrier;

    public MyTask(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " 进入会议...");
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + " 发言...");
            Thread.sleep(1000);
            System.out.println("会议结束，" + Thread.currentThread().getName() + " 离开会议...");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
