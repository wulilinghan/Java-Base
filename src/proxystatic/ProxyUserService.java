package proxystatic;

public class ProxyUserService implements IUserService {

    IUserService userService;

    public ProxyUserService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void say() {
        System.out.println("before");
        userService.say();
        System.out.println("after");
    }
}
