package thread;

/**
 * @program: java-base->SyncTest
 * @description:
 * @author: ManJiis
 * @since: 2019-09-18 18:45
 **/
public class SyncTest {
    static volatile Integer obj = 0;

    public static void main(String[] args) throws InterruptedException {
//        for (int i = 1; i <= 10; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
////                    synchronized (obj) {
//                        obj++;
////                    }
//                    try {
//                        Thread.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }
//        Thread.sleep(2000);
//        System.out.println(obj);
        A a = new A();
//        A a2 = new A();
        new Thread(new Runnable() {
            @Override
            public void run() {
                a.f1();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                a.f3();
            }
        }).start();
    }
}

class A {
    public void f1() {
        synchronized (A.class) {
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }

    public synchronized void f2() {
        for (int i = 100; i <= 110; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

    public synchronized static void f3() {
        for (int i = 200; i <= 210; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }
}