package juc.ForkJoin;

import java.util.concurrent.RecursiveTask;

/**
 * 求和计算的任务！
 *
 * <p>
 * 如何使用 forkjoin
 * 1、forkjoinPool 通过它来执行
 * 2、计算任务 forkjoinPool.execute(ForkJoinTask task)
 * 3. 计算类要继承 ForkJoinTask
 *
 * <p>
 * RecursiveTask 递归任务 有返回值
 * RecursiveAction 递归事件 没有返回值
 *
 * @author ManJiis
 * @since 2021-07-22
 * @since jdk1.8
 */
public class ForkJoinSumTask extends RecursiveTask<Long> {
    /**
     * 1
     */
    private final long start;
    /**
     * 1990900000
     */
    private final long end;

    public ForkJoinSumTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    /**
     * // 计算方法
     *
     * @return /
     */
    @Override
    protected Long compute() {
        // 临界值
        long temp = 10000L;
        if ((end - start) < temp) {
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            // forkjoin 递归

            // 中间值
            long middle = (start + end) / 2;
            ForkJoinSumTask task1 = new ForkJoinSumTask(start, middle);
            task1.fork(); // 拆分任务，把任务压入线程队列
            ForkJoinSumTask task2 = new ForkJoinSumTask(middle + 1, end);
            task2.fork(); // 拆分任务，把任务压入线程队列
            return task1.join() + task2.join();
        }
    }
}