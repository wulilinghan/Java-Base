package top.b0x0.cls.loadclasstest;

public class TestClient {

    public static void main(String[] args) throws ClassNotFoundException {
//        Class<?> cls1 = Class.forName("top.b0x0.cls.loadclasstest.UserService");
        Class.forName("top.b0x0.cls.loadclasstest.UserService",false,TestClient.class.getClassLoader());
        System.out.println(OrderService.names);
    }

//    public static void main(String[] args) throws ClassNotFoundException {
//        ClassLoader classLoader = TestClient.class.getClassLoader();
//        classLoader.loadClass("top.b0x0.cls.loadclasstest.UserService");
//        System.out.println(OrderService.names);
//    }

//    public static void main(String[] args) {
//        UserService userService = new UserService();
//        System.out.println(OrderService.names);

//    }


}
