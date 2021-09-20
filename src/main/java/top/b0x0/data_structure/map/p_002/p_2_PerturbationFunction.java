package top.b0x0.data_structure.map.p_002;

import java.util.HashMap;

/**
 * 扰动函数
 *
 * @author ManJiis
 * @since 2021-09-20
 * @since JDK1.8
 */
public class p_2_PerturbationFunction {
    public static void main(String[] args) {

        String key = "唐桑";

        int hashCode = key.hashCode();
        int hash = hash(key);

        String format = String.format("hashCode:%s hash:%s", hashCode, hash);
        // hashCode:701761
        // hash:701771
        System.out.println(format);

        // 10101011010101000001
        String keyBinaryString = Integer.toBinaryString(hashCode);
        System.out.println("keyBinaryString = " + keyBinaryString);

        int shiftRight = hashCode >>> 16;
        System.out.println("shiftRight = " + shiftRight);

        int decimal = Integer.parseInt("0000000000000000110111110110010", 2);
        System.out.println("decimal = " + decimal);


        /*
            1. 唐桑.hashCode(): 701761  (Binary: 1010 1011 0101 0100 0001)
            2. 唐桑.hashCode() >>> 16: 0000 0000 0000 0000 1010 1011 0101 0100
            3.
         */
    }

    /**
     * hashmap 中的扰动函数
     *
     * @param key /
     * @return /
     */
    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
