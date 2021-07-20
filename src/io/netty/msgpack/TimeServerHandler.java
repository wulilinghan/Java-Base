package io.netty.msgpack;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;


public class TimeServerHandler extends ChannelHandlerAdapter {
    private int count;

    public byte[] intToByteArray(int i) {
        byte[] result = new byte[2];
//        result[0] = (byte) ((i >> 24) & 0xFF);
//        result[1] = (byte) ((i >> 16) & 0xFF);
//        result[2] = (byte)((i >> 8) & 0xFF);
//        result[3] = (byte)(i & 0xFF);
        result[0] = (byte) ((i >> 8) & 0xFF);
        result[1] = (byte) (i & 0xFF);
        return result;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server get request msg = " + msg + " , it's class = " + msg.getClass());

        SmartRPCRequest smartRPCRequest = JSONObject.parseObject((String) msg, SmartRPCRequest.class);

//        List list = (List) msg;
//        if (CollectionUtils.isEmpty(list))
//            throw new RuntimeException("list is EMPTY");
//        SmartRPCRequest smartRPCRequest = MsgPackUtil.deserializationObject(list, SmartRPCRequest.class);
//        System.out.println("Request msg Size = " + list.size());

        SmartRPCResponse response = new SmartRPCResponse();
        response.setId(smartRPCRequest.getId());
        response.setData(smartRPCRequest.getA() + "+" + smartRPCRequest.getB() + " = " + (smartRPCRequest.getA() + smartRPCRequest.getB()));
        System.out.println("" + msg + " , count = " + ++count);

        String responseJson = JSONObject.toJSONString(response);
//        byte[] bytes = responseJson.getBytes("utf-8");
//
//        if (bytes.length > 65535)
//            throw new RuntimeException("data length over");
//
//        int len = bytes.length;
//
//        byte[] lenBytes = intToByteArray(len);
//
//        byte[] allBytes = new byte[lenBytes.length + bytes.length];
//        System.arraycopy(lenBytes, 0, allBytes, 0, lenBytes.length);
//        System.arraycopy(bytes, 0, allBytes, lenBytes.length, bytes.length);
//
//        ByteBuf firstMessage = null;
//        firstMessage = Unpooled.buffer(allBytes.length);
//        firstMessage.writeBytes(allBytes);
//        ctx.write(allBytes);

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
