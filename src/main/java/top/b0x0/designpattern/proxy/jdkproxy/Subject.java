package top.b0x0.designpattern.proxy.jdkproxy;

/**
 * 委托类 (原目标逻辑类)
 *
 * @author ManJiis
 * @since 2020/9/11 19:30
 **/
public class Subject implements ISubject {

    @Override
    public String sayHello(String name) {
        System.out.println("sayHello : hello " + name);
        return "hello " + name;
    }

}
