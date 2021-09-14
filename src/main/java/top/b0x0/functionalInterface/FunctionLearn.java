package top.b0x0.functionalInterface;

import java.util.function.Function;

/**
 * Function 函数型接口, 有一个输入参数，有一个输出
 * 只要是 函数型接口 可以 用 lambda表达式简化
 *
 * @author ManJiis
 * @since 2021-07-22
 * @since jdk1.8
 */
public class FunctionLearn {
    public static void main(String[] args) {

        //
        // Function<String,String> function = new Function<String,String>() {
        // @Override
        // public String apply(String str) {
        // return str;
        // }
        // };

        Function<String, String> function = (str) -> {
            return str;
        };
        System.out.println(function.apply("asd"));
    }
}