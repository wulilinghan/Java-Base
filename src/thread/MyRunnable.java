package thread;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("I'm runnable code");
    }
    public static void main(String[] args) {
        new Thread(new MyRunnable()).start();
    }
}
