package top.b0x0.functionalInterface;

import java.util.function.Predicate;

/**
 * 断定型接口：有一个输入参数，返回值只能是 布尔值！
 *
 * @author ManJiis
 * @since 2021-07-22
 * @since jdk1.8
 */
public class PredicateLearn {
    public static void main(String[] args) {

        // 判断字符串是否为空
        // Predicate<String> predicate = new Predicate<String>(){
        //// @Override
        //// public boolean test(String str) {
        //// return str.isEmpty();
        //// }
        //// };

        Predicate<String> predicate = (str) -> {
            return str.isEmpty();
        };
        System.out.println(predicate.test(""));
    }
}