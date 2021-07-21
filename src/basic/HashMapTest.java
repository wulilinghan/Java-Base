package basic;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @program: java-base->HashMapTest
 * @description: hashMap
 * @author: G_Y
 * @since: 2019-09-27 20:26
 **/
public class HashMapTest {
    public static void main(String[] args) {
//        HashMap<Key, String> map = new HashMap<>();
//        Key key1 = new Key(1, "1");
//        Key key2 = new Key(1, "2");
//        map.put(key1, "a");
//        map.put(key2, "b");
//        System.out.println(map.size());


//        String a = "Aa";
//        String b = "Aa";
//        Object b1;
////        System.out.println(a.hashCode()==b.hashCode());
//        System.out.println(a == b);
        HashMap<String, String> map = new HashMap<>();
        map.put(null,"null");
        map.put(null,"null2");
//        map.put(a,"1");
//        map.put(b,"2");
        System.out.println(map.get(null));


//        System.out.println(1<<30);
    }

//    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        Map<Integer, String> map = new HashMap<>(1500);
//        map.put(1, "one");
//        System.out.println(map.size());
//        Class<? extends Map> aClass = map.getClass();
//        Field table = aClass.getDeclaredField("table");
//        Field threshold = aClass.getDeclaredField("threshold");
//        table.setAccessible(true);
//        threshold.setAccessible(true);
//        Object o = table.get(map);
//        Object o1 = threshold.get(map);
//        for (int i = 2; i <= 1000; i++) {
//            map.put(i,i+"");
//        }
//        Object o2 = table.get(map);
//        Object o12 = threshold.get(map);
//        System.out.println(o);
//        System.out.println(o1);
//    }

}

class Key {
    Integer id;
    String name;
    public Key(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        // equals()跟hashCode()保持一致
        return Objects.equals(id, key.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}