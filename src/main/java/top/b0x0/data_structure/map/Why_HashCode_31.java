package top.b0x0.data_structure.map;

import java.util.HashMap;

/**
 * HashCode计算为什么使用 31 作为乘数？
 *
 * @author ManJiis
 * @since 2021-09-16
 * @since JDK1.8
 */
public class Why_HashCode_31 {
    public static void main(String[] args) {

        User user = new User("haha", 17);
        System.out.println("user.hashCode() = " + user.hashCode());

        HashMap<User, Integer> map = new HashMap<>();
        map.put(user, 66);
        User user1 = new User("haha", 17);
        Integer haha = map.get(user1);
        System.out.println("user1.hashCode() = " + user1.hashCode());

        /*
         * 不重写equals和hashcode方法时：
         * 计算两个对象属性值一致的对象时 获取的hashcode值不一致
         */

        //  不重写equals和hashcode方法时  获取值为null
        // 默认情况下，hashCode方法是将对象的存储地址进行映射
        System.out.println("haha = " + haha);

    }


}

class User {
    private String name;
    private Integer age;
    private String addr;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        User user = (User) o;
//
//        if (name != null ? !name.equals(user.name) : user.name != null) return false;
//        if (age != null ? !age.equals(user.age) : user.age != null) return false;
//        return addr != null ? addr.equals(user.addr) : user.addr == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = name != null ? name.hashCode() : 0;
//        result = 31 * result + (age != null ? age.hashCode() : 0);
//        result = 31 * result + (addr != null ? addr.hashCode() : 0);
//        return result;
//    }
}
