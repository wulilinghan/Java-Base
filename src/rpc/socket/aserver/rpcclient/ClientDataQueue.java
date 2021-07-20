package rpc.socket.aserver.rpcclient;

import rpc.socket.common.Request;
import rpc.socket.common.Response;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 线程间交互的 公共 数据 介质
 */
public class ClientDataQueue {
    /**
     * 用于存储请求
     */
    public static volatile ConcurrentHashMap<String, Request> requestMap = new ConcurrentHashMap<>(512);
    /**
     * 用于存储响应
     */
    public static volatile ConcurrentHashMap<String, Response> responseMap = new ConcurrentHashMap<>(512);
}
