package juc;

import javafx.concurrent.Task;

import java.util.concurrent.*;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @program: java-base->FutrureLearn
 * @description:
 * @author: G_Y
 * @since: 2019-08-21 13:41
 **/
public class FutrureLearn {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 创建一个执行任务目标
        Callable task = new MyTask(100);
        // 将执行任务装配进入
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            Integer sum = futureTask.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 结合线程池使用
        ExecutorService executor = Executors.newCachedThreadPool();
        MyTask task2 = new MyTask(1000);
        Future<Integer> future = executor.submit(task2);
        executor.shutdown();
        try {
            Integer sum = future.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //线程池
        ExecutorService ex = Executors.newFixedThreadPool(3);
        MyTask task3 = new MyTask(200);
        FutureTask<Integer> ft = new FutureTask<Integer>(task3);
        ex.submit(ft);
        Integer integer = ft.get();
        System.out.println(integer);
        ex.shutdown();

    }
}

// 线程的第三种实现方式
// 可以获取线程中的执行结果，泛型则为具体返回值的类型
class MyTask implements Callable<Integer> {
    int obj;

    public MyTask(Integer obj) {
        this.obj = obj;
    }

    @Override
    public Integer call() throws Exception {
        int i = 1;
        int sum = 0;
        do {
            sum += i;
            i++;
        } while (i <= obj);
        TimeUnit.SECONDS.sleep(10);
        return sum;
    }
}