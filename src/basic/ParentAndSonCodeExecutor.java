package basic;

/**
 * Created By gao_e on 2020/3/29 15:52
 */
public class ParentAndSonCodeExecutor {
    public static void main(String[] args) {
        SonC sonC = new SonC();
        sonC.super_f1();
    }
}
class ParentC {
    public void f1() {
        System.out.println("ParentC f1");
        System.out.println(this.getClass().getName());
        this.f2();
    }
    public void f2() {
        System.out.println("ParentC f2");
    }
}
class SonC extends ParentC {
    @Override
    public void f1() {
        System.out.println("走回调拦截逻辑f1");
    }
    @Override
    public void f2() {
        System.out.println("走回调拦截逻辑f2");
    }
    public void super_f1() {
        super.f1();
    }
    public void super_f2() {
        super.f2();
    }
}
