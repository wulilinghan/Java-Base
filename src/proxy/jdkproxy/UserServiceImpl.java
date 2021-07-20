package proxy.jdkproxy;

public class UserServiceImpl implements UserService {

	@Override
	public String getUserNameById(Integer userId) {
		System.out.println("this is UserServiceImpl.getUserNameById");
		return userId + "_testUserName";
	}

	@Override
	public String get2() {
		return null;
	}

}
