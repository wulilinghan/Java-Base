package juc.thread.Volatile;

/**
 * 什么是 指令重排?
 * 你写的程序，计算机并不是按照你写的那样去执行的。
 * <p>
 * 源代码-->编译器优化的重排--> 指令并行也可能会重排--> 内存系统也会重排---> 执行
 * 处理器在进行指令重排的时候，考虑：数据之间的依赖性！
 *
 * @author TANG
 * @since 2021-07-22
 * @since jdk1.8
 */
public class VDemo3 {
    static {

    }

    public static void main(String[] args) {
        // 我们所期望的：1234 但是可能执行的时候回变成 2134 1324
        // 可不可能是 4123
        VDemo3 vDemo3 = new VDemo3();
        vDemo3.thread();
        vDemo3.thread2();
        int x = 1; // 1
        int y = 2; // 2
        x = x + 5; // 3
        y = x * x; // 4
        System.out.println("y = " + y);

    }

    public void thread() {
        synchronized (VDemo3.class) {
            System.out.println("true = " + true);
        }
    }

    public synchronized void thread2() {
        System.out.println("true = " + true);
    }
}
