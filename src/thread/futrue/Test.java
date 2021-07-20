package thread.futrue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test {
    public static void main(String[] args) {
        MyCallableTask myCallableTask = new MyCallableTask();
        FutureTask<Integer> futureTask = new FutureTask<>(myCallableTask);
        new Thread(futureTask).start();
        System.out.println("主线程已经将任务交给其它线程处理");
        try {
//            System.out.println("开始等待...");
            System.out.println("主线程开始处理自己的事情...");
            Thread.sleep(6000);
            System.out.println("主线程处理自己的事情完毕");
            Integer sum = futureTask.get();//主线程代码执行到这里会阻塞，直到Callable任务有返回
            System.out.println(sum); // 5050
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
