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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static com.sun.xml.internal.fastinfoset.util.ValueArray.MAXIMUM_CAPACITY;

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

    public int length_1(int i) {
        return i - 1;
    }

    @Test
    public void test_length_1() {
        int i = length_1(2);
        String binaryString = Integer.toBinaryString(i);
        System.out.println("2 binaryString = " + binaryString);
        i = length_1(16);
        binaryString = Integer.toBinaryString(i);
        System.out.println("16 binaryString = " + binaryString);
        i = length_1(5);
        binaryString = Integer.toBinaryString(i);
        System.out.println("5 binaryString = " + binaryString);
        i = length_1(15);
        binaryString = Integer.toBinaryString(i);
        System.out.println("15 binaryString = " + binaryString);
    }

    static final int MAXIMUM_CAPACITY = 1 << 30;

    static int tableSizeFor(int cap) {
        int n = cap - 1;
        System.out.println(n + " = " + cap + " - 1 " + Integer.toBinaryString(n));
        n |= n >>> 1;
        System.out.println(n + " |= " + n + " >>>1 " + Integer.toBinaryString(n));
        n |= n >>> 2;
        System.out.println(n + " |= " + n + " >>>2 " + Integer.toBinaryString(n));
        n |= n >>> 4;
        System.out.println(n + " |= " + n + " >>>4 " + Integer.toBinaryString(n));
        n |= n >>> 8;
        System.out.println(n + " |= " + n + " >>>8 " + Integer.toBinaryString(n));
        n |= n >>> 16;
        System.out.println(n + " |= " + n + " >>>16 " + Integer.toBinaryString(n));
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    @Test
    public void test_tableSizeFor() {
//        int i = tableSizeFor(17);
        //16 - 10000
        //24 - 11000
        //30 - 11110
        //31 - 11111
        //31 - 11111
        //31 - 11111
        //i = 32

//        int i = tableSizeFor(31);
        //30 - 11110
        //31 - 11111
        //31 - 11111
        //31 - 11111
        //31 - 11111
        //31 - 11111
        //i = 32

//        int i = tableSizeFor(100);
        //99 - 1100011
        //115 - 1110011
        //127 - 1111111
        //127 - 1111111
        //127 - 1111111
        //127 - 1111111
        //i = 128

        int i = tableSizeFor(129);
        //128 - 10000000
        //192 - 11000000
        //240 - 11110000
        //255 - 11111111
        //255 - 11111111
        //255 - 11111111
        //i = 256

        System.out.println("i = " + i);
    }

    @Test
    public void test_1111() {

    }

    @Test
    public void test_tableSizeFor_ex() {
        int i = 17;
        i += i - 1; // 17 + (16-1) = 33
        i += i - 2; // 33 + (33-2) = 64

        // i = 64
        System.out.println("i = " + i);
    }

    /**
     * 扩容元素拆分
     * 为什么扩容，因为数组长度不足了。那扩容最直接的问题，就是需要把元素拆分
     * 到新的数组中。拆分元素的过程中，原 jdk1.7 中会需要重新计算哈希值，但是
     * 到 jdk1.8 中已经进行优化，不在需要重新计算
     */
    @Test
    public void test_hashMap() {
        List<String> list = new ArrayList<>();
        list.add("jlkk");
        list.add("lopi");
        list.add("jmdw");
        list.add("e4we");
        list.add("io98");
        list.add("nmhg");
        list.add("vfg6");
        list.add("gfrt");
        list.add("alpo");
        list.add("vfbh");
        list.add("bnhj");
        list.add("zuio");
        list.add("iu8e");
        list.add("yhjk");
        list.add("plop");
        list.add("dd0p");

        int tab16 = 16;
        int tab32 = 32;
        for (String key : list) {
            int hash = key.hashCode() ^ (key.hashCode() >>> 16);
//            System.out.println("字符串：" + key + " \tIdx(16)：" + hashIdx(hash, 16) + " \thash_Binary值：" + Integer.toBinaryString(hash) + " - " + Integer.toBinaryString(hash & 16) + " \t\tIdx(32)：" + hashIdx(hash, 32));
//            System.out.println(Integer.toBinaryString(key.hashCode()) + " " + Integer.toBinaryString(hash) + " " + Integer.toBinaryString(hashIdx(hash, 32)));
//            System.out.println("字符串：" + key);
//            System.out.println("tab[16] idx：" + hashIdx(hash, 16) + "hash_Binary值：" + Integer.toBinaryString(hash) + " - " + Integer.toBinaryString(hash & 16));
//            System.out.println("tab[32] idx：" + hashIdx(hash, 32));
        }
    }

    static int hashIdx(int hash, int size) {
        return hash & (size - 1);
    }
}
