package top.b0x0.basic;

/**
 * Created By gao_e on 2020/3/29 15:52
 */
public class ParentAndSonCodeExecutor2 {
    public static void main(String[] args) {
        SonC2 sonC2 = new SonC2();
        sonC2.f1();
    }
}
class ParentC2 {
    public void f1() {
        System.out.println("ParentC f1");
        System.out.println(this.getClass().getName());
        this.f2();
    }
    public void f2() {
        System.out.println("ParentC f2");
    }
}
class SonC2 extends ParentC2 {
    private ParentC2 target = new ParentC2();
    @Override
    public void f1() {
        System.out.println("走回调拦截逻辑f1....before");
        target.f1();
        System.out.println("走回调拦截逻辑f1....after");
    }
    @Override
    public void f2() {
        System.out.println("走回调拦截逻辑f2....before");
        target.f2();
        System.out.println("走回调拦截逻辑f2.....after");
    }
    // 模拟的子类中的代理方法，最终根本没有机会被执行
    public void super_f1() {
        super.f1();
    }
    public void super_f2() {
        super.f2();
    }
}
