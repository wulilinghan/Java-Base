package proxy.cglibproxy.filtertest;

import org.springframework.cglib.core.DebuggingClassWriter;

/**
 * Created By gao_e on 2020/3/20 10:17
 */
public class Client {
    public static void main(String[] args) {
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\class4");
        RealSubject simpleRealSubject =
                ProxyInstanceFactory.getProxyInstance4(RealSubject.class);

        simpleRealSubject.f1("黑马程序员");
        System.out.println("------------------");

        simpleRealSubject.f2("传智播客");
        System.out.println("------------------");

        simpleRealSubject.f3("长沙黑马程序员");

        System.out.println(simpleRealSubject.name);
    }
}
