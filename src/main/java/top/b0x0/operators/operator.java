package top.b0x0.operators;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class operator {
    public static void main(String[] args) throws NoSuchAlgorithmException {

//        按位与();
//        按位或();
//        异或();
        取反();

        int a = 16, b = -16, c = 4;
        String binary_16 = Integer.toBinaryString(a);
        String binary_fu16 = Integer.toBinaryString(b);
        String binary_4 = Integer.toBinaryString(c);
        System.out.println("binary_16 = " + binary_16);
        System.out.println("binary_fu16 = " + binary_fu16);
        System.out.println("binary_4 = " + binary_4);

        int right = a >> c;
        int right2 = a >>> c;
        int right3 = b >> c;
        int right4 = b >>> c;
        log.info("{} >> {} = {}", binary_16, c, right);
        log.info("{} >>> {} = {}", binary_16, c, right2);
        log.info("{} >> {} = {}", binary_fu16, c, right3);
        log.info("{} >>> {} = {}", binary_fu16, c, right4);

        short as = 1;
//        int i = "小傅哥".hashCode();
//        System.out.println("i = " + i);
    }

    /**
     * ~ 取反 1为0,0为1
     */
    public static void 取反() {
        // 14的二进制是1110，11的二进制是1011，
        // -11的二进制 给11的二进制取反,然后+1
        // 0000 0000 0000 0000 0000 0000 0000 1011
        // 取反--> 1111 1111 1111 1111 1111 1111 1111 0100
        // 低位+1--> 1111 1111 1111 1111 1111 1111 1111 0101 (这里获取就是-11的二进制)
        int a = 14, b = 11, c = -11;

        // int是32位 补齐前面的0
        // 0000 0000 0000 0000 0000 0000 0000 1110
        // 取反 1111 1111 1111 1111 1111 1111 1111 0001
        // window计算器是64位 补上前面的1 复制到计算器上计算
        // 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 0001
        // 十进制结果为 -15

        int i = ~a;
        log.info("~{} = {}", a, i);

        // 1111 1111 1111 1111 1111 1111 1111 0101
        // 取反 --> 0000 0000 0000 0000 0000 0000 0000 1010 (10)
        int y = ~c;
        log.info("~{} = {}", c, y);
    }

    /**
     * & 运算规则: 两个为真才为真 1&1=1 , 1&0=0 , 0&1=0 , 0&0=0
     */
    public static void 按位与() {
        int a = 14, b = 11, c = -11;

        // 14的二进制是1110，11的二进制是1011，1110&1011
        // 1110
        // 1011
        // 1&1=1，1&0=0，1&1=1，1&0=0
        // 得出结果 1010 ，1010十进制是10
        int i = a & b;
        log.info("{} & {} = {}", a, b, i);

        int y = a & c;
        System.out.println("y = " + y);

        System.out.println("Integer.toBinaryString(a) = " + Integer.toBinaryString(a));
        System.out.println("Integer.toBinaryString(c) = " + Integer.toBinaryString(c));
    }

    /**
     * ^ 运算规则： 1^0 = 1 , 1^1 = 0 , 0^1 = 1 , 0^0 = 0
     */
    public static void 异或() {
        int a = 14, b = 11, c = -11;

        // 14的二进制是1110，11的二进制是1011，1110 ^ 1011
        // 1110
        // 1011
        // 1^1=0，1^0=1，1^1=0，1^0=1
        // 得出结果 0101 ，0101十进制是5
        int i = a ^ b;
        log.info("{} ^ {} = {}", a, b, i);


        int y = a ^ c;
        log.info("{} ^ {} = {}", a, c, y);
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
            4的二进制往左移动3位(高位移出(舍弃))  正数低位补0，负数补1（正数右边补0，负数补1）
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
            16的二进制往右移动3位(低位移出(舍弃))  正数高位补0，负数补1 （正数左边补0，负数补1）
            10000 --》 00010
         */
        // 结果: 2
        System.out.println("right = " + right);

        String binary_16 = Integer.toBinaryString(16); // 10000
        String binary_32 = Integer.toBinaryString(32); // 10
    }

    /**
     * >>>
     * 忽略了符号位扩展，0补最高位
     * 无符号右移规则和右移运算是一样的，只是填充时不管左边的数字是正是负都用0来填充，无符号右移运算只针对[负数]计算，因为对于正数来说这种运算没有意义
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
