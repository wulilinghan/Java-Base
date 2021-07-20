package io.netty;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeServerHandler extends ChannelHandlerAdapter {
    private int count;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        // 获取操作系统对应的换行符
        // System.getProperty("line.separator");

//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req, "UTF-8").substring(0, req.length - System.getProperty("line.separator").length());
        // 用了 ch.pipeline().addLast(new StringDecoder()); 之后 直接使用String 接收 msg
        String body = (String) msg;
        System.out.println("The time server receive order : " + body + " ===== count = " + ++count);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
                ? new Date(System.currentTimeMillis()).toString() + System.getProperty("line.separator")
                : "BAD ORDER" + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(resp);
        ctx.flush();
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
