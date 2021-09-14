package top.b0x0.cls;

public class LoadClassTest {

    // public static void main(String[] args) throws ClassNotFoundException {
    // Class.forName("top.b0x0.cls.ObjectClass", true,
    // Thread.currentThread().getContextClassLoader());
    // }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName("top.b0x0.cls.ObjectClass", false,
                Thread.currentThread().getContextClassLoader());
//        Map map = ObjectClass.map;
//        System.out.println(map.size());

//        ObjectClass.sayLove();




    }

}
