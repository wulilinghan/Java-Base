package top.b0x0.operators;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * java运算符
 *
 * @author ManJiis
 * @since 2021-07-30
 * @since JDK1.8
 */
public class operator {
    public static void main(String[] args) throws NoSuchAlgorithmException {

//        按位与();
        按位或();
    }

    /**
     * & 运算规则: 两个为真才为真
     * 1&1=1 , 1&0=0 , 0&1=0 , 0&0=0
     */
    public static void 按位与() {
        int a = 14, b = 11, c = -11;

        // 14的二进制是1110，11的二进制是1011，1110&1011
        // 1110
        // 1011
        // 1&1=1，1&0=0，1&1=1，1&0=0
        // 得出结果 1010 ，1010十进制是10
        int i = a & b;
        System.out.println("i = " + i);

        int y = a & c;
        System.out.println("y = " + y);

        System.out.println("Integer.toBinaryString(a) = " + Integer.toBinaryString(a));
        System.out.println("Integer.toBinaryString(c) = " + Integer.toBinaryString(c));
    }

    /**
     * | 运算规则：一个为真即为真
     * 1|0 = 1 , 1|1 = 1 , 0|0 = 0 , 0|1 = 1
     */
    public static void 按位或() {
        int a = 14, b = 11, c = -11;

        // 14的二进制是1110，11的二进制是1011，1110|1011
        // 1110
        // 1011
        // 1|1=1，1|0=1，1|1=1，1|0=1
        // 得出结果 1111 ，1111十进制是15
        int i = a | b;
        System.out.println("i = " + i);

        int y = a | c;
        System.out.println("y = " + y);
    }

    /**
     * 带符号左位移
     * << 在数字没有溢出的前提下，对于正数和负数，左移一位都相当于乘以2的1次方，左移n位就相当于乘以2的n次方。
     */
    public static void 带符号左位移() {
        // 4 乘 2的3次方
        int left = 4 << 3; // 32
        /*
            4的二进制往左移动3位  正数低位补0，负数补1
            100 --》 100000
         */
        // 结果: 32
        System.out.println("left = " + left);

        String binary_4 = Integer.toBinaryString(4); // 100
        String binary_32 = Integer.toBinaryString(32); // 100000
    }

    /**
     * 带符号右移
     * >> 右移一位相当于除2，右移n位相当于除以2的n次方。
     */
    public static void 带符号右位移() {
        // 16 除 2的3次方
        int right = 16 >> 3;
        /*
            16的二进制往右移动3位  正数高位补0，负数补1
            10000 --》 00010
         */
        // 结果: 2
        System.out.println("right = " + right);

        String binary_16 = Integer.toBinaryString(16); // 10000
        String binary_32 = Integer.toBinaryString(32); // 10
    }

    /**
     * >>>
     */
    public static void 无符号右位移() {
        String key = "唐桑";
        int hashCode = key.hashCode(); // 701761
        String binaryString = Integer.toBinaryString(hashCode); // 1010 1011 0101 0100 0001

        // 无符号右移运算 >>>
        int shiftRight = hashCode >>> 8; // 2741
        String binaryString1 = Integer.toBinaryString(shiftRight);  // 1010 1011 0101
        /**/
        int shiftRight2 = hashCode >>> 16; //10
        String binaryString2 = Integer.toBinaryString(shiftRight2); // 1010

        String binaryString3 = Integer.toBinaryString(-6);
        System.out.println("binaryString3 = " + binaryString3);
    }
}
