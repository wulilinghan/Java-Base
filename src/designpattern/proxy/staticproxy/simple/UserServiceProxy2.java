package designpattern.proxy.staticproxy.simple;

/**
 * @Author G_Y
 * @Date 2020/9/11 19:03
 * @Description: // 静态代理类  方式2
 **/
public class UserServiceProxy2 extends UserService {
    @Override
    public void regist(String name, String pwd) {
        // 前置增强
        System.out.println("注册+1,name="+name);
        super.regist(name, pwd);
        // 后置增强
        System.out.println("注册成功+1,name="+name);
    }
}