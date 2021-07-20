package proxy.jdkproxy;

/**
 * Created By gao_e on 2020/3/16 16:44
 * 使用动态代理的客户端
 */
public class Client {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        // 1-1、有委托者
        ISubject realSubject = new RealSubject();

        // 使用动态生成代理对象来做具体的业务处理
        ISubject proxyInstance = ProxyInstanceFactory.getProxyInstanceBy(realSubject, ISubject.class);
        proxyInstance.sayName("黑马程序员");




//        Class<?>[] interfaces = realSubject.getClass().getInterfaces();
//        for (Class<?> anInterface : interfaces) {
//            System.out.println(anInterface.getName());
//        }

//        proxyInstance.doSomething();
        System.out.println("==================================");

        // 1-2、有委托者
//        ISubject subRealSubject = new SubRealSubject();
//        Class<?>[] interfaces1 = subRealSubject.getClass().getSuperclass().getInterfaces();
//        for (Class<?> anInterface : interfaces1) {
//            System.out.println(anInterface.getName());
//        }

        // 使用动态生成代理对象来做具体的业务处理
//        ISubject proxyInstance2 = (ISubject) ProxyInstanceFactory.getProxyInstanceBy(subRealSubject);
//        proxyInstance2.doSomething();
//        System.out.println("==================================");

        // 2、没有委托者，直接根据接口实现获取代理对象
//        ISubject proxySelfLogicInstance = ProxyInstanceFactory.getProxyInstanceBy(ISubject.class);
//        proxySelfLogicInstance.doSomething();
    }
}
