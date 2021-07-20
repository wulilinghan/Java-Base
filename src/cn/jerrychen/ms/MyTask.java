package cn.jerrychen.ms;

public class MyTask implements Runnable {
    private static int num = 10; //总数

    private String userName;

    public MyTask(String userName) {
        this.userName = userName;
    }

    @Override
    public void run() {
        System.out.println(userName + "正在参与秒杀任务");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (MyTask.class) {
            if (num > 0) {
                System.out.println(userName + "抢到了手机，还剩余" + --num + "部手机");
            } else {
                System.out.println(userName + "秒杀失败");
            }
        }
    }
}
