package cn.jerrychen.ThreadPool;

/*
实现runnable接口的任务
 */
public class MyTask2 implements Runnable {
    private int id; //任务编号

    public MyTask2(int id) {
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
        String name=Thread.currentThread().getName();
        System.out.println("线程:"+name+"即将执行任务:" +id);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程:"+name+"完成了任务:" +id);
    }
}
