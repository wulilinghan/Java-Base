package io.netty.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created By gao_e on 2020/3/8 14:52
 */
public class ServerStartHandler implements Runnable {

    private Integer port;

    public ServerStartHandler(Integer port) {
        this.port = port;
    }

    private static final ThreadPoolExecutor starterServerTP =
            new ThreadPoolExecutor(1, 1, 0,
                    TimeUnit.SECONDS, new SynchronousQueue(), new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public void run() {
        // 开启server端，监听连接、数据请求
        try {
            this.bind(port);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void useInnerPrivateThreadPoolExecutor() {
        starterServerTP.execute(this);
    }


    private void bind(int port) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(2);// 管理accept连接的线程组
        EventLoopGroup workGroup = new NioEventLoopGroup(4);// 处理具体业务操作的线程组
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024) // 输入连接指示（对连接的请求）的最大队列长度被设置为 backlog 参数。如果队列满时收到连接指示，则拒绝该连接。
                    .childHandler(new ServerChildChannelHandler());
            // 绑定端口同步等待成功
            ChannelFuture f = serverBootstrap.bind(port).sync();

            System.out.println("当前服务启动成功,等待客户端连接,端口为：" + port);

            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } finally {
            // 退出释放资源
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

}
