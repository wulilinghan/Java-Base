package juc.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: java-base->ThreadPoolExcutorLearn
 * @description:
 * @author: G_Y
 * @since: 2019-08-21 13:42
 **/
public class ThreadPoolExcutorLearn {

    private static List<User> users = new ArrayList<>(100);

    static {
        init();
    }

    public static void main(String[] args) {
//        MyRejectedExecutionHandler myRejectedExecutionHandler = new MyRejectedExecutionHandler();
        // 核心线程，总线程，超时归还时间，时间单位，线程任务队列，拒绝策略(调用线程执行)
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1,
                2, 2L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());
        // 设置主线程 超时归还
        pool.allowCoreThreadTimeOut(true);

        for (final User user : users) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("this is user age = " + user.getAge());
                }
            });
        }
        // 不让继续添加线程任务
        pool.shutdown();
        // 查看是否执行完毕
        System.out.println("isTerminated = " + pool.isTerminated());
    }

    public static void init() {
        for (int i = 0; i < users.size(); i++) {
            users.add(new User(i));
        }
    }
}

class User {
    private Integer age;

    public User(int i) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}