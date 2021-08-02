package juc.ForkJoin;

import java.util.concurrent.*;
import java.util.stream.LongStream;

/**
 * ForkJoin
 * ForkJoin 在 JDK 1.7 ， 并行执行任务！提高效率。大数据量！
 * 大数据：Map Reduce （把大任务拆分为小任务
 *
 * ForkJoin 特点：工作窃取
 * 这个里面维护的都是双端队列
 *
 * @author ManJiis
 * @since 2021-07-22
 * @since jdk1.8
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        // 8407
        threadPool.execute(Test::test1);

        // 4358
        threadPool.execute(() -> {
            try {
                test2();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 3138
        threadPool.execute(Test::test3);

        threadPool.shutdown();
    }


    /**
     * 普通程序员
     */
    public static void test1() {
        //设置用几核处理数据,可以多调调找到最合适的值，因机器而异（一般不建议改变，用默认值是比较合理的）
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");
        long sum = 0L;
        long start = System.currentTimeMillis();
        for (long i = 1L; i <= 100_0000_0000L; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("test1 sum=" + sum + " 时间：" + (end - start));
    }


    /**
     * 会使用ForkJoin的程序员
     *
     * @throws ExecutionException   /
     * @throws InterruptedException /
     */
    public static void test2() throws ExecutionException, InterruptedException {
        //设置用几核处理数据,可以多调调找到最合适的值，因机器而异（一般不建议改变，用默认值是比较合理的）
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinSumTask(0L, 100_0000_0000L);
        // 提交任务
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        Long sum = submit.get();
        long end = System.currentTimeMillis();
        System.out.println("test2 sum=" + sum + " 时间：" + (end - start));
    }

    /**
     * 会使用Stream并行流的程序员
     */
    public static void test3() {
        //设置用几核处理数据,可以多调调找到最合适的值，因机器而异（一般不建议改变，用默认值是比较合理的）
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");
        long start = System.currentTimeMillis();
        // Stream并行流 ()
        long sum = LongStream.rangeClosed(0L, 100_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("test3 sum=" + sum + "时间：" + (end - start));
    }
}