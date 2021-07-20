package proxy.cglibproxy;

/**
 * @program: java-base->User
 * @description: user
 * @author: G_Y
 * @create: 2019-08-21 17:53
 * 被代理类
 **/
public class User {
    public String sayAge(Integer age) {
        System.out.println("User age is :" + age);
        this.haha();
        return "I'm " + age + " years old";
    }
    public void haha() {
        System.out.println("haha");
    }
}
