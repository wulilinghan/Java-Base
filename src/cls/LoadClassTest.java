package cls;

import java.util.Map;

public class LoadClassTest {

    // public static void main(String[] args) throws ClassNotFoundException {
    // Class.forName("cls.ObjectClass", true,
    // Thread.currentThread().getContextClassLoader());
    // }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName("cls.ObjectClass", false,
                Thread.currentThread().getContextClassLoader());
//        Map map = ObjectClass.map;
//        System.out.println(map.size());

//        ObjectClass.sayLove();




    }

}
