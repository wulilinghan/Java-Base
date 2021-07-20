package proxystatic;

public class UserService implements IUserService {
    @Override
    public void say() {
        System.out.println("Hello");
    }
}
