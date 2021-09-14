package top.b0x0.rpc.socket.bserver.rpcServer;

import top.b0x0.rpc.socket.common.Request;
import top.b0x0.rpc.socket.common.Response;

import java.util.concurrent.ConcurrentHashMap;

public class ServerDataQueue {

    /**
     * 用于存储请求
     */
    public static ConcurrentHashMap<String, Request> requestMap = new ConcurrentHashMap<>(512);
    /**
     * 用于存储响应
     */
    public static ConcurrentHashMap<String, Response> responseMap = new ConcurrentHashMap<>(512);

}
