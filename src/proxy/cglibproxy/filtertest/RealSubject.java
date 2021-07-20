package proxy.cglibproxy.filtertest;

/**
 * Created By gao_e on 2020/3/20 10:18
 */
public class RealSubject {
    private static RealSubject realSubject = new RealSubject();
    public static RealSubject getInstance(){
        return realSubject;
    }
    public String name = "SimpleRealSubject";
    @Transactional
    public String f1(String str) {
        System.out.println("SimpleRealSubject public f1 " + str);
        return "public f1";
    }
    @CatchAble
    public String f2(String str) {
        System.out.println("SimpleRealSubject public f2 " + str);
        return "public f2";
    }
    // 加上final则不会走拦截逻辑，那么就不会立马使用委托对象，在调用f1方法时，依然用的代理类对象
    /*final*/ public String f3(String str) {
        System.out.println("SimpleRealSubject public f3 " + str);
        this.f1(str);
        this.f2(str);
        return "public f3";
    }
}
