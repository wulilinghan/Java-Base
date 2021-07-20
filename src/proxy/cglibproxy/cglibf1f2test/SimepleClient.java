package proxy.cglibproxy.cglibf1f2test;

/**
 * Created By gao_e on 2020/3/20 10:17
 */
public class SimepleClient {
    public static void main(String[] args) {
        SimpleRealSubject simpleRealSubject =
                SimpleProxyInstanceFactory.getProxyInstance(SimpleRealSubject.class);
        simpleRealSubject.f1("长沙黑马程序员");
    }
}
