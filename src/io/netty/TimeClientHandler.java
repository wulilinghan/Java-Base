package io.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeClientHandler extends ChannelHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(TimeClientHandler.class.getName());
    private byte[] request;
    int count;

    public TimeClientHandler() {

        // 获取操作系统对应的换行符
        // System.getProperty("line.separator");

        request = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf firstMessage = null;
        for (int i = 0; i <= 100; i++) {
            firstMessage = Unpooled.buffer(request.length);
            firstMessage.writeBytes(request);
            ctx.writeAndFlush(firstMessage);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String responseStr = (String) msg;
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] response = new byte[buf.readableBytes()];
//        buf.readBytes(response);
//        String body = new String(response, "UTF-8");
        String body = responseStr;
        logger.info("Now is : {} count = {}", body, ++count);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warn("unexpected exception from downstream : {}", cause.getMessage());
        ctx.close();
    }

}
