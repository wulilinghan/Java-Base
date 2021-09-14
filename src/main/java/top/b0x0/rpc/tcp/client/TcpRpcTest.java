package top.b0x0.rpc.tcp.client;

import java.io.IOException;
import java.net.UnknownHostException;

import top.b0x0.rpc.tcp.Service;

public class TcpRpcTest {

    private static Service service;

    public static void main(String[] args)
            throws UnknownHostException, IOException {
        init();
        System.out.println("tcp top.b0x0.rpc start ... ");
        System.out.println("RPC result is : " + service.sayHello("GaoYue"));
        System.out.println("RPC result is : " + service.sayHello("TLX"));
//        destroy();
    }

    public static void init() throws IOException {
        service = ProxyHandler.getProxyInstance(Service.class);
        ClientSocket.startSocket();
    }

    public static void destroy() throws IOException {
        ClientSocket.close();
    }

}
