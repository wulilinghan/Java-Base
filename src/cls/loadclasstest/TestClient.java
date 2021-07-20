package cls.loadclasstest;

public class TestClient {

    public static void main(String[] args) throws ClassNotFoundException {
//        Class<?> cls1 = Class.forName("cls.loadclasstest.UserService");
        Class.forName("cls.loadclasstest.UserService",false,TestClient.class.getClassLoader());
        System.out.println(OrderService.names);
    }

//    public static void main(String[] args) throws ClassNotFoundException {
//        ClassLoader classLoader = TestClient.class.getClassLoader();
//        classLoader.loadClass("cls.loadclasstest.UserService");
//        System.out.println(OrderService.names);
//    }

//    public static void main(String[] args) {
//        UserService userService = new UserService();
//        System.out.println(OrderService.names);

//    }


}
