package proxy.jdkproxy;

/**
 * Created By gao_e on 2020/3/16 16:47
 * 委托类
 */
public class RealSubject implements ISubject{
    @Override
    public void doSomething() {
        System.out.println("This is real doSomething");
    }
    @Override
    public String sayName(String name) {
        System.out.println(name);
        return "Hello " + name;
    }
}
