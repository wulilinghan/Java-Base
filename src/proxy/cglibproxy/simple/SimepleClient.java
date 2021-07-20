package proxy.cglibproxy.simple;

import org.springframework.cglib.core.DebuggingClassWriter;

/**
 * Created By gao_e on 2020/3/20 10:17
 */
public class SimepleClient {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\class");
        SimpleRealSubject simpleRealSubject =
                SimpleProxyInstanceFactory.getProxyInstance(SimpleRealSubject.class);
        simpleRealSubject.f1("长沙黑马程序员");
        System.out.println(simpleRealSubject.name);
    }
}
