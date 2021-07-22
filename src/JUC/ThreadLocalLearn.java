package JUC;

/**
 * @program: java-base->ThreadLocalLearn
 * @description: ThreadLocal使用
 * @author: ManJiis
 * @since: 2019-08-21 13:43
 **/
public class ThreadLocalLearn {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            final int num = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("当前线程名称" + Thread.currentThread().getName() + "——对应num:" + num);
                    UserInfo user = new UserInfo(Thread.currentThread().getName() + "——" + num, num);
                    // 在线程开始的时候，将数据放入ThreadLocal
                    Manager.setThreadLocalValue(user);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 在线程 过程中的任何位置可以随时取用
                    System.out.println(Manager.user());
                }
            }).start();
        }
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        ThreadLocal<UserInfo> threadLocal = Manager.threadLocal;
//        System.out.println(threadLocal);
    }
}

class Manager {
    private static ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

    public static UserInfo user() {
        return threadLocal.get();
    }

    /**
     * 放置thread中的数据
     */
    public static void setThreadLocalValue(UserInfo info) {
        threadLocal.set(info);
    }

    /**
     * 删除thread中的数据
     */
    public static void removeThreadLocalValue() {
        threadLocal.remove();
    }
}

class UserInfo {
    public UserInfo(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    String name;
    Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}