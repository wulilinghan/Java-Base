package proxy.cglibproxy.simple;

/**
 * Created By gao_e on 2020/3/20 10:18
 */
public class SimpleRealSubject {
    public String name = "SimpleRealSubject";
    public String f1(String str) {
        System.out.println("SimpleRealSubject public f1 " + str);
        return "public f1";
    }
    public SimpleRealSubject() {
        System.out.println("This is SimpleRealSubject construct");
    }
//    public SimpleRealSubject(String name) {
//        System.out.println("This is SimpleRealSubject construct");
//    }
}
