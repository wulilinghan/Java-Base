package top.b0x0.cls.clstest;

import top.b0x0.cls.annotation.Autowired;
import top.b0x0.cls.annotation.Controller;

@Controller(name = "userService", values = {"abc", "edf"})
public class UserService extends AbstractUserService {

    @Autowired
    private UserMapper userMapper;

    private String f1(String s) {
        System.out.println("private f1");
        return s;
    }

    public static Integer f2(Integer i1, Integer i2) {
        System.out.println("static f2");
        return i1 + i2;
    }

    public String f3() {
        System.out.println("public f3 no args");
        return "f3";
    }

    public String f3(String name, Integer age) {
        System.out.println("public f3 has args");
        return "f3 " + name + " " + age;
    }

    private String name;
    private Integer age;

    public UserService(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public UserService(String name) {
        this.name = name;
    }

    public UserService() {
    }
}
