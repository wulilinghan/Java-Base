package juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ManJiis
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // new Thread(new Runnable()).start();
        // new Thread(new FutureTask<V>()).start();
        // new Thread(new FutureTask<V>( Callable )).start();

        new Thread().start(); // 怎么启动Callable

        MyThread thread = new MyThread();

        FutureTask<Integer> futureTask = new FutureTask<>(thread); // 适配类

        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start(); // 只打印一个call() 说明结果会被缓存，效率高

        Integer o = futureTask.get(); //这个get 方法可能会产生阻塞！把他放到最后

        // 总结
        // 1、有缓存
        // 2、结果可能需要等待，会阻塞！

        // 或者使用异步通信来处理！
        System.out.println(o);
    }
}

class MyThread implements Callable<Integer> {
    @Override
    public Integer call() {
        System.out.println("call()"); // 会打印几个call
        // 耗时的操作
        return 1024;
    }
}