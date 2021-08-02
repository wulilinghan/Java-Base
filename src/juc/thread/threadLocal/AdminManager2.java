package juc.thread.threadLocal;

public class AdminManager2 {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
//    /**
//     * 获取所有threadlocal中的数据
//     */
//    public static UserInfo user() {
//        return threadLocal.get();
//    }
//    /**
//     * 放置thread中的数据
//     */
//    public static void setThreadLocalValue(UserInfo info) {
//        threadLocal.set(info);
//    }
//    /**
//     * 删除thread中的数据
//     */
//    public static void removeThreadLocalValue() {
//        threadLocal.remove();
//    }
}
