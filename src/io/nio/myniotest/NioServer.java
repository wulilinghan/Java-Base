package io.nio.myniotest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
    private Selector selector;
    private ServerSocketChannel servChannel;
    private volatile boolean stop;

    public NioServer(int port) {
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
            System.err.println("The NIO server is start in port ：" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        this.stop = true;
    }

    public void start() {
        while (!stop) {
            try {
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            System.out.println("尝试获取就绪的操作...");
            // 获取该选择器上的 所有 可选择的 key
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            // 遍历key
            Iterator<SelectionKey> it = selectionKeys.iterator();
            SelectionKey key = null;
            while (it.hasNext()) {
                key = it.next();
                it.remove();
                new ServerOPTHandler(key).handle();
            }
        }
    }
}
