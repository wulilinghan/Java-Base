package top.b0x0.data_structure.map.p_002;

import java.util.HashMap;

/**
 * 扰动函数
 *
 * @author ManJiis
 * @since 2021-09-20
 * @since JDK1.8
 */
public class p_2_HashMethod {
    public static void main(String[] args) {

        String key = "唐桑";

        int hashCode = key.hashCode();
        int hash = hash(key);
        String format = String.format("hashCode:%s hash:%s", hashCode, hash); // hashCode:701761 hash:701771

        String keyBinaryString = Integer.toBinaryString(hashCode); // 10101011010101000001
        int shiftRight = hashCode >>> 16; //1010

        HashMap<String, Object> map = new HashMap<>(16);
        map.put(key, key);
        System.out.println("map = " + map);

        System.out.println("Integer.toBinaryString(15) = " + Integer.toBinaryString(15));

        /*
         key下标计算 先经过扰动函数生成一个新的hashCode 然后与数组长度-1进行 & 计算:

                    1. 唐桑.hashCode(): 701761  (701761二进制: 1010 1011 0101 0100 0001)
                    2. 唐桑.hashCode() >>> 16: 1010
                    3. 唐桑.hashCode() ^ (唐桑.hashCode() >>> 16)
                        0000 0000 0000 1010 1011 0101 0100 0001 ^ 0000 0000 0000 0000 0000 0000 0000 1010
                        ---------------------------------------------------------------------------------
                        异或计算 (1^0 = 1 , 1^1 = 0 , 0^1 = 1 , 0^0 = 0)
                        0000 0000 0000 1010 1011 0101 0100 0001
                        0000 0000 0000 0000 0000 0000 0000 1010
                   结果: 0000 0000 0000 1010 1011 0101 0100 1011 (这是经过扰动函数后的hashCode 701771)
                    4. (唐桑.hashCode() ^ (唐桑.hashCode() >>> 16)) & (tab.length - 1)
                        0000 0000 0000 1010 1011 0101 0100 1011 & 15(十进制 15的二进制是 1111)
                                                                & 1111
                        ----------------------------------------------------------------------------------
                        按位与计算 (两个为真才为真 1&1=1 , 1&0=0 , 0&1=0 , 0&0=0)
                        0000 0000 0000 1010 1011 0101 0100 1011
                        0000 0000 0000 0000 0000 0000 0000 1111
                   结果: 0000 0000 0000 0000 0000 0000 0000 1011  --> 1011 (二进制)
                   下标为 1011十进制为 11
         */
    }

    /**
     * hashmap 中的扰动函数 返回一个新的hash值
     * 哈希值右移 16 位，也就正好是自己长度的一半，之后与原哈希值做异或运算，这样就混合了原哈希值中的高位和低位，增大了随机性
     *
     * @param key /
     * @return /
     */
    private static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * 不经过扰动
     *
     * @param key /
     * @return /
     */
    private static int noHash(Object key) {
        return (key == null) ? 0 : key.hashCode();
    }

    /**
     * 经过扰动函数计算出来的key下标
     *
     * @param key     /
     * @param tabSize 数组长度
     * @return /
     */
    public static int hashIdx(Object key, int tabSize) {
        return hash(key) & (tabSize - 1);
    }

    /**
     * 不经过扰动函数计算出来的key下标
     *
     * @param key     /
     * @param tabSize 数组长度
     * @return /
     */
    public static int noHashIdx(Object key, int tabSize) {
        return noHash(key) & (tabSize - 1);
    }

}
