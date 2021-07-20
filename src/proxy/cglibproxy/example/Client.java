package proxy.cglibproxy.example;

/**
 * Created By gao_e on 2020/3/18 10:45
 */
public class Client {
    public static void main(String[] args) {
        RealSubject proxyInstance = ProxyInstanceFactory.getProxyInstance(RealSubject.class);
        proxyInstance.f1("长沙黑马程序员");
        System.out.println("---------------");
        proxyInstance.f3("长沙黑马程序员");
        System.out.println("---------------");
        proxyInstance.f4("长沙黑马程序员");
        System.out.println("---------------");
        proxyInstance.f5("长沙黑马程序员");
    }
}
