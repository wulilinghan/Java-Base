package top.b0x0.cls.clstest;

import top.b0x0.cls.annotation.Autowired;

import java.lang.reflect.*;

public class ClientTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        Class<?> controllerClass = Class.forName("top.b0x0.cls.clstest.UserController");
        Field userServiceF = controllerClass.getDeclaredField("userService");
        boolean annotationPresent = userServiceF.isAnnotationPresent(Autowired.class);
        System.out.println(annotationPresent);
        if(annotationPresent) {
            // 如果当前字段被@Autowired修饰，Spring IOC 初始化过程中，则会考虑针对当前字段做自动注入操作
            // 获取字段的类型
            Class<?> type = userServiceF.getType();
            // 从IOC已经构建的Bean容器中根据类型获取Bean
            // 这里我自己new一个，用于测试
            UserService userService = new UserService();
            // 获取到Bean之后，接下来执行Field的set方法，实现自动注入
            userServiceF.setAccessible(true);
            // 同样需要从IOC容器中根据当前Class——controllerClass，获取到当前Controller对象，我同样new一个用于测试
            UserController userController = new UserController();
            // 动态注入
            userServiceF.set(userController, userService);
        }

//        UserController userController = new UserController();
//        userServiceF.setAccessible(true);
//        // 反射执行Filed的set方法
//        userServiceF.set(userController,new UserService());
//        // // 反射执行Field的get方法
//        UserService userService1 = (UserService) userServiceF.get(userController);
//        System.out.println(userService1);


//        Field[] declaredFields = controllerClass.getDeclaredFields();
//        for (Field declaredField : declaredFields) {
//            System.out.println(declaredField.getName());
//            System.out.println(declaredField.getType());
//        }


//        Method f = controllerClass.getMethod("f");
//        boolean annotationPresent = f.isAnnotationPresent(RequestMapping.class);
//        System.out.println(annotationPresent);
//        if(annotationPresent) {
//            RequestMapping annotation = f.getAnnotation(RequestMapping.class);
//            String[] value = annotation.value();
//            for (String s : value) {
//                System.out.println(s);
//            }
//        }


//        Class<?> c3 = Class.forName("top.b0x0.cls.clstest.UserService");
//        Method f3 = c3.getMethod("f3");
//        UserService userService = new UserService();
//        Object invoke = f3.invoke(userService);
//        System.out.println(invoke);


//        Method f2 = c3.getMethod("f2", Integer.class, Integer.class);
//        Object invoke = f2.invoke(c3, 1, 1);
//        System.out.println(invoke);


//        UserService userService = new UserService();
//        Method f1 = c3.getDeclaredMethod("f1", String.class);
//        f1.setAccessible(true);
//        f1.invoke(userService,"haha");





//        Method f3_1 = c3.getDeclaredMethod("f3");
//        Method f3_2 = c3.getDeclaredMethod("f3", String.class, Integer.class);
//        // 获取所有方法
//        Method[] methods = c3.getDeclaredMethods();
//        for (Method method : methods) {
//            // 方法名称
//            System.out.println(method.getName());
//            // 方法所在类
//            System.out.println(method.getDeclaringClass().getName());
//            // 方法返回类型
//            System.out.println(method.getReturnType().getName());
//            // 方法参数类型集合
//            Class<?>[] parameterTypes = method.getParameterTypes();
//            for (Class<?> parameterType : parameterTypes) {
//                // 打印方法参数类型名称
//                System.out.println(parameterType.getName());
//            }
//            System.out.println("----------------------");
//        }


//        UserService o1 = (UserService) c3.newInstance();
//
//        Constructor<?> constructor2 = c3.getDeclaredConstructor();
//        constructor2.setAccessible(true);
//        UserService o = (UserService) constructor2.newInstance();
//
//        // 根据参数类型获取构造函数
//        Constructor<?> constructor1 = c3.getConstructor(String.class, Integer.class);
//        Parameter[] parameters = constructor1.getParameters();
//        for (Parameter parameter : parameters) {
//            System.out.println(parameter.getName());
//        }
//
//        // 使用构造函数创建对象
//        UserService userService = (UserService) constructor1.newInstance("hello", 18);
//        // 获取所有构造函数
//        Constructor<?>[] constructors = c3.getConstructors();
//        // 遍历构造函数
//        for (Constructor<?> constructor : constructors) {
//            // 获取该构造函数的 参数类型集
//            Class<?>[] parameterTypes = constructor.getParameterTypes();
//            System.out.println("------------");
//            for (Class<?> parameterType : parameterTypes) {
//                System.out.println(parameterType.getName());
//            }
//        }
//
//        Class<?> c1 = Class.forName("top.b0x0.cls.clstest.IUserService");
//        Class<?> c2 = Class.forName("top.b0x0.cls.clstest.AbstractUserService");

        // 获取 UserService类上的Controller注解对象
//        Controller annotation = c3.getAnnotation(Controller.class);
//        // 从注解对象中获取属性
//        String name = annotation.name();
//        String[] values = annotation.values();
//        System.out.println(name);
//        for (String value : values) {
//            System.out.println(value);
//        }


//        boolean annotationPresent = c3.isAnnotationPresent(Controller.class);
//        System.out.println(annotationPresent); // true
//        annotationPresent = c2.isAnnotationPresent(Controller.class);
//        System.out.println(annotationPresent); // false


//        Class<?>[] interfaces1 = c2.getInterfaces();
//        Class<?>[] interfaces2 = c3.getInterfaces();
//
//        System.out.println(c1.isInterface());
//        System.out.println(c2.isInterface());
//        System.out.println(c3.isInterface());
//
//
//        Class<?> superclass = c2.getSuperclass();
//
//        Class<?> superclass1 = c3.getSuperclass();
//
//
//        System.out.println(superclass);
//        System.out.println(superclass1);
//        System.out.println(interfaces1.length);
//        System.out.println(interfaces2.length);

    }
}
