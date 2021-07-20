package io.netty.test;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created By gao_e on 2020/3/8 15:24
 */
public class ServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("获取到一个客户端的请求数据 msg = " + msg);

        System.out.println("Server get data msg = " + msg + " , msg class is " + msg.getClass());

        SmartRPCRequest smartRPCRequest = JSONObject.parseObject((String) msg, SmartRPCRequest.class);

        // TODO 同步调用服务等待响应
        SmartRPCResponse smartRPCResponse = new SmartRPCResponse();
        smartRPCResponse.setCode(111);
        ctx.writeAndFlush(smartRPCResponse);
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println(" server channelReadComplete ");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
