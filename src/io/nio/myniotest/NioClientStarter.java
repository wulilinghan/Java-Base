package io.nio.myniotest;

import rpc.socket.common.Request;
import rpc.socket.contract.ParamDTO;

import java.util.concurrent.ConcurrentHashMap;

public class NioClientStarter {

    public static void main(String[] args) {
        ConcurrentHashMap<String, Request> requestMap = new ConcurrentHashMap<>();
        Request request1 = new Request();
        request1.setArgs(new Object[]{new ParamDTO(1, 1)});
        request1.setRequestId("1");
        Request request2 = new Request();
        request2.setArgs(new Object[]{new ParamDTO(100, 100)});
        request2.setRequestId("2");
        Request request3 = new Request();
        request3.setArgs(new Object[]{new ParamDTO(1000, 1000)});
        request3.setRequestId("3");
        requestMap.put("1", request1);
        requestMap.put("2", request2);
        requestMap.put("3", request3);
        // 添加请求
        ClientDataQueue.requestMap.putAll(requestMap);
        new NioClient("127.0.0.1", NioServerStarter.serverPort).start();
    }

}
