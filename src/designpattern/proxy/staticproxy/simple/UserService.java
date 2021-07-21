package designpattern.proxy.staticproxy.simple;

/**
 * @author ManJiis
 * @since 2020/9/11 18:44
 * @Description: // 委托类 (原目标逻辑)
 **/
public class UserService implements IUserService{
    @Override
    public void regist(String name, String pwd) {
        // 注册逻辑
        System.out.println("注册");
    }
}
