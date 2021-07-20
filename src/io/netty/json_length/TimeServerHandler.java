package io.netty.json_length;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


public class TimeServerHandler extends ChannelHandlerAdapter {
    private int count;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("Server get request msg = " + msg + " , it's class = " + msg.getClass());
        SmartRPCRequest smartRPCRequest = JSONObject.parseObject((String) msg, SmartRPCRequest.class);
        SmartRPCResponse response = new SmartRPCResponse();
        response.setId(smartRPCRequest.getId());
        response.setData(smartRPCRequest.getA() + " + " + smartRPCRequest.getB() + " = " + (smartRPCRequest.getA() + smartRPCRequest.getB()));
        System.out.println(msg + " , count = " + ++count);
        String responseJson = JSONObject.toJSONString(response);
        ctx.writeAndFlush(responseJson);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

}
