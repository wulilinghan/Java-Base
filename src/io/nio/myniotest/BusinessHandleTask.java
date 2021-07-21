package io.nio.myniotest;

import rpc.socket.common.Request;
import rpc.socket.common.Response;
import rpc.socket.contract.ParamDTO;
import rpc.socket.contract.Result;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: GY.
 * @Description: TODO()
 * @since:Created in 2019/11/21 0021.
 * @Modified By:
 */
public class BusinessHandleTask implements Runnable {
    private Request request;
    private String key;

    public BusinessHandleTask(String key,Request request) {
        this.request = request;
        this.key = key;
    }

    private static ThreadPoolExecutor businessHandleThreadGroup =
            new ThreadPoolExecutor(3, 5, 3,
                    TimeUnit.SECONDS, new ArrayBlockingQueue<>(200), new ThreadPoolExecutor.DiscardOldestPolicy());
    @Override
    public void run() {
        Object[] args = request.getArgs();
        ParamDTO arg = (ParamDTO) args[0];
        Response response = new Response();
        Result result = new Result();
        result.setSum(arg.getNum1()+arg.getNum2());
        response.setData(result);
        ServerDataQueue.responseMap.put(key,response);
        ServerDataQueue.requestMap.remove(key);
    }
    public void handle(){
        businessHandleThreadGroup.execute(this);
    }
}
