package io.netty.json_length;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class TimeClient {

    public void connect(int port, String host) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup(3);

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChildChannelHandler());
            // 发起同步连接操作
            ChannelFuture future = bootstrap.connect(host, port).sync();
            // 同步等待客户端链路关闭
            future.channel().closeFuture().sync();
        } finally {
            // 优雅退出，释放NIO线程组
            group.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
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

            ch.pipeline().addLast(new TimeClientHandler());
        }
    }
    public static void main(String[] args) throws Exception {
        int port = 8887;
        new TimeClient().connect(port, "192.168.0.105");
    }
}