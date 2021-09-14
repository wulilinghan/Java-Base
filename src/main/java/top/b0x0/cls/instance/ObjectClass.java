package top.b0x0.cls.instance;

import java.util.ArrayList;
import java.util.List;

public class ObjectClass {
    public ObjectClass(String s) {
        names.add("eeee");
    }
    public ObjectClass() {
    }

    private static final List<String> names;
    static {
        names = new ArrayList<>();
        names.add("GaoYue");
        names.add("TLX");
    }
    public void printList() {
        for (String string : names) {
            System.out.println(string);
        }
    }
    public List<String> getList() {
        return names;
    }
}
