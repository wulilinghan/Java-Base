package proxystatic;

public class Test {

    public static void main(String[] args) {
        IUserService userService = new UserService();
        IUserService proxyUserService = new ProxyUserService(userService);
        proxyUserService.say();
    }

}
