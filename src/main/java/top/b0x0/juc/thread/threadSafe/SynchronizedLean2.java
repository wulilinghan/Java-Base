package top.b0x0.juc.thread.threadSafe;

/**
 * synchronized learn
 *
 * @author TANG
 * @since 2021-07-27
 * @since jdk1.8
 */
public class SynchronizedLean2 {
    public static void main(String[] args) {
        Father lean2 = new FatherSon();
        lean2.sayHello();
    }
}
