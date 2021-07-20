package io.nio.myniotest;

import rpc.socket.common.Response;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: GY.
 * @Description: TODO()
 * @Date:Created in 2019/11/21 0021.
 * @Modified By:
 */
public class ClientOPTHandler implements Runnable {

    private SelectionKey key;
    private Selector selector;

    public ClientOPTHandler(SelectionKey key) {
        this.key = key;
        this.selector = key.selector();
    }

    private static ThreadPoolExecutor workThreadGroup =
            new ThreadPoolExecutor(3, 4, 3,
                    TimeUnit.SECONDS, new ArrayBlockingQueue<>(200), new ThreadPoolExecutor.DiscardOldestPolicy());

    private static volatile boolean isConeected = false;
    @Override
    public void run() {
        if (key.isValid()) {
            if (key.isConnectable()) {
                if(!isConeected){
                    connect();
                    isConeected = true;
                }
            }
            if (key.isReadable()) {
                read();
            }
//            if(key.isWritable()){
//                SocketChannel sc = (SocketChannel) key.channel();
//                doWrite(sc);
//            }
        }
    }

    private void doWrite(SocketChannel sc) {
        new ClientWriteHandler(sc).start();
    }
    public void connect() {
        System.out.println("client connected");
        SocketChannel sc = (SocketChannel) key.channel();
        try {
            if (sc.finishConnect()) {
                sc.register(selector, SelectionKey.OP_READ);
                doWrite(sc);
            } else {
                System.exit(1);
            }
        } catch (IOException e) {
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
            Response response = (Response) NioObjectIOUtil.toObject(data);
            System.out.println("Get a Response = " + response);
            ClientDataQueue.responseMap.put(response.getRequestId(), response);
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
    public void star() {
        workThreadGroup.execute(this);
    }
}
