package redis;

import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: java-base->RedistTest1
 * @description:
 * @author: G_Y
 * @since: 2019-08-23 12:49
 **/
public class RedistTest1 {
    public static String KEY = "KEY";
    public static String LOCKER = "LOCKER";
    public final static Jedis connect_static = RedistConnector.connect();
    static final CountDownLatch countDownLatch = new CountDownLatch(50);

    static ThreadPoolExecutor pool = new ThreadPoolExecutor(4,
            6, 2, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.CallerRunsPolicy());

    static {
//        connect_static.set(KEY, "0");
    }

    public static void main(String[] args) {

        RedistTest1 redisTest1 = new RedistTest1();

        for (int i = 1; i <= 50; i++) {
            pool.execute(new MyTask2(redisTest1));
        }
        pool.shutdown();
        System.out.println("1——————pool.isTerminated——1——" + pool.isTerminated());
        while (!pool.isTerminated()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            continue;
        }
        System.out.println("1——————pool.isTerminated——" + pool.isTerminated());
        while (countDownLatch.getCount() != 0) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            continue;
        }
        System.out.println(countDownLatch.getCount());
        String s = connect_static.get(KEY);
        System.out.println(s);
    }

    public synchronized void add() {
//        while (connect_static.setnx(LOCKER, "1").equals(0)) {
        String requestID = UUID.randomUUID().toString();
        while (!RedisTool.tryGetDistributedLock(connect_static,RedistTest1.LOCKER,requestID,5000)) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            continue;
        }
//        connect_static.watch(LOCKER);
        try {
            String s = connect_static.get(KEY);
            if (StringUtils.isEmpty(s)) {
                s = "0";
            }
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Integer i = Integer.parseInt(s);
            i++;
            connect_static.set(KEY, i.toString());
        } finally {
//            connect_static.del(LOCKER);
            boolean b = RedisTool.releaseDistributedLock(connect_static, RedistTest1.LOCKER, requestID);
            if(!b){
                System.out.println("无法释放锁");
            }
            countDownLatch.countDown();
        }
//        Transaction multi = connect_static.multi();
//        multi.del(LOCKER);
//        String watch = connect_static.unwatch();
    }

}

class MyTask2 implements Runnable {
    RedistTest1 r;

    public MyTask2(RedistTest1 r) {
        this.r = r;
    }

    @Override
    public void run() {
        r.add();
    }
}