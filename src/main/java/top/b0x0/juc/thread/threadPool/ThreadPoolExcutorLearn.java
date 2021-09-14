package top.b0x0.juc.thread.threadPool;

import top.b0x0.common.NamedThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author ManJiis
 * @since 2019-08-21 13:42
 **/
public class ThreadPoolExcutorLearn {

    private static final List<User> users = new ArrayList<>(100);

    public static void init() {
        for (int i = 0; i < 20; i++) {
            users.add(new User(i));
        }
    }

    static {
        init();
    }


    public static void main(String[] args) {
        /*
         *  1） FixedThreadPool 和 SingleThreadPool：
         *      允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM。
         *  2） CachedThreadPool：
         *      允许的创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致 OOM。
         */

        // 1. 单个线程
        // ExecutorService threadPool = Executors.newSingleThreadExecutor();

        // 2. 创建一个固定的线程池的大小
        // ExecutorService threadPool = Executors.newFixedThreadPool(5);

        // 3. 可伸缩的，遇强则强，遇弱则弱
        // ExecutorService threadPool = Executors.newCachedThreadPool();

        // 核心线程，总线程，超时归还时间，时间单位，线程任务队列，拒绝策略(调用线程执行)
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                1, 2, 2L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new NamedThreadFactory("ThreadPoolExcutorLearn", false),
                new ThreadPoolExecutor.CallerRunsPolicy());
        /*
         *  拒绝策略
         * new ThreadPoolExecutor.AbortPolicy() // 银行满了，还有人进来，不处理这个人的，抛出异常
         * new ThreadPoolExecutor.CallerRunsPolicy() // 哪来的去哪里！
         * new ThreadPoolExecutor.DiscardPolicy() //队列满了，丢掉任务，不会抛出异常！
         * new ThreadPoolExecutor.DiscardOldestPolicy() //队列满了，尝试去和最早的竞争，也不会抛出异常！
         */

        // 设置主线程 超时归还
        pool.allowCoreThreadTimeOut(true);

        /*
         * 1. 当线程池小于corePoolSize时，新提交任务将创建一个新线程执行任务，即使此时线程池中存在空闲线程。
         * 2. 当线程池达到corePoolSize时，新提交任务将被放入workQueue中，等待线程池中任务调度执行
         * 3. 当workQueue已满，且maximumPoolSize>corePoolSize时，新提交任务会创建新线程执行任务
         * 4. 当提交任务数超过maximumPoolSize时，新提交任务由RejectedExecutionHandler处理
         * 5. 当线程池中超过corePoolSize线程，空闲时间达到keepAliveTime时，关闭空闲线程
         * 6. 当设置allowCoreThreadTimeOut(true)时，线程池中corePoolSize线程空闲时间达到keepAliveTime也将关闭
         *
         * 可以看出，allowCoreThreadTimeOut这个方法就像其字面的意思一样，允许Core Thread超时后可以关闭。
         */

        for (User user : users) {
            pool.execute(() -> {
//                try {
//                    TimeUnit.MILLISECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println("this is user age = " + user.getAge());
            });
        }
        // 不让继续添加线程任务
        pool.shutdown();

        // 查看是否执行完毕
        System.out.println("isTerminated = " + pool.isTerminated());
    }


}

class User {
    private Integer age;

    public User() {
    }

    public User(Integer i) {
        this.age = i;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                '}';
    }
}