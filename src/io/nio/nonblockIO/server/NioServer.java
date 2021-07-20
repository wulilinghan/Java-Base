package io.nio.nonblockIO.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: GY.
 * @Description: NIO server 启动类
 * @Date:Created in 2019/11/22 0022.
 * @Modified By:
 */
public class NioServer {

    public static void main(String[] args) throws IOException {
        new NioServer().start();
    }

    public void start() throws IOException {
        // 开启多路复用器
        Selector selector = Selector.open();
        // 开启server端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 为server通道绑定监听的端口
        serverSocketChannel.bind(new InetSocketAddress(8000));
        // 设置通道为非阻塞，可用于读、写，或同时读写
        serverSocketChannel.configureBlocking(false);
        // 将server通道注册到多路复用器上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动成功！");
        while (true) {
            int readyChannels = selector.select();
            if (readyChannels == 0) {
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.readyOps() == 0)
                    continue;
                new ServerHandler(key).handle();
                iterator.remove();
            }
        }
    }
}