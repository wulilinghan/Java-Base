package rpc.tcp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import rpc.tcp.Constant;
import rpc.tcp.Service;

public class ProviderServiceSocket {

    private ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 6, 3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(200), new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * 接口名 跟 具体实现实例 的映射
     */
    private static Map<String, Object> mapCls;
    static {
        mapCls = new HashMap<String, Object>();
        try {
            // 初始化
            mapCls.put(Service.class.getName(), ServiceImpl.class.newInstance());
            System.out.println("mapCls 加载完毕！");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> getMap() {
        return mapCls;
    }

    public void serverStart() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(Constant.PORT);
            System.out.println("等待客户端连接...");
            while (true) {
                Socket socket = null;
                try {
                    socket = server.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 启用多线程处理每一个客户端连接
                threadPool.execute(new ServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
