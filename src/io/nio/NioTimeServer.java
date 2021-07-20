package io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class NioTimeServer {
    public static void main(String[] args) {
        // System.exit(1);
        int port = 10002;
        if (args != null && args.length > 0) {
            port = Integer.valueOf(args[0]);
        }
        // 启动一个线程来 开启一个 nio server服务
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer, "NIO-multiplexeerTimeServer-001").start();
    }
}
class MultiplexerTimeServer implements Runnable {
    private Selector selector;
    private ServerSocketChannel servChannel;
    private volatile boolean stop;
    public MultiplexerTimeServer(int port) {
        // 初始化 nio server配置
        try {
            // 初始化 选择器
            selector = Selector.open();
            // 打开 server端通道
            servChannel = ServerSocketChannel.open();
            // 设置为非阻塞
            servChannel.configureBlocking(false);
            // 为server端通道socekt绑定端口 、 请求的传入连接队列的最大长度为1024字节。
            servChannel.socket().bind(new InetSocketAddress(port), 1024);
            // 用给定的选择器注册此频道，返回选择键
            // 将server端通道注册到选择器上、 用于套接字接受操作的操作设置位
            SelectionKey register = servChannel.register(selector, SelectionKey.OP_ACCEPT);
            // The servChannel.register SelectionKey key is: class
            // sun.nio.ch.SelectionKeyImpltruefalse
            System.out.println("The servChannel.register SelectionKey key is: "
                    + register.getClass() + register.isValid() + register.isAcceptable());
            System.err.println("The time server is start in port ：" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public void stop() {
        this.stop = true;
    }
    @Override
    public void run() {
        while (!stop) {
            try {
                // 1秒 轮询 一次 选择器上的 通道
                selector.select(1000);
                // 获取该选择器上的 所有 可选择的 key
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                // 遍历key
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        // 可以用异步 线程池 处理该 key
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            // 处理新接入的请求消息
            if (key.isAcceptable()) {// 数据接入
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                // 将socket通道同样注册到选择器 并且告知 key的 操作为 op_read
                sc.register(selector, SelectionKey.OP_READ);
            }
            if (key.isReadable()) {// 数据可读
                // read the data
                SocketChannel sc = (SocketChannel) key.channel();
                // 字节缓冲区 允许 1024字节
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                // 判断 buffer区是否有东西可读
                if (readBytes > 0) {
                    // 翻转此缓冲区
                    // 将限制设置为当前位置，然后位置设置为零。如果标记已定义，则它是丢弃。
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    // 数据读取
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("The time server receive order : " + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
                            ? new Date(System.currentTimeMillis()).toString()
                            : "BAD ORDER";
                    doWrite(sc, currentTime);
                } else if(readBytes < 0) {
                    // 对端链路关闭
                    key.cancel();
                    sc.close();
                } else {
                    ; // 读到0字忽略
                }
            }
        }
    }
    private void doWrite(SocketChannel channel, String response) throws IOException {
        if (response != null && response.trim().length() > 0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writerBuffer = ByteBuffer.allocate(bytes.length);
            writerBuffer.put(bytes);
            writerBuffer.flip();
            channel.write(writerBuffer);
        }
    }
}
