package proxystatic.example;

/**
 * Created By gao_e on 2020/3/16 14:56
 */
public class SubjectStaticFactory {
    /**
     * 对客户类来说，其并不知道返回的是代理类对象还是委托类对象
     */
    public static Subject getInstance() {
        return new ProxySubject(new RealSubject());
    }
}
