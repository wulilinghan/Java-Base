package proxy.jdkproxy.protectedtest;

/**
 * Created By gao_e on 2020/3/17 20:44
 */
public class UserService {
    private String name;
    private UserService(){}
    protected UserService(String name) {
        this.name = name;
    }
}
class SubUserService extends UserService {
    protected SubUserService(String name) {
        super(name);
    }
}