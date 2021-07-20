package thread.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] args) {
        ExecutorService e1 = Executors.newFixedThreadPool(3);
        e1.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("处理任务");
            }
        });
    }
}
