package top.b0x0.juc.locks;

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
 * 商品抢购
 *
 * @author ManJiis
 * @program java-base->LockLearn
 * @since 2019-08-21 13:43
 **/
public class LocksLearn {

    // 商品key:value——> id:库存
    private static final HashMap<Integer, Integer> PRODUCT_STOCK = new HashMap<>();

    static {
        PRODUCT_STOCK.put(0, 5);
        PRODUCT_STOCK.put(1, 5);
        PRODUCT_STOCK.put(2, 5);
    }

    private static final Map<Integer, ReentrantReadWriteLock> READ_WRITE_LOCK_MAP = new ConcurrentHashMap<>();

    public static ReentrantReadWriteLock getLockByGoodsId(int goodsId) {
        ReentrantReadWriteLock reentrantReadWriteLock = null;
        if ((reentrantReadWriteLock = READ_WRITE_LOCK_MAP.get(goodsId)) != null) {
            return reentrantReadWriteLock;
        }
        ReentrantReadWriteLock reentrantReadWriteLock1 = new ReentrantReadWriteLock();
        reentrantReadWriteLock = READ_WRITE_LOCK_MAP.putIfAbsent(goodsId, reentrantReadWriteLock1);
        if (reentrantReadWriteLock == null) {
            return reentrantReadWriteLock1;
        }
        return reentrantReadWriteLock;
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
            // [0,1,2]
            int goodsId = random.nextInt(3);
            ReentrantReadWriteLock lock = LocksLearn.getLockByGoodsId(goodsId);
            threadPoolExecutor.execute(() -> {

                // 获取读锁
                ReadLock readLockProduct = lock.readLock();
                readLockProduct.lock();
                try {
                    Integer stock1 = PRODUCT_STOCK.get(goodsId);
                    if (stock1 < 1) {
                        System.out.println("用户" + userId + "抢" + goodsId + "号商品失败，库存不足");
                        return;
                    }
                } finally {
                    readLockProduct.unlock();
                }

                // 获取写锁
                ReentrantReadWriteLock.WriteLock writeLockProduct = lock.writeLock();
                writeLockProduct.lock();
                try {
                    Integer stock = PRODUCT_STOCK.get(goodsId);
                    if (stock > 0) {
                        PRODUCT_STOCK.put(goodsId, stock - 1);
                        System.out.println("用户：" + userId + "买到了一个" + goodsId + "号商品");
                    } else {
                        System.out.println("用户" + userId + "抢" + goodsId + "号商品失败，库存不足");
                    }
                } finally {
                    writeLockProduct.unlock();
                }
            });

        }

        threadPoolExecutor.shutdown();
    }


}
