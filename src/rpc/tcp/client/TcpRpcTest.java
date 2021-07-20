package rpc.tcp.client;

import java.io.IOException;
import java.net.UnknownHostException;

import rpc.tcp.Service;

public class TcpRpcTest {

    private static Service service;

    public static void main(String[] args)
            throws UnknownHostException, IOException {
        init();
        System.out.println("tcp rpc start ... ");
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
