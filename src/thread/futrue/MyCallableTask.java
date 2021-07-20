package thread.futrue;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MyCallableTask implements Callable<Integer> {
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
