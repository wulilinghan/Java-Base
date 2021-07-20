package cn.jerrychen.ThreadPool;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/*
实现提交任务，执行任务
 */
public class MyThreadPool extends Thread {
    private int corePoolSize;   //核心线程
    private int maximumPoolSize;    //线程池最大大小
    private List<MyWorker> threads;  //线程集合
    private int workSize;//任务队列长度
    private BlockingQueue<Runnable> workQueue;//任务队列，因为需要FIFO，所以选用了队列数据结构

    private int keepAliveTime;//非核心线程存活时间
    private RejectedExecutionHandler handler;//线程饱和策略

    public MyThreadPool(int corePoolSize, int maximumPoolSize, int workSize) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workSize = workSize;
        //在构造方法中初始化
        workQueue = new LinkedBlockingDeque<>();
        threads = new ArrayList<>();
    }

    //提交任务
    public void submit(Runnable task) {
        if (workQueue.size() > workSize) {
            System.out.println(task+"被执行线程饱和策略");
            System.out.println("丢弃任务");
        } else {
            workQueue.add(task);
            execute(task);
        }


    }

    //执行任务
    public void execute(Runnable task) {
        if (threads.size() < corePoolSize) {
            //小于核心线程数制造线程，并传入任务队列
            MyWorker work = new MyWorker("核心线程" + threads.size(), workQueue);
            work.start();
            threads.add(work);
        } else if (threads.size() < maximumPoolSize) {
            //小于最大线程数制造非核心线程
            MyWorker work = new MyWorker("非核心线程" + threads.size(), workQueue);
            work.start();
            threads.add(work);
        } else {
            System.out.println(task+"被存入队列");
        }
    }

    //关闭线程池，清空线程
    public void shutdown() {
        for (MyWorker thread : threads) {
            thread.isExit = true;
        }
    }

}
