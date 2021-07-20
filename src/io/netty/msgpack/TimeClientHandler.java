package io.netty.msgpack;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.collections4.CollectionUtils;
import org.msgpack.MessagePack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimeClientHandler extends ChannelHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(TimeClientHandler.class.getName());
    int count;

    public TimeClientHandler() {
    }

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

//    public static void main(String[] args) {
//        byte[] bytes = intToByteArray(2048);
//        for (byte aByte : bytes) {
//            System.out.println(aByte);
//        }
//        bytes = short2ByteNew((short) 2048);
//        for (byte aByte : bytes) {
//            System.out.println(aByte);
//        }
//    }

    public byte[] short2ByteNew(short x) {
        byte high = (byte) (0x00FF & (x >> 8));//定义第一个byte
        byte low = (byte) (0x00FF & x);//定义第二个byte
//        System.out.println(high);//打印第一个byte值
//        System.out.println(low);//打印第二个byte值
        byte[] bytes = new byte[2];
        bytes[0] = high;
        bytes[1] = low;
        return bytes;
    }

    private static ExecutorService executorService = Executors.newFixedThreadPool(6);
    // TODO 多线程测试
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i <1000 ; i++) {
            SmartRPCRequest request = new SmartRPCRequest();
            request.setA(i);
            request.setB(i);
            request.setId(i);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    String requestJson = JSONObject.toJSONString(request);
                    ctx.writeAndFlush(requestJson);
                }
            });
        }
        // TODO 多线程改造
//        SmartRPCRequest request = new SmartRPCRequest();
//        request.setA(1);
//        request.setB(2);
//        request.setId(101);
//        byte[] bytes = requestJson.getBytes("utf-8");
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
//
//        ByteBuf firstMessage = null;
//        firstMessage = Unpooled.buffer(allBytes.length);
//        firstMessage.writeBytes(allBytes);
//        ctx.write(allBytes);

//        SmartRPCRequest request2 = new SmartRPCRequest();
//        request2.setA(100);
//        request2.setB(200);
//        request2.setId(102);
//
//        requestJson = JSONObject.toJSONString(request2);
//        bytes = requestJson.getBytes("utf-8");
//        firstMessage = Unpooled.buffer(bytes.length);
//        firstMessage.writeBytes(bytes);
//        ctx.write(bytes);

//        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Get result msg = " + msg + " , msg class = " + msg.getClass().getSimpleName());

//        MessagePack messagePack = new MessagePack();
//        List list = (List) msg;
//        if (CollectionUtils.isEmpty(list))
//            throw new RuntimeException("list is EMPTY");
//        SmartRPCResponse smartRPCResponse = MsgPackUtil.deserializationObject(list, SmartRPCResponse.class);

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
