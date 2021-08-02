package juc.thread.threadLocal;


/**
 * ManJiis
 */
public class AdminManager {
    private static final ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

    /**
     * 获取所有threadlocal中的数据
     */
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

    public static void main(String[] args) {
        f();
    }

    public static void f() {
        User user = new User();
        // 存储数据库
        // 很长 ...

        // 这句代码
        user = null;
        // GC了
    }

}
