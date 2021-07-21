package rpc.socket.bserver;

import rpc.socket.bserver.rpcServer.ReisterServer;
import rpc.socket.bserver.rpcServer.RpcServer;
import rpc.socket.bserver.rpcServer.ServerWork;
import rpc.socket.bserver.rpcServer.ServiceLoader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author GY
 * @since 2019年11月18日
 * @说明: 服务提供方启动类
 */
public class ServerStart {

    /**
     * 使用额外线程专门启动工作组线程，并且轮询就位请求任务
     */
    private static ExecutorService startWorkThread = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        // 具体service服务实例化——根据项目基础包路径
        ServiceLoader.loadService("rpc.socket.bserver");
        // 根据已装载的service实例+服务信息，注册服务信息到文件(注册中心)
        ReisterServer.regist();
        startWorkThread.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("启动工作组等待处理任务");
                // 启动工作线程组，处理请求任务
                ServerWork.startWork();
            }
        });
        // 启动服务监听请求
        RpcServer.serverStart();
    }

}
