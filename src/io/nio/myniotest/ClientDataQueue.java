package io.nio.myniotest;

import rpc.socket.common.Request;
import rpc.socket.common.Response;

import java.util.concurrent.ConcurrentHashMap;

public class ClientDataQueue {
    public static ConcurrentHashMap<String,Request> requestMap =
            new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String,Response> responseMap =
            new ConcurrentHashMap<>();
}
