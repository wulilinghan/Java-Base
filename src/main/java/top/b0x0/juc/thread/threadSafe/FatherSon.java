package top.b0x0.juc.thread.threadSafe;

/**
 * @author TANG
 * @since 2021-07-27
 * @since jdk1.8
 */
public class FatherSon extends Father {

    @Override
    public synchronized void sayHello() {
        System.out.println(" son say hello ");
        super.sayHello();
        System.out.println(" son say end...");
    }
}
