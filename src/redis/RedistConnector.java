package redis;

import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * @program: java-base->RedistConnector
 * @description:
 * @author: G_Y
 * @since: 2019-08-23 12:50
 **/
public class RedistConnector {

    public static Jedis connect() {
//        不使用连接池
        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        jedis.auth("admin");
        //ping通显示PONG
//        System.out.println(jedis.ping());//去ping我们redis的主机所在ip和端口

        //从redis 连接池中获取
//        Jedis jedis = JedisPoolTest.getJedis();
        return jedis;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    Jedis connect = connect();
                    f(connect);
                }
            });
            t1.start();
        }
    }

    private static void f(Jedis connect) {
        long key;
        while ((key = connect.setnx("key", "1")) != 1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            continue;
        }
        System.out.println("getLocker" + key);
        for (int i = 1; i <= 100; i++) {
            System.out.println(i);
        }
        String s = connect.get("KEY");
        if (StringUtils.isEmpty(s)) {
            s = "0";
        }
        Integer in = Integer.parseInt(s);
        in++;
        connect.set("KEY", in.toString());
        System.out.println("---------------------");
        connect.del("key");
    }

}
