package juc.thread.threadSafe;

/**
 * @program: java-base->ThreadTest
 * @description: 同步操作——赋值
 * @author: ManJiis
 * @since: 2019-08-27 16:27
 **/
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        for (int j = 1; j < 10; j++) {
            final ThreadTest threadTest = new ThreadTest();
            for (int i = 1; i <= 50; i++) {
                new Thread(new MyRunnable(threadTest)).start();
            }
            Thread.sleep(500);
            System.out.println("------------------");
            System.out.println(threadTest.m);
            System.out.println(threadTest.c);
            if (threadTest.m != threadTest.c) {
                throw new RuntimeException();
            }
            System.out.println("====================");
        }
    }
    int m = 0;
    static int c = 0;
    public synchronized int add() {
        m++;
        return m;
    }
}
class MyRunnable implements Runnable {
    ThreadTest test;
    public MyRunnable(ThreadTest test) {
        this.test = test;
    }
    @Override
    public void run() {
        // test.add()执行完毕，如：结果为49，在打印输出49时(还未打印)，此时该线程让出cpu时间片
        // 此时另外一个线程将49+1，并且执行完输出，打印为50，完成，然后前一个线程再打印出49,
        // 所以为啥存在 49 会在 50 之后打印 (由此解释非顺序打印的原因)
        System.out.println(
                test.c = test.add()/*事实证明这(add方法、赋值)的确是一个原子操作，前提是add是同步方法*/
        );
    }
}
