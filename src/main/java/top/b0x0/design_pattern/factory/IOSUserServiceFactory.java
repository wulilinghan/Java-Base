package top.b0x0.design_pattern.factory;

/**
 * Created By gao_e on 2020/5/6 15:29
 */
public class IOSUserServiceFactory implements UserServiceFactory {
    @Override
    public UserService getUserServiceInstance() {
        // 可能是一个非常复杂的 对象的创建流程
        return new UserServiceIOS();
    }
}
