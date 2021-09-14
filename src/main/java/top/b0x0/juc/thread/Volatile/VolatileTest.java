package top.b0x0.juc.thread.Volatile;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatileTest {
    static boolean b = true;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newSingleThreadExecutor();

        final int[] i = new int[1];
        i[0] = 100;

        service.execute(() -> {
            // 线程使用b值
            while (b) {
                System.out.println(i[0]);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i[0]++;
                System.out.println(i[0]);
            }
            System.out.println("son top.b0x0.juc.thread over");
        });

        Thread.sleep(10);

        // 主线程 修改b值
        b = false;
        System.out.println("main over");

//        service.shutdown();
    }

    public static void f() {
        System.out.println("f");
    }
}
