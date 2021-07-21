package io.nio.myniotest;

import rpc.socket.common.Response;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: GY.
 * @Description: TODO()
 * @since:Created in 2019/11/21 0021.
 * @Modified By:
 */
public class NioClient {

    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;
    public NioClient(String host, int port) {
        this.host = host == null ? "127.0.0.1" : host;
        this.port = port;
        try {
            this.selector = Selector.open();
            this.socketChannel = SocketChannel.open();
            this.socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public void start(){
        try {
            // 连接一次
            doConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (!stop) {
            try {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    new ClientOPTHandler(key).star();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            ConcurrentHashMap<String,Response> responseMap = ClientDataQueue.responseMap;
        }
    }

    /**
     * @throws IOException
     * 发起连接
     */
    private void doConnect() throws IOException {
        boolean connect = socketChannel.connect(new InetSocketAddress(host, port));
        if (connect) {
            System.out.println("client connected");
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }
    private void doWrite(SocketChannel sc) throws IOException {
        new ClientWriteHandler(sc).start();
    }
}
