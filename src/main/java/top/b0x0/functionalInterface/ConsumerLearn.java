package top.b0x0.functionalInterface;

import java.util.function.Consumer;

/**
 * Consumer 消费型接口: 只有输入，没有返回值
 *
 * @author ManJiis
 * @since 2021-07-22
 * @since jdk1.8
 */
public class ConsumerLearn {
    public static void main(String[] args) {

        // Consumer<String> consumer = new Consumer<String>() {
        // @Override
        // public void accept(String str) {
        // System.out.println(str);
        // }
        // };

        Consumer<String> consumer = (str) -> {
            System.out.println(str);
        };
        consumer.accept("sdadasd");
    }
}