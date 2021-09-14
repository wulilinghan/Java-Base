package top.b0x0.designpattern.factory;

/**
 * @program: java-base->SimpleFactory
 * @description: 简单工厂
 * @author: ManJiis
 * @since: 2019-08-16 16:54
 **/
public class SimpleFactory {

    private SimpleFactory(){}
    public static SimpleFactory simpleFactory = new SimpleFactory();


    // 不同的方法 创建不同的 对象
    // 静态工厂方法体现
    public static UserService getUserServiceAndroid() {
        return new UserServiceAndroid();
    }

    public static UserService getUserServiceIOS() {
        return new UserServiceIOS();
    }

    public UserService getUserService(EUserServiceImpl e) {
        Class<? extends UserService> cls = e.getCls();
        try {
            return cls.newInstance();
        } catch (Exception exc) {
            throw new RuntimeException("创建实例异常");
        }
    }
    public static UserService getUserService2(String serviceName) {
        if("UserServiceImpl1".equals(serviceName)) {
            return new UserServiceImpl1();
        }
        if("UserServiceImpl2".equals(serviceName)) {
            return new UserServiceImpl2();
        }
        throw new IllegalArgumentException("参数不合法");
    }

}
