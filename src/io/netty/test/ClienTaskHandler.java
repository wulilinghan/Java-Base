package io.netty.test;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created By gao_e on 2020/3/10 10:09
 */
public class ClienTaskHandler extends ChannelHandlerAdapter {

//    private ClientConnectServerHandler clientConnectServerHandler;

    public ClienTaskHandler() {

    }

//    public ClienTaskHandler(ClientConnectServerHandler clientConnectServerHandler) {
//        this.clientConnectServerHandler = clientConnectServerHandler;
//    }

    private static ExecutorService executorService = Executors.newFixedThreadPool(3);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接服务端成功，开始从请求队列获取请求数据，进行网络通信请求调用");

        SmartRPCRequest smartRPCRequest = new SmartRPCRequest();
        smartRPCRequest.setItfcName("com.itcast.ATestService");
        String requestJson = JSONObject.toJSONString(smartRPCRequest);
        ctx.writeAndFlush(requestJson);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Get result msg = " + msg + " , msg class = " + msg.getClass().getSimpleName());
        SmartRPCResponse smartRPCResponse = JSONObject.parseObject((String) msg, SmartRPCResponse.class);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println(" server channelReadComplete ");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("unexpected exception from downstream : " + cause.getMessage());
        ctx.close();
    }

}
