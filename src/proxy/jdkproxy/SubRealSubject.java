package proxy.jdkproxy;

/**
 * Created By gao_e on 2020/3/16 16:47
 * 委托类
 */
public class SubRealSubject extends RealSubject {
    @Override
    public void doSomething() {
        System.out.println("This is son real doSomething");
    }
}
