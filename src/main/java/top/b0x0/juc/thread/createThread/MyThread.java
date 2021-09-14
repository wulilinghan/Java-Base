package top.b0x0.juc.thread.createThread;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("I'm top.b0x0.juc.thread code");
    }

    public static void main(String[] args) {
        new MyThread().start();
    }
}
