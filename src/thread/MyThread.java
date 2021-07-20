package thread;

public class MyThread  extends Thread {
    @Override
    public void run() {
        System.out.println("I'm thread code");
    }
    public static void main(String[] args) {
        new MyThread().start();
    }
}
