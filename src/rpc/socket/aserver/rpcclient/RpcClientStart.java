package rpc.socket.aserver.rpcclient;

import java.util.Set;

/**
 * RPC 客户端启动器
 */
public class RpcClientStart {
    static {
        try {
            // 加载注册中心信息
            Class.forName("rpc.socket.aserver.rpcclient.ServerRegistry", true, Thread.currentThread().getContextClassLoader());
            Set<String> hostAndPort = ServerRegistry.hostAndPort;
            for (String s : hostAndPort) {
                String[] split = s.split(",");
                // 创建跟服务端的长连接 针对每一个服务提提供方，都用一个线程去维护一个长连接
                new RpcClientSocket(split[0], Integer.parseInt(split[1])).createConnect();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("服务连接异常");
        }
    }
}
