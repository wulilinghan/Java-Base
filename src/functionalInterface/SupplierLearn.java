package functionalInterface;

import java.util.function.Supplier;

/**
 * Supplier 供给型接口 没有参数，只有返回值
 * @author ManJiis
 * @since 2021-07-22
 * @since jdk1.8
 */
public class SupplierLearn {
    public static void main(String[] args) {

        // Supplier supplier = new Supplier<Integer>() {
        // @Override
        // public Integer get() {
        // System.out.println("get()");
        // return 1024;
        // }
        // };

        Supplier supplier = () -> {
            return 1024;
        };
        System.out.println(supplier.get());
    }
}