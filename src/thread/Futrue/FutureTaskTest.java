package thread.Futrue;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ManJiis
 */
public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // new Thread(new Runnable()).start();
        // new Thread(new FutureTask<V>()).start();
        // new Thread(new FutureTask<V>( Callable )).start();

        // 怎么启动Callable ？
        // 一般用utureTask把它包装起来

        MyThreadCall myThreadCall = new MyThreadCall();

        // 适配类
        FutureTask<String> futureTask1 = new FutureTask<>(myThreadCall);

        FutureTask<String> futureTask2 = new FutureTask<>(myThreadCall);

        new Thread(futureTask1, "A").start();
        new Thread(futureTask1, "B").start();

        new Thread(futureTask2, "C").start();
        // futureTask被两个线程执行只打印一个call() 说明结果会被缓存，效率高

        // 这个get 方法可能会产生阻塞！把他放到最后
        String r1 = futureTask1.get();

        // 总结
        // 1、有缓存
        // 2、结果可能需要等待，会阻塞！

        // 或者使用异步通信来处理！
        System.out.println(r1);
    }
}

class MyThreadCall implements Callable<String> {
    @Override
    public String call() {
        String threadName = Thread.currentThread().getName();
        // 会打印几个call()?
        System.out.println(threadName + " call()");
        int random = new Random().nextInt(100);
        return threadName + " " + random;
    }
}