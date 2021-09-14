package top.b0x0.juc.thread.myThreadPool;

/**
 * 实现runnable接口的任务
 *
 * @author ManJiis
 * @since 2021-07-31
 */
public class MyTask implements Runnable {
    /**
     * 任务编号
     */
    private int id;

    public MyTask(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MyTask{" +
                "id=" + id +
                '}';
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("线程:" + name + "即将执行任务:" + id);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程:" + name + "完成了任务:" + id);
    }
}
