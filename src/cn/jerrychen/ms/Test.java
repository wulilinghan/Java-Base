package cn.jerrychen.ms;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
秒杀活动
20个顾客抢10部手机
 */
public class Test {
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2,
                5,
                1,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(15));
        for (int i = 0; i < 20; i++) {
            pool.execute(new MyTask("客户" + i));
        }
    }
}
