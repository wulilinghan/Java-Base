package proxy.cglibproxy.example;

import proxy.cglibproxy.example.interceptors.Transactional;

/**
 * Created By gao_e on 2020/3/18 10:18
 */
public class RealSubject {
    public RealSubject() {
        System.out.println("This is RealSubject constractor");
    }
    public String s = new String("aaaaa");
    @Transactional
    public String f1(String str) {
        System.out.println("public f1" + str);
        return "public f1";
    }
    private String f2(String str) {
        System.out.println("private f2" + str);
        System.out.println("-----------");
        f1(str);
        return "private f2";
    }
    final public String f3(String str) {
        System.out.println("final public f3" + str);
        System.out.println("-----------");
        return "final public f3";
    }
    public String f4(String str) {
        System.out.println("public f4" + str);
        System.out.println("-----------");
        this.f1(str);
        System.out.println("-----------");
        this.f2(str);
        System.out.println("-----------");
        this.f3(str);
        return "public f4";
    }
    final public String f5(String str) {
        System.out.println("final public f5" + str);
        System.out.println("-----------");
        this.f1(str);
        System.out.println("-----------");
        this.f2(str);
        System.out.println("-----------");
        this.f3(str);
        System.out.println("-----------");
        this.f4(str);
        return "final public f5";
    }
    private Integer age;
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}
