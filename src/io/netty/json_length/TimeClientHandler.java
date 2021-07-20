package io.netty.json_length;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimeClientHandler extends ChannelHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(TimeClientHandler.class.getName());
    int count;

    public TimeClientHandler() {
    }

    // 多线程测试
    private static ExecutorService executorService = Executors.newFixedThreadPool(6);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 1; i <= 1; i++) {
            SmartRPCRequest request = new SmartRPCRequest();
            request.setA(i);
            request.setB(i);
            request.setId(i);
            // 多线程改造 测试
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    String requestJson = JSONObject.toJSONString(request);
                    ctx.writeAndFlush(requestJson);
                }
            });
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Get result msg = " + msg + " , msg class = " + msg.getClass().getSimpleName());
        SmartRPCResponse smartRPCResponse = JSONObject.parseObject((String) msg, SmartRPCResponse.class);
        String body = smartRPCResponse.getData();
        logger.info("Now is : {} count = {}", body, ++count);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warn("unexpected exception from downstream : {}", cause.getMessage());
        ctx.close();
    }

}
