package redis;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @program: java-base->RedisTool
 * @description:
 * @author: G_Y
 * @create: 2019-08-23 16:23
 **/
public class RedisTool {

    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 释放分布式锁
     *
     * @param jedis     Redis客户端
     * @param lockKey   锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

    /**
     * 尝试获取分布式锁
     *
     * @param jedis      Redis客户端
     * @param lockKey    锁
     * @param requestId  请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    private static final String LOCK_SUCCESS = "OK";
    // nxxx NX|XX, NX -- Only set the key if it does not already exist. XX -- Only set the key if it already exist.
    private static final String SET_IF_NOT_EXIST = "NX";
    // expire time units: EX = seconds; PX = milliseconds
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        // nxxx NX|XX, NX -- Only set the key if it does not already exist. XX
        // expire time units: EX = seconds; PX = milliseconds
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

}
