package designpattern.factory;

/**
 * @author: ThinkPad.
 * @Description: TODO()
 * @since:Created in 2019/8/16.
 * @Modified By:
 */
public enum EUserServiceImpl {

    IOS("IOS", UserServiceIOS.class),
    ANDROID("ANDROID", UserServiceAndroid.class);
    private String name;
    private Class<? extends UserService> cls;

    public String getName() {
        return name;
    }

    public Class<? extends UserService> getCls() {
        return cls;
    }

    private EUserServiceImpl(String name, Class cls) {
        this.name = name;
        this.cls = cls;
    }

}
