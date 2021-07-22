package thread.Futrue;


import java.util.concurrent.Callable;
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
            //主线程代码执行到这里会阻塞，直到Callable任务有返回
            Integer sum = futureTask.get();
            // 5050
            System.out.println(sum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class MyCallableTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("额外线程任务开始处理....");
        int sum = 0;
        // 计算1...100的和
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
//        TimeUnit.SECONDS.sleep(5);
        Thread.sleep(5000);
        return sum;
    }
}
