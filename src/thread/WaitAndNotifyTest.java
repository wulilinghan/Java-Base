package thread;

public class WaitAndNotifyTest {

    static volatile Integer step = 1;
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (step != 2) {
                    synchronized ("") {
                        try {
                            "".wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("step2");
                synchronized ("") {
                    "".notifyAll();
                }
            }
        }).start();
        System.out.println("step1");
        step = 2;
        synchronized ("") {
            "".notifyAll();
            "".wait();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("step3");
                step = 4;
                synchronized ("") {
                    "".notifyAll();
                }
            }
        }).start();
        if(step != 4) {
            synchronized ("") {
                "".wait();
            }
        }
        System.out.println("end");
    }
}
