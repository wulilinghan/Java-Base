package top.b0x0.design_pattern.proxy.staticproxy.simple;

/**
 * 代理类
 *
 * @author ManJiis
 * @since 2020/9/11 18:47
 **/
public class UserServiceProxy1 implements IUserService {

    // 委托对象
    private IUserService userService;

    public UserServiceProxy1(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void regist(String name, String pwd) {
        System.out.println("注册+1,name=" + name);
        // 注册之前打印日志
        userService.regist(name, pwd);
        // 注册成功也打印一下
        System.out.println("注册成功+1,name=" + name);
    }
}
