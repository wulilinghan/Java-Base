package top.b0x0.juc.thread.threadLocal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {
    // 模拟 当前map 就是 redis 存储 用户信息的
    static Map<String, UserInfo> map = new HashMap<>();
    // 对redis 存储的用户数据 做初始化，相当于10个用户全部登陆成功了
    static {
        map.put("1", new UserInfo(1, 999, "admmin"));
        map.put("2", new UserInfo(2, 100, "guest"));
        map.put("3", new UserInfo(3, 100, "gaoyue"));
        map.put("4", new UserInfo(4, 99, "test"));
        map.put("5", new UserInfo(5, 100, "tlx"));
        map.put("6", new UserInfo(6, 1000, "root"));
        map.put("7", new UserInfo(7, 100, "ccc"));
        map.put("8", new UserInfo(8, 100, "fff"));
        map.put("9", new UserInfo(9, 100, "omg"));
        map.put("10", new UserInfo(10, 100, "rng"));
    }
    // 这是模拟了 服务器 用来处理用户请求的线程池
    static ThreadPoolExecutor threadPoolExcutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(200), new ThreadPoolExecutor.CallerRunsPolicy());




    public static void main(String[] args) {
        // 模拟了10个用户并发请求
        for (int i = 1; i <= 10; i++) {
            // 假设 i为 token值
//            threadPoolExcutor.execute(new Handle(i));
            new Thread(new Handle(i),"top.b0x0.juc.thread:"+i).start();
        }
    }
}
class Handle implements Runnable {
    Integer token;
    public Handle(Integer token) {
        this.token = token;
    }
    @Override
    public void run() {
        //从程序中获取token对应的用户信息
        // 模拟过滤器中，根据用户token(jti)从reids中获取jwt userinfo数据
        UserInfo userInfo = ThreadLocalTest.map.get(token+"");
//        AdminManager.removeThreadLocalValue();
        System.out.println(Thread.currentThread().getName() + "  ------------  token = " + token);
        // (框架)设置用户数据到 当前线程 的 一个 存储数据的地方
//        Thread top.b0x0.juc.thread = Thread.currentThread();
        AdminManager.setThreadLocalValue(userInfo);

        //业务处理 handler
        Controller controller = new Controller();
        controller.f();
    }
}
class Controller {
    private Service service = new Service();
    public void f() {
        service.f();
    }
}
class Service {
    public void f() {
        // 打印当前访问的用户信息
        UserInfo user = AdminManager.user();
        System.out.println(Thread.currentThread().getName() + " user = " + user);
//        user = AdminManager.user();
//        System.out.println(Thread.currentThread().getName() + " user = " + user);
    }
}
