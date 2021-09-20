package top.b0x0.base.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.lang.model.element.VariableElement;

/**
 * String 的一些方法测试
 *
 * @author ManJiis
 * @since 2021-09-20
 * @since JDK1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StringMethodTest {

    @Test
    public void test_supplementarySpace() {
        // 长度不足 前面补充空格
        String format = String.format("%5s", "_AA");
        System.out.println("format = " + format);

        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        String format2 = String.format("%04d", 101);//0101
        System.out.println("format2 = " + format2);

        // 35代表字符串总长度，如果字符串本身不够35，则会在其后补空格凑位数
        System.out.println("java后面补空格:" + String.format("%1$-35s", "AA_BB_CC"));

    }

    @Test
    public void test_hashCode() {
        String s = "唐桑";
        int hashCode = s.hashCode();
        System.out.println("hashCode = " + hashCode);
    }

    /**
     * baseConversion
     */
    @Test
    public void test_baseConversion() {
        // 1. 唐桑和歌但.hashCode(): -547002843
        //    (唐桑和歌但.hashCode().Binary: 110111110110010 10110011000100101)
        String key = "唐桑和歌但";
        int hashCode = key.hashCode();

        // 把key的hashcode转为二进制
        String keyBinaryString = Integer.toBinaryString(hashCode);
        System.out.println("keyBinaryString = " + keyBinaryString);
        int keyDecimal = Integer.valueOf("11011111011001010110011000100101", 2);

        String format = String.format("keyBinaryString:%s keyDecimal:%s", keyBinaryString, keyDecimal);
        System.out.println(format);

        // 二进制转十进制
        int decimal = Integer.parseInt("0000000000000000110111110110010", 2);
        System.out.println("decimal = " + decimal);
    }

    /**
     * Java 二进制，八进制，十进制，十六进制转换
     */
    @Test
    public void test_baseConversion_2() {
        int i = 547002843;
        System.out.println("---------------------------------");
        System.out.println("十进制转二进制：" + Integer.toBinaryString(i));
        System.out.println("十进制转八进制：" + Integer.toOctalString(i));
        System.out.println("十进制转十六进制：" + Integer.toHexString(i));
        System.out.println("---------------------------------");
        System.out.println("二进制转十进制：" + Integer.valueOf("100000100110101001100111011011", 2));
        System.out.println("八进制转十进制：" + Integer.valueOf("4046514733", 8));
        System.out.println("十六进制转十进制：" + Integer.valueOf("209a99db", 16));
        System.out.println("---------------------------------");

        /*
            负数使用此方式做进制转换 抛异常
         */
        int i1 = -547002843;
//        System.out.println("十进制转二进制：" + Integer.toBinaryString(i1));
//        System.out.println("十进制转八进制：" + Integer.toOctalString(i1));
//        System.out.println("十进制转十六进制：" + Integer.toHexString(i1));
//        System.out.println("---------------------------------");
//        System.out.println("二进制转十进制：" + Integer.valueOf("11011111011001010110011000100101", 2));
//        System.out.println("八进制转十进制：" + Integer.valueOf("33731263045", 8));
//        System.out.println("十六进制转十进制：" + Integer.valueOf("df656625", 16));
//        System.out.println("---------------------------------");
    }

    @Test
    public void test_division() {

        int minValue = Integer.MIN_VALUE;
        int maxValue = Integer.MAX_VALUE;
        String format = String.format("minValue:%s maxValue:%s", minValue, maxValue);
        System.out.println(format);

        long minAbs = Math.abs((long) minValue);
        String format2 = String.format("minAbs:%s maxValue:%s", minAbs, maxValue);
        System.out.println("format2 = " + format2);

        int start = 0;
        for (long i = Integer.MIN_VALUE; i <= Integer.MAX_VALUE; i += 67108864) {
            long min = i;
            long max = min + 67108864;
            start++;
            String format1 = String.format(" %s [%s %s]", start, min, max);
            System.out.println(format1);
        }
    }
}
