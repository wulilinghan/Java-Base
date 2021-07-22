package JUC.aqs.semaphore;

import java.util.concurrent.Semaphore;

/**
 * // 需求
 * // 针对某个接口方法的访问，同一时刻 只允许 3 个线程同时访问
 * // 并发线程数是100个
 *
 * @author ManJiis
 * @since 2020/5/19 18:48
 */
public class SemaphoreLean2 {
    public static void main(String[] args) {
        // 需求
        // 针对某个接口方法的访问，同一时刻 只允许 3 个线程同时访问
        // 并发线程数是100个
        Service service = new Service();
        for (int i = 0; i < 1000000; i++) {
            Thread thread = new Thread(new RequestTask(service));
            thread.setName("线程" + i);
            thread.start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class RequestTask implements Runnable {
    static Semaphore semaphore = new Semaphore(3);
    private final Service service;

    public RequestTask(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        boolean b = semaphore.tryAcquire(1);
        if (!b) {
            System.out.println("抢单失败");
            return;
        }
        service.f();
        semaphore.release(1);
    }
}

class Service {
    public void f() {
        try {
            System.out.println(Thread.currentThread().getName() + "开始了");
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + "结束了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
