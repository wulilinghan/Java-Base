package top.b0x0.base.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.b0x0.data_structure.map.p_001.HashRateInfo;
import top.b0x0.data_structure.map.p_001.HashUtils;
import top.b0x0.data_structure.map.p_002.p_2_HashMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author ManJiis
 * @since 2021-09-17
 * @since JDK1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class P_001_HashMap_Test {
    private static final Logger log = LoggerFactory.getLogger(P_001_HashMap_Test.class);

    private Set<String> words;

    @Before
    public void before() {
        "aa".hashCode();

        String uri = "D:\\work-space\\ower\\Java-Base\\src\\main\\java\\top\\b0x0\\data_structure\\map\\p_001\\103976个英语单词库.txt";
        words = HashUtils.readTxt(uri);
//        System.out.println("words = " + words);
    }

    /**
     * 测试 String.hashcode() 方法不同乘积因子(2, 3, 5, 7, 17, 31, 32, 33, 39, 41, 199)的碰撞率
     */
    @Test
    public void test_collisionRate() {
        Integer[] factory = {2, 3, 5, 7, 17, 31, 32, 33, 39, 41, 199};
        List<HashRateInfo> hashRateInfos = HashUtils.collisionRateList(words, factory);
        for (HashRateInfo rateInfo : hashRateInfos) {

            int multiplier = rateInfo.getMultiplier();
            int minHash = rateInfo.getMinHash();
            int maxHash = rateInfo.getMaxHash();
            int collisionCount = rateInfo.getCollisionCount();
            double collisionRate = rateInfo.getCollisionRate();

            String format = String.format("乘数因子:%s 最小hash:%s 最大hash:%s 碰撞次数:%s 碰撞概率:%.4f%% ",
                    multiplier, minHash, maxHash, collisionCount, collisionRate * 100);

            System.out.println(format);

            /*
             * 使用 31 作为hashcode方法的乘积因子hash碰撞的概率最小，数据散列比较均匀，并且计算出的hash值也不会超过int的范围，不会丢失数据
             */

        }
    }

    /**
     * 测试 String.hashcode() 方法不同乘积因子(2, 3, 5, 7, 17, 31, 32, 33, 39, 41, 199)的的散列值的分布情况
     */
    @Test
    public void test_hashArea() {
        System.out.println(HashUtils.hashArea(words, 2).values());
        System.out.println(HashUtils.hashArea(words, 7).values());
        System.out.println(HashUtils.hashArea(words, 31).values());
        System.out.println(HashUtils.hashArea(words, 32).values());
        System.out.println(HashUtils.hashArea(words, 199).values());
    }

    /**
     * 测试扰动函数效果
     */
    @Test
    public void test_disturb() {
        HashMap<Integer, Integer> map = new HashMap<>(16);
        for (String word : words) {
            // 使用扰动函数
//            int idx = p_2_HashMethod.hashIdx(word, 128);
            int idx = p_2_HashMethod.noHashIdx(word, 128);
            if (map.containsKey(idx)) {
                Integer o = map.get(idx);
                map.put(idx, ++o);
            } else {
                map.put(idx, 1);
            }
        }

        for (Integer key : map.keySet()) {
//            log.info("{} - {}", key, map.get(key));
            String format = String.format("%s - %s", key, map.get(key));
            System.out.println(format);
        }
    }


}
