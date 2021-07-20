package thread;

public class VolatileTest {
    static boolean b = true;
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 线程使用b值
                while(b) {
//                    System.out.println("100");
//                    try {
//                        Thread.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    int i = 100;
                    i++;
                    f();
                    continue;
                }
                System.out.println("son thread over");
            }
            public void f(){
                System.out.println("f");
            }
        }).start();
        Thread.sleep(10);
        // 主线程 修改b值
        b = false;
        System.out.println("main over");
    }

}
