package io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TimeClient {

    public static void main(String[] args) throws Exception {
//        System.out.println("qqq"+System.getProperty("line.separator")+"aaaa");
        int port = 8887;
        new TimeClient().connect(port, "127.0.0.1");
    }

    public void connect(int port, String host) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup(6);

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
            ch.pipeline().addLast(new LineBasedFrameDecoder(1024));// 解码帧的最大长度
            ch.pipeline().addLast(new StringDecoder());
            ch.pipeline().addLast(new TimeClientHandler());
        }
    }

}