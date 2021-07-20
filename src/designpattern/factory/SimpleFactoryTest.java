package designpattern.factory;

/**
 * @program: java-base->SimpleFactoryTest
 * @description: test
 * @author: G_Y
 * @create: 2019-08-16 17:19
 **/
public class SimpleFactoryTest {

    public static void main(String[] args) {
        UserService userService = SimpleFactory.simpleFactory.getUserService(EUserServiceImpl.IOS);
        UserService userService1 = SimpleFactory.simpleFactory.getUserService(EUserServiceImpl.ANDROID);
        userService.say();
        userService1.say();
    }
}
