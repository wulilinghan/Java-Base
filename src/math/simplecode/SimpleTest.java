package math.simplecode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @program: java-base->SimpleTest
 * @description: 减少程序中过多的if-else、switch-case使用
 * @author: G_Y
 * @since: 2019-08-28 15:14
 * 代码是可鉴赏的艺术品，机器运行只是它的附属功能
 **/
public class SimpleTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        String c = sc.next();
        int b = sc.nextInt();
        //...
        int result = Component.map.get(c).excute(a, b);
        System.out.println(result);
    }

//    private static Map<String, BiFunction<? super Integer,? super Integer, ? extends Integer>> map = new HashMap<>();
//    static {
//        map.put("+", Hand::add);
//        map.put("-", Hand::subtraction);
//    }



//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int a = sc.nextInt();
//        String c = sc.next();
//        int b = sc.nextInt();
//        //...
//        int result = map.get(c).apply(a,b);
//        System.out.println(result);
//    }

    private static Map<String, Function<? super Hand, ? extends Integer>> optMap = new HashMap<>();
    static {
        optMap.put("+", Hand::add);
        optMap.put("-", Hand::subtraction);
    }
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int a = sc.nextInt();
//        String c = sc.next();
//        int b = sc.nextInt();
//        //...
//        int result = new Hand(a,b).execute(optMap.get(c));
//        System.out.println(result);
//    }
}
class Hand {
    public Hand(int a, int b) {
        this.a = a;
        this.b = b;
    }
//    public static Integer add(Integer a, Integer b) {
//        return a + b;
//    }
//    public static Integer subtraction(Integer a, Integer b) {
//        return a - b;
//    }
    private Integer a;
    private Integer b;
    public Integer add() {
        return a + b;
    }
    public Integer subtraction() {
        return a - b;
    }
    public Integer execute(Function<? super Hand, ? extends Integer> function) {
        return  function.apply(this);
    }
}
class Data {

}