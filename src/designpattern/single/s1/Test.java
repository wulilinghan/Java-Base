package designpattern.single.s1;

/**
 * @Author G_Y
 * @Date 2020/9/6 18:45
 * @Description: // TODO
 **/
public class Test {
    public static void main(String[] args) {
        EUser.SINZGLE_USER.f();
        try {
            Class<?> aClass = Class.forName("designpattern.single.s1.User");
            System.out.println(aClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        User instance = User.getInstance();
    }
}