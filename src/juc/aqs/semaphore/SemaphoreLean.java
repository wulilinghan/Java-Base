package juc.aqs.semaphore;

import common.NamedThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程A、B、C，分别打印ABC，要求按顺序重复输出10次ABC
 *
 * @author ManJiis
 */
public class SemaphoreLean {
    static ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(3, 3, 1,
                    TimeUnit.SECONDS, new ArrayBlockingQueue<>(3),
                    new NamedThreadFactory("Semaphore", false));

    public static void main(String[] args) {

        Semaphore a = new Semaphore(1);
        Semaphore b = new Semaphore(0);
        Semaphore c = new Semaphore(0);

        threadPoolExecutor.execute(new PrintABC(a, 'A', b));
        threadPoolExecutor.execute(new PrintABC(b, 'B', c));
        threadPoolExecutor.execute(new PrintABC(c, 'C', a));

        threadPoolExecutor.shutdown();
    }
}

class PrintABC implements Runnable {
    private final Semaphore semaphore;
    private final Semaphore nextSamaphore;
    private final char objChar;

    public PrintABC(Semaphore samphone, char objChar, Semaphore nextSamaphore) {
        this.semaphore = samphone;
        this.objChar = objChar;
        this.nextSamaphore = nextSamaphore;
    }

    @Override
    public void run() {
        int i = 1;
        do {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(objChar);
            nextSamaphore.release();
            i++;
        } while (i < 11);
    }
}
