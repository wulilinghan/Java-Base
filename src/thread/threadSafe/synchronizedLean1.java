package thread.threadSafe;

import java.util.concurrent.TimeUnit;

/**
 * @Author G_Y
 * @Date 2020/8/30 20:19
 * @Description: // synchronized初级使用篇
 **/
public class synchronizedLean1 {

    public static void main(String[] args) {

        // 1
        /*final Human h1 = new Human();
        final Human h2 = new Human();
        new Thread(new Runnable() {
            @Override
            public void run() {
                h1.say();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                h2.say();
            }
        }).start();*/

        // 1
        /*final Human h1 = new Human();
        final Human h2 = h1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                h1.say();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                h2.hello();
            }
        }).start();*/

        // 2
        /*final Human h1 = new Human();
        new Thread(new Runnable() {
            @Override
            public void run() {
                h1.say();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                h1.bibi();
            }
        }).start();*/

        // 3
        /*final Human h1 = new Human();
        final Human h2 = new Human();
        new Thread(new Runnable() {
            @Override
            public void run() {
                h1.aiai();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                h2.aiai();
            }
        }).start();*/

        // 4
        /*final Human h1 = new Human();
        new Thread(new Runnable() {
            @Override
            public void run() {
                h1.cici();
            }
        }).start();*/
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                h1.didi();
            }
        }).start();*/
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                h1.eiei();
            }
        }).start();*/
    }
}

class Human {
    public static void sleep1ms() {
        for (int i = 1; i <= 10; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

    public synchronized void say() {
        Human.sleep1ms();
    }

    public synchronized void hello() {
        Human.sleep1ms();
    }

    public static synchronized void bibi() { // 当前类对象  Human.class
        Human.sleep1ms();
    }

    public void aiai() {
        synchronized (this.getClass()) {
            Human.sleep1ms();
        }
    }

    public void cici() {
        synchronized (Human.class) {
            Human.sleep1ms();
        }
    }

    public void didi() {
        synchronized ("") {
            Human.sleep1ms();
        }
    }

    public void eiei() {
        synchronized ("abcdefg") {
            Human.sleep1ms();
        }
    }
}

//class RunTask implements Runnable {
//
//    private Human human;
//
//    public RunTask(Human human) {
//        this.human = human;
//    }
//
//    @Override
//    public void run() {
//        human.say();
//    }
//}
