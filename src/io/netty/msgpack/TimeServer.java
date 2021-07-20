package io.netty.msgpack;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

public class TimeServer {

    public void bind(int port) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);// 管理accept连接的线程组
        EventLoopGroup workGroup = new NioEventLoopGroup(3);// 处理具体业务操作的线程组
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

//            ch.pipeline().addLast("frameDecoder",
//                    new LengthFieldBasedFrameDecoder(65535,
//                            0,2,0,2));
//            ch.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
//            ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
//            ch.pipeline().addLast("msgpack encoder", new ServerMsgpackEncoder());
//            ch.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));


            ChannelPipeline pipeline = ch.pipeline();
            pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 2, 0, 2));
            pipeline.addLast("frameEncoder", new LengthFieldPrepender(2));
            pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
            pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));

            ch.pipeline().addLast(new TimeServerHandler());
        }
    }

    public static void main(String[] args) throws InterruptedException {
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
