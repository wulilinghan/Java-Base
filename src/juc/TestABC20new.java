package juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created By gao_e on 2020/5/17 19:41
 */
public class TestABC20new {

    static int maxCount = 20;
//    static volatile int printedCount = 0;
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    static volatile String shouldPrintStr = "A";

    public static void main(String[] args) {

        Thread t1 = new Thread(new MyRunable2("A","B"));
        Thread t2 = new Thread(new MyRunable2("B","C"));
        Thread t3 = new Thread(new MyRunable2("C","A"));
        t1.start();
        t2.start();
        t3.start();

    }


}

class MyRunable2 implements Runnable {
    private String printStr;
    private String nextStr;

    public MyRunable2(String printStr, String nextStr) {
        this.printStr = printStr;
        this.nextStr = nextStr;
    }

    @Override
    public void run() {
        while (true) {
            synchronized ("") {
                if(TestABC20new.shouldPrintStr != printStr) {
                    try {
                        "".wait();
                        continue;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int i = TestABC20new.atomicInteger.addAndGet(1);
                if (i > TestABC20new.maxCount) {
                    System.out.println(TestABC20new.atomicInteger.get());
                    break;
                }
                System.out.println(printStr);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                TestABC20new.shouldPrintStr = nextStr;
                "".notifyAll();
            }
        }
    }
}
