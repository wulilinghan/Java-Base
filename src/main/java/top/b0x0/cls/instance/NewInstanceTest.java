package top.b0x0.cls.instance;

public class NewInstanceTest {

    public static void main(String[] args) {
        try {
            ObjectClass objectClass = ObjectClass.class.newInstance();
            // List<String> list = objectClass.getList();
            // System.out.println(list);
            objectClass.printList();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
