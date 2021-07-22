package thread.ThreadPool;

import java.util.concurrent.*;

/**
 * 自定义拒绝策略
 *
 * @author ManJiis
 * @since 2021-07-22
 * @since jdk1.8
 */
public class TestMyReject {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(
                        1,                       // 核心线程
                        2,                  // 总线程数
                        1,                     // 保活时间
                        TimeUnit.SECONDS,                    // 保活时间单位
                        new ArrayBlockingQueue<>(2),// 任务队列
                        new MyRejectedExecutionHandler());   // 拒绝策略
        for (int i = 1; i <= 100; i++) {
            final int task = i;
            threadPoolExecutor.execute(new MyTask("第" + task + "个任务"));
        }
    }
}

class MyTask implements Runnable {
    String data;

    public MyTask(String data) {
        this.data = data;
    }

    @Override
    public void run() {
        System.out.println("正在处理：" + data);
        try {
            // 假设每个任务需要处理20毫秒
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return data;
    }
}

/**
 * 自定义拒绝策略
 */
class MyRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //打印丢弃的任务
        System.out.println(r.toString() + " is discard");
    }
}
