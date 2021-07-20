package effective.object;

public class ServiceTest {
    
    static {
        // 注册默认service实例应用 略...
        Services.registerDefaultProvider(new ProviderImpl1());
        // 注册具体service实例应用 略...
    }

    public static void main(String[] args) {
        Service service1 = Services.newInstance();
        //Service service2 = Services.newInstance("service2");
        service1.sayHello();
    }

}
