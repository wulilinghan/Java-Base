package proxy.cglibproxy.cglibf1f2test;

/**
 * Created By gao_e on 2020/3/20 10:18
 */
public class SimpleRealSubject {
    final public String f1(String str) {
        System.out.println("SimpleRealSubject public f1 " + str);
        this.f2(str);
        return "public f1";
    }
    public String f2(String str) {
        System.out.println("SimpleRealSubject public f2 " + str);
        return "public f2";
    }
}
