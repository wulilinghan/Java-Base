package top.b0x0.rpc.socket.bserver.rpcServer;

import top.b0x0.rpc.socket.bserver.ServerConfig;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务提供者
 */
public class RpcServer {

    // 主线程启动ServerSocker 监听连接请求，创建Socket
    public static void serverStart() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(ServerConfig.port);
            System.out.println("bserver完成启动，等待调用方请求");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("服务启动异常");
        }
        try {
            while (true) {
                // 一个服务提供者可以为很多调用方提供服务
                Socket socket = serverSocket.accept();
                new RpcServerSocket(socket).socketOpt();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
