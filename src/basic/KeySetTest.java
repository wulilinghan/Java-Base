package basic;

import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class KeySetTest {
//    static volatile ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
    static volatile HashMap<String, String> map = new HashMap<>();
    public static void main(String[] args) {
//        ConcurrentHashMap.KeySetView<String, String> strings = map.keySet();
//        new A().f();
        Set<String> strings = map.keySet();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    map.put("key","value");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while(true) {
            if(CollectionUtils.isEmpty(strings))
                continue;
            for (String s : strings) {
                System.out.println(map.get(s));
            }
        }
    }
}
class A{
    public static void f(){}
}
