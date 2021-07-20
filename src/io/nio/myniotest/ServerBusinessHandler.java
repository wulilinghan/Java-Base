package io.nio.myniotest;

import org.apache.commons.collections4.CollectionUtils;
import rpc.socket.common.Request;
import rpc.socket.common.Response;
import rpc.socket.contract.ParamDTO;
import rpc.socket.contract.Result;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: GY.
 * @Description:
 * @Date:Created in 2019/11/21 0021.
 * @Modified By:
 */
public class ServerBusinessHandler implements Runnable{
    private static ExecutorService businessListenerThread = Executors.newSingleThreadExecutor();
    @Override
    public void run() {
        ConcurrentHashMap.KeySetView<String, Request> integers = ServerDataQueue.requestMap.keySet();
//        ConcurrentHashMap.KeySetView<String, Response> strings = ServerDataQueue.responseMap.keySet();
        while(true) {
            if(CollectionUtils.isEmpty(integers))
                continue;
            for (String key:integers) {
                Request request = ServerDataQueue.requestMap.get(key);
                new BusinessHandleTask(key,request).handle();
            }
        }
    }
    public void start() {
        businessListenerThread.execute(this);
        System.out.println("业务线程组启动成功");
    }
}
