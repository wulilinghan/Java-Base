package io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimeServer {

    public void bind(int port) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(2);// 管理accept连接的线程组
        EventLoopGroup workGroup = new NioEventLoopGroup(4);// 处理具体业务操作的线程组
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                           .channel(NioServerSocketChannel.class)
                           .option(ChannelOption.SO_BACKLOG, 1024) // 输入连接指示（对连接的请求）的最大队列长度被设置为 backlog 参数。如果队列满时收到连接指示，则拒绝该连接。
                           .childHandler(new ChildChannelHandler());
            // 绑定端口同步等待成功
            ChannelFuture f = serverBootstrap.bind(port).sync();
            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } finally {
            // 退出释放资源
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            // 遍历byteBuf中的可读字节，判断看是否有 换行 符号 "\n" 或者 "\r\n"
            ch.pipeline().addLast(new LineBasedFrameDecoder(1024)); // 解码帧的最大长度
            ch.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
            ch.pipeline().addLast(new TimeServerHandler());
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        System.out.println("a".getBytes().length);

        int port = 8887;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (Exception e) {
            }
        }
        new TimeServer().bind(port);
    }

}
