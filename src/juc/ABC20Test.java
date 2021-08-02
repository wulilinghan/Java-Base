package juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created By gao_e on 2020/5/16 19:26
 */
public class ABC20Test {
    public static void main(String[] args) {
        //第一种方式
//        waintAndNotify();

        // 第二种方式
//        lockAndCondition();

        // 第三种方式
//        Semaphore  // 信号量 灯
//        Semaphoretest();

    }

    // 第一种方式 wait notify
    static volatile String obj = "A";
    static int i = 0;

    public static void waintAndNotify() {
//        AtomicInteger atomicInteger = new AtomicInteger(0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized ("") {
                        if (obj != "A") {
                            try {
                                "".wait();   continue;

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
//                        int i = atomicInteger.addAndGet(1);
                        i++;
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (i > 20) {
                            break;
                        }
                        System.out.println("A");
                        obj = "B";
                        "".notifyAll();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized ("") {
                        if (obj != "B") {
                            try {
                                "".wait();
                                continue;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
//                        int i = atomicInteger.addAndGet(1);
                        i++;
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (i > 20) {
                            break;
                        }
                        System.out.println("B");
                        obj = "C";
                        "".notifyAll();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized ("") {
                        if (obj != "C") {
                            try {
                                "".wait();
                                continue;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
//                        int i = atomicInteger.addAndGet(1);
                        i++;
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (i > 20) {
                            break;
                        }
                        System.out.println("C");
                        obj = "A";
                        "".notifyAll();
                    }
                }
            }
        }).start();
    }


    // 第二种方式 lock condition

    static Lock lock = new ReentrantLock();
    static volatile String currentThreadPrint = "A";
    static Condition conditionA = lock.newCondition();
    static Condition conditionB = lock.newCondition();
    static Condition conditionC = lock.newCondition();

    public static void lockAndCondition() {

        Thread thread1 = new Thread(new MyPrintThreadTask(conditionA, conditionB, "A", "B"));
        Thread thread2 = new Thread(new MyPrintThreadTask(conditionB, conditionC, "B", "C"));
        Thread thread3 = new Thread(new MyPrintThreadTask(conditionC, conditionA, "C", "A"));
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static void Semaphoretest() {
        Semaphore semaphoreA = new Semaphore(1);
        Semaphore semaphoreB = new Semaphore(0);
        Semaphore semaphoreC = new Semaphore(0);
        Thread thread1 = new Thread(new MyPrintThreadTask2("A",semaphoreA,semaphoreB));
        Thread thread2 = new Thread(new MyPrintThreadTask2("B",semaphoreB,semaphoreC));
        Thread thread3 = new Thread(new MyPrintThreadTask2("C",semaphoreC,semaphoreA));
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class MyPrintThreadTask implements Runnable {
    private Condition currentCondition;
    private Condition nextCondition;
    private String printStr;
    private String nextStr;
    static AtomicInteger atomicInteger = new AtomicInteger(0);

    public MyPrintThreadTask(Condition currentCondition, Condition nextCondition, String printStr,
                             String nextStr) {
        this.currentCondition = currentCondition;
        this.nextCondition = nextCondition;
        this.printStr = printStr;
        this.nextStr = nextStr;
    }

    @Override
    public void run() {
        while (true) {
            int i = atomicInteger.addAndGet(1);
            if (i > 20) {
                break;
            }
            ABC20Test.lock.lock();
            try {
                if (!ABC20Test.currentThreadPrint.equals(printStr)) {
                    // 疑问？没疑问，lock之内
                    Thread.sleep(1);
                    currentCondition.await();
                }
                System.out.println(printStr);
                ABC20Test.currentThreadPrint = nextStr;
                nextCondition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ABC20Test.lock.unlock();
            }
        }
    }
}

class MyPrintThreadTask2 implements Runnable {
    private String printStr;
//    private String nextStr;
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    private Semaphore currentSemaphore;
    private Semaphore nextSemaPhore;

    public MyPrintThreadTask2(String printStr, Semaphore currentSemaphore, Semaphore nextSemaPhore) {
        this.printStr = printStr;
//        this.nextStr = nextStr;
        this.currentSemaphore = currentSemaphore;
        this.nextSemaPhore = nextSemaPhore;
    }

    @Override
    public void run() {
        while (true) {
            int i = atomicInteger.addAndGet(1);
            if (i > 20) {
                break;
            }
            try {
                currentSemaphore.acquire(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(printStr);
            nextSemaPhore.release(1);
        }
    }
}