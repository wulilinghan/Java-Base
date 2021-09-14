package top.b0x0.cls.instance.face;

public class TestExtends {
    public static void main(String[] args) {
        Parent parent = new Son();
        parent.f();// 输出什么？
        System.out.println(parent.getClass().getSimpleName());// 输出什么？
    }
    public static class Son extends Parent {
        @Override
        public void f() {System.out.println("Son");}
    }
    public static class Parent{
        public void f() {System.out.println("Parent");}
    }
}
