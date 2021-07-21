package io.nio.nonblockIO.client;

import io.nio.myniotest.NioObjectIOUtil;
import io.nio.nonblockIO.server.ServerData;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * nio 具体 io 操作
 *
 * @author: GY.
 */
public enum ClientOperation {
    CONNECTABLE(SelectionKey.OP_CONNECT) {
        @Override
        public void opt(SelectionKey key) throws IOException {
            SocketChannel sc = (SocketChannel) key.channel();
            Selector selector = key.selector();
            if (sc.finishConnect()) {
                sc.register(selector, SelectionKey.OP_READ);
                sc.register(selector, SelectionKey.OP_WRITE);
                System.out.println("连接服务端成功！");
            } else {
                System.exit(1);
            }
        }
    },
    WRITABLE(SelectionKey.OP_WRITE) {
        @Override
        public void opt(SelectionKey key) throws IOException {
            writeThread.execute(new Runnable() {
                @Override
                public void run() {
                    List<Object> list = ClientData.synData;
                    Iterator<Object> iterator = list.iterator();
                    while (true) {
                        if (!iterator.hasNext()) {
                            synchronized (ClientData.clientDataKey) {
                                try {
                                    // 服务端无数据 则写出线程休眠，等待有数据被唤醒
                                    ClientData.clientDataKey.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        while (iterator.hasNext()) {
                            Object request = iterator.next();
                            // 写出对象
                            ByteBuffer byteBuffer = NioObjectIOUtil.toByteBuffer(request);
                            SocketChannel sc = (SocketChannel) key.channel();
                            try {
                                sc.write(byteBuffer);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            iterator.remove();
                            System.out.println("请求写出成功：" + request);
                        }
                    }
                }
            });
        }
    },
    READABLE(SelectionKey.OP_READ) {
        @Override
        public void opt(SelectionKey key) throws IOException {
            System.out.println("可读已经就位");
            // 获取当前key的通道
            SocketChannel sc = (SocketChannel) key.channel();
            // 字节缓冲区 允许 1024字节
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            int readBytes = sc.read(readBuffer);
            try {
                readBytes = sc.read(readBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 判断 buffer区是否有东西可读
            if (readBytes > 0) {
                // 翻转此缓冲区
                // 将限制设置为当前位置，然后位置设置为零。如果标记已定义，则它是丢弃。
                readBuffer.flip();
                byte[] data = new byte[readBuffer.remaining()];
                key.interestOps(SelectionKey.OP_READ);
                //获取响应的对象
                Object response = NioObjectIOUtil.toObject(data);
                System.out.println("Get a response = " + response);
                // TODO 处理 response
            } else if (readBytes < 0) {
                // 对端链路关闭
                key.cancel();
                try {
                    sc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else ;
        }
    };
    private static ExecutorService writeThread = Executors.newSingleThreadExecutor();
    private Integer keyInt;

    private ClientOperation(Integer keyInt) {
        this.keyInt = keyInt;
    }

    public Integer keyInt() {
        return keyInt;
    }

    public static ClientOperation get(SelectionKey key) {
        if (key.isWritable())
            return WRITABLE;
        if(key.isReadable())
            return READABLE;
        if(key.isConnectable())
            return CONNECTABLE;
        else return null;
//        ClientOperation[] opts = ClientOperation.values();
//        for (ClientOperation opt : opts) {
//            Integer keyInt = opt.keyInt();
//            if ((key.readyOps() & keyInt) != 0) {
//                return opt;
//            }
//        }
//        return null;
    }

    public abstract void opt(SelectionKey key) throws IOException;

}
