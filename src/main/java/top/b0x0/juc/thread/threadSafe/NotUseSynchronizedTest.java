package top.b0x0.juc.thread.threadSafe;

/**
 * @program: java-base->NotUseSynchronizedTest
 * @description:
 * @author: ManJiis
 * @since: 2019-08-27 21:35
 **/
public class NotUseSynchronizedTest {


    public static void main(String[] args) throws InterruptedException {

        NotUseSynchronizedTest notUseSynchronizedTest = new NotUseSynchronizedTest();

        for (int i = 1; i <= 500; i++) {
            new Thread(new MyRunnable2(notUseSynchronizedTest)).start();
        }

        Thread.sleep(5000);
        System.out.println(notUseSynchronizedTest.m);
    }

    int m = 0;

    public int getM() {
        return m;
    }

    public boolean updateMByOldM(int newM, int oldM) {
        if (m == oldM) {
            // 非原子性操作
            m = newM;
            return true;
        }
        return false;
    }

}

class MyRunnable2 implements Runnable {
    NotUseSynchronizedTest test;

    public MyRunnable2(NotUseSynchronizedTest test) {
        this.test = test;
    }

    @Override
    public void run() {
        int m = test.getM();
        int newM = m + 1;
        while (!test.updateMByOldM(newM, m)) {
            continue;
        }
        System.out.println("update success");
    }
}