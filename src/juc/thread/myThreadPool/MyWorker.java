package juc.thread.myThreadPool;

import java.util.concurrent.BlockingQueue;

/**
 * 执行任务
 *
 * @author ManJiis
 * @since 2021-07-31
 */
public class MyWorker extends Thread {
    private String name;
    private BlockingQueue<Runnable> tasks;
    public boolean isExit = false;

    public MyWorker(String name, BlockingQueue<Runnable> tasks) {
        //调用父类构造方法传入name
        super(name);
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (!isExit) {
            while (tasks.size() != 0) {
                Runnable remove = tasks.remove();
                remove.run();
            }
        }
    }
}
