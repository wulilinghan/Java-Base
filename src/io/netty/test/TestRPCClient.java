package io.netty.test;

import com.alibaba.fastjson.JSONObject;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created By gao_e on 2020/3/11 0:16
 */
public class TestRPCClient {
    public static void main(String[] args) {
        int port = 9999;
        String host = "127.0.0.1";
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        ChannelInitializer channelInitializer = new ChannelInitializer() {
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
                    private ExecutorService executorService = Executors.newSingleThreadExecutor();
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        System.out.println("...............开始执行请求写出操作...............");
                        SmartRPCRequest request = new SmartRPCRequest();
                        request.setItfcName("smart.rpc.producer.contract.facade.ISmartRPCTestService");
                        request.setMethodName("hello");
                        request.setMethodParameTypes(new Class[]{String.class});
                        request.setRequestId("123999");
                        request.setServiceFullName("smartRPCTestService");
                        request.setServiceId("smartRPCTestService");
                        request.setArgs(new Object[]{"TLX"});
                        String requestJson = JSONObject.toJSONString(request);
                        ctx.writeAndFlush(requestJson);
                        ctx.flush();
//                        executorService.execute(new Runnable() {
//                            @Override
//                            public void run() {
//
//                            }
//                        });
                    }
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        System.out.println("Get result msg = " + msg + " , msg class = " + msg.getClass().getSimpleName());
                        SmartRPCResponse smartRPCResponse = JSONObject.parseObject((String) msg, SmartRPCResponse.class);
                        // 操作结果
                    }
                    @Override
                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                        System.out.println("unexpected exception from downstream : " + cause.getMessage());
                        ctx.close();
                    }
                });
            }
        };
        bootstrap.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(channelInitializer);
        // 发起同步连接操作
        ChannelFuture future = null;
        try {
            future = bootstrap.connect(host, port).sync();
            // 同步等待客户端链路关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 优雅退出，释放NIO线程组
            System.out.println("Over");
            group.shutdownGracefully();
        }
    }
}
