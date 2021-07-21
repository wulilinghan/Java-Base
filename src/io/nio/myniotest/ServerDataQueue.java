package io.nio.myniotest;

import rpc.socket.common.Request;
import rpc.socket.common.Response;
import rpc.socket.contract.ParamDTO;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: GY.
 * @Description: TODO()
 * @since:Created in 2019/11/20 0020.
 * @Modified By:
 */
public class ServerDataQueue {
    public static ConcurrentHashMap<String,Request> requestMap =
            new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String,Response> responseMap =
            new ConcurrentHashMap<>();
//    static {
//        Request request1 = new Request();
//        request1.setArgs(new Object[]{new ParamDTO(1,1)});
//        request1.setRequestId("1");
//        Request request2 = new Request();
//        request2.setArgs(new Object[]{new ParamDTO(100,100)});
//        request2.setRequestId("2");
//        requestMap.put("1",request1);
//        requestMap.put("2",request2);
//    }
}
