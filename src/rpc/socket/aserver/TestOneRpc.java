package rpc.socket.aserver;

import rpc.socket.aserver.rpcclient.RpcClientInvocationHandler;
import rpc.socket.contract.ParamDTO;
import rpc.socket.contract.Result;
import rpc.socket.contract.SimpleServiceFacade;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author GY
 * @since 2019年11月18日
 * @说明: 服务调用方启动类
 */
public class TestOneRpc {
    // 被测试的远程接口
    private static SimpleServiceFacade simpleServiceFacade;

    static {
        try {
            //在加载当前类的时候将RPC框架服务启动
            Class.forName("rpc.socket.aserver.rpcclient.RpcClientStart", true, Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 装载注入service——实则是一个动态代理对象
        simpleServiceFacade = RpcClientInvocationHandler.getProxyInstance(SimpleServiceFacade.class);
    }

    /**
     * 模拟多线程(多用户)并发访问
     */
    static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3,
            6, 3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(500), new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * @param args
     */
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 6, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20), new ThreadPoolExecutor.CallerRunsPolicy());
        // 使用多线程提交10个远程调用请求
        for (int i = 1; i <= 10; i++) {
            final int a = i;
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    Result result = simpleServiceFacade.addNumOf(new ParamDTO(a, a));
                    System.out.println("远程调用的最终结果为：" + result);
                }
            });
        }
    }
}

