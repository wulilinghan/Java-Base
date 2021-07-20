package io.nio.nonblockIO.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioClient {

    public static void main(String[] args) throws IOException {
        new NioClient().start();
    }

    public void start() throws IOException {
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        this.doConnect(socketChannel, selector);
        while (true) {
            int readyChannels = selector.select();
            if (readyChannels == 0) {
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.readyOps() == 0) {
                    continue;
                }
                new ClientHandler(key).handle();
                iterator.remove();
            }
        }

    }

    public void doConnect(SocketChannel socketChannel, Selector selector) throws IOException {
        boolean connect = socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));
        if (connect) {
            System.out.println("尝试连接服务端成功！");
            socketChannel.register(selector, SelectionKey.OP_WRITE);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else {
            System.out.println("尝试连接失败，监听连接状态");
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }


}
