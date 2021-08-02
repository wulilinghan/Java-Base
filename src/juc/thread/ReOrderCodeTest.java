package juc.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created By gao_e on 2020/5/26 9:53
 */
public class ReOrderCodeTest {



    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        lock.lock();
//        lock.tryLock();
        lock.lock();

        InitTask initTask = new InitTask();
        new Thread(initTask).start();
        initTask.ac = new Ac();
        initTask.notInit = false;

        lock.unlock();
        lock.unlock();
    }
}
class InitTask implements Runnable {
    public boolean notInit = true;
    public Ac ac = null;

    @Override
    public void run() {
        while (notInit){
            // 还未初始化
        }
        ac.f();
    }
}
class Ac {
    private String str = "abc";
    public void  f() {
        String[] bs = str.split("b");
    }
}
