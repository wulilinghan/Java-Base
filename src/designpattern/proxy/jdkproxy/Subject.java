package designpattern.proxy.jdkproxy;

/**
 * @Author G_Y
 * @Date 2020/9/11 19:30
 * @Description: // 委托类 (原目标逻辑类)
 **/
public class Subject implements ISubject{
    @Override
    public String sayHello(String name) {
        System.out.println("sayHello : hello " + name);
        return "hello " + name;
    }
}
