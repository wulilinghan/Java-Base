package top.b0x0.design_pattern.proxy.jdkproxy;

/**
 * 委托类 (原目标逻辑类)
 *
 * @author ManJiis
 * @since 2020/9/11 19:30
 **/
public class UserServiceImpl implements IUserService {

    @Override
    public String sayHello(String name) {
        System.out.println("sayHello : hello " + name);
        return "hello " + name;
    }

}
