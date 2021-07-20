package io.netty.test;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * Created By gao_e on 2020/3/8 15:03
 */
public class ServerChildChannelHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 解决粘、拆包问题
        // 字符串最大字节数 65535，也就是不能超过64M
        pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
        pipeline.addLast("frameEncoder", new LengthFieldPrepender(2));
        // 以字符串进行编码、解码
        pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
        ch.pipeline().addLast(new ServerHandler());
    }
}
