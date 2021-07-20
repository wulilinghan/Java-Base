package io.nio.myniotest;

import rpc.socket.common.Request;
import rpc.socket.common.Response;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerOPTHandler implements Runnable {

    private SelectionKey key;
    private Selector selector;

    public ServerOPTHandler(SelectionKey key) {
        this.key = key;
        this.selector = key.selector();
    }

    private static ThreadPoolExecutor workThreadGroup =
            new ThreadPoolExecutor(3, 10, 3,
                    TimeUnit.SECONDS, new ArrayBlockingQueue<>(200), new ThreadPoolExecutor.DiscardOldestPolicy());

    @Override
    public void run() {
        System.out.println(ServerDataQueue.requestMap.size());
        System.out.println(ServerDataQueue.responseMap.size());
        if (key.isValid()) {
            // 处理新接入的请求消息
            if (key.isAcceptable()) {// 数据接入
                accept();
            }
            if (key.isReadable()) {// 数据可读
                read();
            }
            if (key.isWritable()) { // 数据可写
                write();
            }
        }
    }

    public void accept() {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel sc = null;
        System.out.println("server accepting...");
        try {
            sc = ssc.accept();
            if(sc == null)
                return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            sc.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 将socket通道同样注册到选择器 并且告知 key的 操作为 op_read
        try {
            sc.register(selector, SelectionKey.OP_READ);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        // read the data
        SocketChannel sc = (SocketChannel) key.channel();
        // 字节缓冲区 允许 1024字节
        ByteBuffer readBuffer = ByteBuffer.allocate(4);
        int len = readBuffer.getInt();
        if (len == 0) {
            return;
        }
        ByteBuffer buff = ByteBuffer.allocate(len);
        int readBytes = 0;
        try {
            readBytes = sc.read(buff);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 判断 buffer区是否有东西可读
        if (readBytes > 0) {
            // 翻转此缓冲区
            // 将限制设置为当前位置，然后位置设置为零。如果标记已定义，则它是丢弃。
            buff.flip();
//            byte[] bytes = new byte[buff.remaining()];
            byte[] data = buff.array();
            key.interestOps(SelectionKey.OP_READ);

            //转成请求的对象
            Request request = (Request) NioObjectIOUtil.toObject(data);
            System.out.println("Get a request = " + request);
            ServerDataQueue.requestMap.put(request.getRequestId(), request);
            try {
                sc.register(selector, SelectionKey.OP_WRITE);
            } catch (ClosedChannelException e) {
                e.printStackTrace();
            }
        } else if (readBytes < 0) {
            // 对端链路关闭
            key.cancel();
            try {
                sc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handle() {
        workThreadGroup.execute(this);
    }
    public void write() {
        synchronized ("") {
            ConcurrentHashMap.KeySetView<String, Response> keys = ServerDataQueue.responseMap.keySet();
            for (String key : keys) {
                Response response = ServerDataQueue.responseMap.get(key);
                ByteBuffer byteBuffer = NioObjectIOUtil.toByteBuffer(response);
                SocketChannel sc = (SocketChannel) this.key.channel();
                try {
                    sc.write(byteBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
