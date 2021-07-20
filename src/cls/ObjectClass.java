package cls;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ObjectClass {

    public static final Map<String, String> map = new HashMap<>();
    static {
        map.put("GaoYue", "TLX");
        System.out.println(map.toString());
//        sayLove();
    }

    public static void sayLove() {
        Iterator<Entry<String, String>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            System.out.println(entry.getKey() + " LOVE " + entry.getValue());
        }
    }
}
