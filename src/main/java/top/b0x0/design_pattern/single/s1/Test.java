package top.b0x0.design_pattern.single.s1;

/**
 * @author ManJiis
 * @since 2020/9/6 18:45
 * @Description: // TODO
 **/
public class Test {
    public static void main(String[] args) {
        EUser.SINZGLE_USER.f();
        try {
            Class<?> aClass = Class.forName("top.b0x0.design_pattern.single.s1.User");
            System.out.println(aClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        User instance = User.getInstance();
    }
}
