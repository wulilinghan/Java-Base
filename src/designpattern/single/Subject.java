package designpattern.single;

/**
 * Created By gao_e on 2020/5/5 16:05
 * 目标类
 */
public class Subject {

    private Subject(){}
    // 单例模式 5种创建方式

    // 恶汉
    private static Subject subject = new Subject();
    public static Subject getSingleInstance1(){
        return subject;
    }

    // 懒汉
    private static Subject subject2 = null;
    public static Subject getSingleInstance2(){
        if(subject2 != null){
            return subject2;
        }
        synchronized ("") {
            if(subject2 != null){
                return subject2;
            }
            return (subject2 = new Subject());
        }
    }

    // 静态内部类
    private static class InnerClass{
        public static Subject subject = new Subject();
    }
    public static Subject getSingleInstance3(){
        return InnerClass.subject;
    }
    // CAS

    // 枚举


    public void f() {

    }

    // 为什么要用单例模式？
    // 内存空间
    // 整个程序中，只要用一个实例，就能够解决所有问题！避免程序内存空间的消耗
    // 我能用一个对象处理问题的，我就坚决不会再创建一个对象
    // 什么情况下可以使用单例模式呢？
    // 我们只想 通过 对象 调用其方法，
    // 不需要针对 不同的对象设置不同的成员属性的时候(就是不需要区分对象的差异时)，我们就可以
    // 针对当前这个类，创建单例，在整个程序中使用当前类的同一个对象
    // spring中 controller、service、以及mybatis中的mapper都是默认使用的单例

    // 面向对象编程的语言，我们进行方法调用的时候，会用一个对象来调用方法

    // 使用单例调用方法 跟 使用类名 直接调用 static (工具类)方法的区别？
    // 方法重写、动态代理、及配合其它设计模式一起使用
    // 这个答案，存在于 面向对象编程的 特征里面
    // 面向对象编程：封装、集成、多态
    // 父子类的关系(继承)，子类对象可以调用父类方法、父类引用指向子类对象
    // 更贴合实际生活，容易理解
    // IOC 控制反转，利用面向对象编程的特性实现解耦


}
