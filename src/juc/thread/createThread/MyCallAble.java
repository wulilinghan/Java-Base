package juc.thread.createThread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 用Callable任务计算1-100的和
 *
 * @author ManJiis
 * @since 2020/7/21 19:28
 **/
public class MyCallAble {
    private static final Logger LOG = LoggerFactory.getLogger(MyCallAble.class);

    public static void main(String[] args) {
        // 两种方式使用 Callable

        // 1、封装成FutureTask对象
        MyCallableTask myCallableTask = new MyCallableTask();

        FutureTask<Integer> futureTask = new FutureTask<>(myCallableTask);

        new Thread(futureTask,"A").start();

        try {

            Integer integer = futureTask.get();
            System.out.println(integer);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // 2、结合线程池来使用
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<Integer> future = executorService.submit(myCallableTask);
        try {

//            Integer integer = future.get();

            // 可以设置超时时间
            Integer integer = future.get(5, TimeUnit.SECONDS);
            System.out.println(integer);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            //   throw new 业务异常;
            if (e instanceof TimeoutException) {
                LOG.error("运行超时........");
            }
        }
    }
}

class MyCallableTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Thread.sleep(6000);
        return 5050;
    }
}
