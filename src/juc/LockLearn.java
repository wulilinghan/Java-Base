package juc;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

/**
 * @program: java-base->LockLearn
 * @description:
 * @author: G_Y
 * @create: 2019-08-21 13:43
 **/
public class LockLearn {
    // 商品key:value——> id:库存
    private static HashMap<Integer, Integer> productStock = new HashMap<>();

    static {
        productStock.put(0, 5);
        productStock.put(1, 5);
        productStock.put(2, 5);
    }

    public static void main(String[] args) {


        Random random = new Random();

//        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
//        ReadLock readLock = reentrantReadWriteLock.readLock();
//        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 1, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(200), new ThreadPoolExecutor.CallerRunsPolicy());

        // 200 个用户并发 i 即 用户id
        for (int i = 1; i <= 200; i++) {
            int userId = i;
            // 分别抢3种秒杀商品
            int goodsId = random.nextInt(3);
            ReentrantReadWriteLock lock = LockLearn.getLockByGoodsId(goodsId);
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    ReadLock readLock1 = lock.readLock();
                    readLock1.lock();
                    try {
                        Integer stock1 = productStock.get(goodsId);
                        if(stock1<1) {
                            System.out.println("用户"+userId+"抢"+goodsId+"号商品失败，库存不足");
                            return;
                        }
                    } finally {
                        readLock1.unlock();
                    }

                    ReentrantReadWriteLock.WriteLock writeLock1 = lock.writeLock();
                    writeLock1.lock();
                    try {
                        Integer stock = productStock.get(goodsId);
                        if(stock > 0) {
                            productStock.put(goodsId,stock-1);
                            System.out.println("用户："+userId+"买到了一个"+goodsId+"号商品");
                        } else {
                            System.out.println("用户"+userId+"抢"+goodsId+"号商品失败，库存不足");
                        }
                    } finally {
                        writeLock1.unlock();
                    }
                }
            });

        }
    }

    private static Map<Integer,ReentrantReadWriteLock> lockMap = new ConcurrentHashMap<>();

    public static ReentrantReadWriteLock getLockByGoodsId(int goodsId) {
        ReentrantReadWriteLock reentrantReadWriteLock = null;
        if((reentrantReadWriteLock = lockMap.get(goodsId)) != null)
            return reentrantReadWriteLock;
        ReentrantReadWriteLock reentrantReadWriteLock1 = new ReentrantReadWriteLock();
        reentrantReadWriteLock = lockMap.putIfAbsent(goodsId, reentrantReadWriteLock1);
        if(reentrantReadWriteLock == null) {
            return reentrantReadWriteLock1;
        }
        return reentrantReadWriteLock;
    }

}
