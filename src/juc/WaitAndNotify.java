package juc;

/**
 * @author ManJiis
 * @since 2020/7/21 18:41
 * @Description: // 学习如何使用wait & notify
 **/
public class WaitAndNotify {
    static Integer step = 0;//用来做控制的
    public static String id = "007";
    public static void main(String[] args) {
        // 线程1 打印 A，线程2 打印B
        new Thread(new PrintATask()).start();
        new Thread(new PrintBTask()).start();
        // 用wait跟notify来实现
    }
}
class PrintATask implements Runnable {
    @Override
    public void run() {
        for(int i =0;i<10;i++){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (WaitAndNotify.id) {
                if(WaitAndNotify.step != 0) {
                    try {
                        WaitAndNotify.id.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("A");
                WaitAndNotify.step = 1;
                WaitAndNotify.id.notify();
            }
        }
    }
}
class PrintBTask implements Runnable{
    @Override
    public void run() {
        for(int i =0;i<10;i++){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (WaitAndNotify.id) {
                if(WaitAndNotify.step != 1) {
                    try {
                        WaitAndNotify.id.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("B");
                WaitAndNotify.step = 0;
                WaitAndNotify.id.notify();
            }
        }
    }
}




class Request{
    Integer requestId;
    Object[] args;
}
class Response{
    Integer requestId;
    Object result;
}
class GetRequestTask implements Runnable {

    @Override
    public void run() {

    }
}
