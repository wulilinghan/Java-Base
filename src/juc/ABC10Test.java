package juc;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABC10Test {
    static volatile char currentThreadName = 'A';
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ExecutorService executorServiceA = Executors.newSingleThreadExecutor();
        ExecutorService executorServiceB = Executors.newSingleThreadExecutor();
        ExecutorService executorServiceC = Executors.newSingleThreadExecutor();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
//            System.out.println("所有的子线程都运行到了屏障位置");
//        });
        for (int i = 1; i <= 10; i++) {
            executorServiceA.execute(new ATask(conditionA, conditionB));
            executorServiceB.execute(new BTask(conditionB, conditionC));
            executorServiceC.execute(new CTask(conditionC, conditionA));
        }
        executorServiceA.shutdown();
        executorServiceB.shutdown();
        executorServiceC.shutdown();
    }
}

class ATask implements Runnable {
    Condition conditionA;
    Condition conditionB;

    public ATask(Condition conditionA, Condition conditionB) {
        this.conditionA = conditionA;
        this.conditionB = conditionB;
    }

    @Override
    public void run() {
        ABC10Test.lock.lock();
        try {
            if (ABC10Test.currentThreadName != 'A') {
                try {
                    conditionA.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("A");
            ABC10Test.currentThreadName = 'B';
            conditionB.signal();
        } finally {
            ABC10Test.lock.unlock();
        }
    }
}

class BTask implements Runnable {
    Condition conditionC;
    CyclicBarrier cyclicBarrier;
    Condition conditionB;

    public BTask(Condition conditionB, Condition conditionC) {
        this.conditionB = conditionB;
        this.conditionC = conditionC;
    }

    @Override
    public void run() {
        ABC10Test.lock.lock();
        try {
            if (ABC10Test.currentThreadName != 'B') {
                try {
                    conditionB.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("B");
            ABC10Test.currentThreadName = 'C';
            conditionC.signal();
        } finally {
            ABC10Test.lock.unlock();
        }
    }
}

class CTask implements Runnable {
    Condition conditionC;
    Condition conditionA;

    public CTask(Condition conditionC, Condition conditionA) {
        this.conditionC = conditionC;
        this.conditionA = conditionA;
    }

    @Override
    public void run() {
        ABC10Test.lock.lock();
        try {
            if (ABC10Test.currentThreadName != 'C') {
                try {
                    conditionC.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("C");
            ABC10Test.currentThreadName = 'A';
            conditionA.signal();
        } finally {
            ABC10Test.lock.unlock();
        }
    }
}
