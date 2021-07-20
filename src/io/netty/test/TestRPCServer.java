package io.netty.test;

import com.alibaba.fastjson.JSONObject;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * Created By gao_e on 2020/3/11 0:34
 */
public class TestRPCServer {

    public static void main(String[] args) {
        int port = 10000;
        try {
            new TestRPCServer().bind(port);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void bind(int port) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();// 管理accept连接的线程组
        EventLoopGroup workGroup = new NioEventLoopGroup();// 处理具体业务操作的线程组
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024) // 输入连接指示（对连接的请求）的最大队列长度被设置为 backlog 参数。如果队列满时收到连接指示，则拒绝该连接。
                    .childHandler(new ChannelInitializer(){
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            // 解决粘、拆包问题
                            // 字符串最大字节数 65535，也就是不能超过64M
                            pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
                            pipeline.addLast("frameEncoder", new LengthFieldPrepender(2));
                            // 以字符串进行编码、解码
                            pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                            pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                            ch.pipeline().addLast(new ChannelHandlerAdapter(){
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    System.out.println("获取到一个客户端的请求数据 msg = " + msg);
                                    System.out.println("Server get data msg = " + msg + " , msg class is " + msg.getClass());
                                    SmartRPCRequest smartRPCRequest = JSONObject.parseObject((String) msg, SmartRPCRequest.class);
                                    // 同步调用服务等待响应，此方法内部会产生阻塞，直到获取到返回结果
                                    SmartRPCResponse smartRPCResponse = null;
                                    try {
                                        smartRPCResponse = new SmartRPCResponse();
                                        smartRPCResponse.setRequestId("123999");
                                        smartRPCResponse.setData("Hello TLX");
                                    } catch (Exception e) {
                                        System.out.println(e);
                                        smartRPCResponse = new SmartRPCResponse();
                                        smartRPCResponse.setSuccess(false);
                                        smartRPCResponse.setRequestId(smartRPCRequest.getRequestId());
                                    }
                                    // 转json后，IO写回响应客户端
                                    String responseJson = JSONObject.toJSONString(smartRPCResponse);
                                    ctx.writeAndFlush(responseJson);
                                    ctx.flush();
                                }
                                @Override
                                public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                                    System.out.println(" server channelReadComplete ");
                                    ctx.flush();
                                }
                                @Override
                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                    ctx.close();
                                }
                            });
                        }
                    });
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
