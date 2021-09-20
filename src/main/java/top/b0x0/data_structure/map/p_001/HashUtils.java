package top.b0x0.data_structure.map.p_001;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author ManJiis
 * @since 2021-09-17
 * @since JDK1.8
 */
public class HashUtils {

    /**
     * hashCode()
     *
     * <pre>>
     *  public int hashCode() {
     *       int h = hash;
     *    if (h == 0 && value.length > 0) {
     *          char val[] = value;
     *       for (int i = 0; i < value.length; i++) {
     *             h = 31 * h + val[i];
     *       }
     *           hash = h;
     *      }
     *       return h;
     *   }
     * </pre>
     *
     * @param str        字符串
     * @param multiplier /
     * @return int
     */
    public static int hashCode(String str, Integer multiplier) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = multiplier * hash + str.charAt(i);
        }
        return hash;
    }

    /**
     * Hash 碰撞概率计算
     *
     * @param hashCodeList /
     * @param multiplier   测试因子
     * @return /
     */
    public static HashRateInfo collisionRate(List<Integer> hashCodeList, Integer multiplier) {
        Integer maxHash = hashCodeList.stream().max(Integer::compareTo).get();
        Integer minHash = hashCodeList.stream().min(Integer::compareTo).get();

        int collisionCount = (int) (hashCodeList.size() - hashCodeList.stream().distinct().count());
        double collisionRate = (collisionCount * 1.0) / hashCodeList.size();

        return new HashRateInfo(maxHash, minHash, multiplier, collisionCount, collisionRate);
    }

    public static List<HashRateInfo> collisionRateList(Set<String> strings, Integer... multipliers) {
        List<HashRateInfo> rateInfos = new ArrayList<>();
        HashRateInfo rateInfo;
        for (Integer multiplier : multipliers) {
            ArrayList<Integer> hashCodeList = new ArrayList<>();
            for (String str : strings) {
                hashCodeList.add(hashCode(str, multiplier));
            }
            rateInfo = collisionRate(hashCodeList, multiplier);
            rateInfos.add(rateInfo);
        }
        return rateInfos;
    }

    /**
     * 哈希值分段存放
     * int范围 -2<sup>31</sup> 2<sup>31</sup>-1
     *
     * @param hashCodeList /
     * @return /
     */
    public static Map<Integer, Integer> hashArea(List<Integer> hashCodeList) {
        Map<Integer, Integer> statistics = new LinkedHashMap<>();
        int start = 0;
        for (long i = Integer.MIN_VALUE; i <= Integer.MAX_VALUE; i += 67108864) {
            long min = i;
            long max = min + 67108864;
            // 用这个 67108864 数字是为什么将[-2147483648 2147483648]拆分为128个区间
            // 这里统计散列到每个区间的个数
            int num = (int) hashCodeList.parallelStream().filter(x -> x >= min && x < max).count();
            statistics.put(start++, num);
        }
        return statistics;
    }

    public static Map<Integer, Integer> hashArea(Set<String> strList, Integer multiplier) {
        List<Integer> hashCodeList = new ArrayList<>();
        for (String str : strList) {
            Integer hashCode = hashCode(str, multiplier);
            hashCodeList.add(hashCode);
        }
        return hashArea(hashCodeList);
    }

    public static void main(String[] args) {
//        int start = 0;
//        for (long i = Integer.MIN_VALUE; i <= Integer.MAX_VALUE; i += 67108864) {
//            long min = i;
//            long max = min + 67108864;
//            start++;
//            String format1 = String.format(" %s [%s %s]", start, min, max);
//            System.out.println(format1);
//        }

        long minValue = Math.abs((long) Integer.MIN_VALUE);
        int maxValue = Integer.MAX_VALUE;
        int i = maxValue / (128 / 2);
        System.out.println("i = " + i);

    }

    /**
     * 读取本地文件，单词表
     *
     * @param uri 单词表.txt文件
     * @return 单词集合(去重)
     */
    public static Set<String> readTxt(String uri) {
        Set<String> set = new HashSet<>();
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(uri), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] ss = line.split("\t");
                set.add(ss[1]);
            }
            br.close();
            isr.close();
        } catch (Exception ignore) {
            return null;
        }
        return set;
    }
}
