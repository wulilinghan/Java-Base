package top.b0x0.data_structure.map.p_002;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 简单散列表实现
 *
 * @author ManJiis
 * @since 2021-09-20
 * @since JDK1.8
 */
public class p_1_SimpleHashTabDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Shenzhen");
        list.add("唐桑和歌但");
        list.add("Operation");
        list.add("Allan");
        list.add("Tom");
        list.add("hello");
        list.add("jjjj");
        list.add("MOMO");
        list.add("luola");
        list.add("The Master Hunter");

        String[] strings = new String[8];

        for (String key : list) {
            int idx = calculateTheSubscript(key, strings);
            String format = String.format("key = %s idx = %s", key, idx);
            System.out.println(format);
            if (strings[idx] == null) {
                strings[idx] = key;
                continue;
            }
            strings[idx] = strings[idx] + "->" + key;
        }

        System.out.println("strings = " + Arrays.toString(strings));
    }

    /**
     * 计算key在数组中的下标
     *
     * @param key     /
     * @param strings String[]
     * @return int idx
     */
    public static int calculateTheSubscript(String key, String... strings) {
        return key.hashCode() & (strings.length - 1);
    }
}
