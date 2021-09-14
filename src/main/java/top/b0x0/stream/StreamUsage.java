package top.b0x0.stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 * jdk8 top.b0x0.stream 用法
 *
 * @author ManJiis
 * @since 2021-08-23
 * @since JDK1.8
 */
public class StreamUsage {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 21, "17611111111");
        User u2 = new User(2, "b", 22, "17622222222");
        User u3 = new User(3, "c", 23, "17633333333");
        User u4 = new User(4, "c", 24, "17644444444");
        User u5 = new User(5, "e", 25, "17644444444");
        User u6 = new User(6, "e", 26, "17688888888");
        User u7 = new User(7, "d", 18, "17688888888");
        User u8 = new User(7, "e", 18, "17699999999");

        // 集合就是存储
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5, u6, u7, u8);

        // Java8 top.b0x0.stream 对象 List 根据的某一字段将对象分组为 Map
        Map<String, List<User>> userGroupingByMap = list.stream().collect(Collectors.groupingBy(User::getName));
        System.out.println("userGroupingByMap = " + userGroupingByMap);

        // 提取对象 List 中的某一字段生成新的 List
        List<Integer> newList = list.stream().map(User::getId).collect(Collectors.toList());
        System.out.println("newList = " + newList);

        // 实现对象List根据对象某一指定字段进行去重操作
        //根据 user 对象的 phone 进行去重
        ArrayList<User> filterList = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(User::getPhone))), ArrayList::new));
        System.out.println("filterList = " + filterList);


    }
}
