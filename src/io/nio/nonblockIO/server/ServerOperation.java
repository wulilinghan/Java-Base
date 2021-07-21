package io.nio.nonblockIO.server;

import io.nio.myniotest.NioObjectIOUtil;

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
public enum ServerOperation {

    WRITABLE(SelectionKey.OP_WRITE) {
        @Override
        public void opt(SelectionKey key) throws IOException {
            System.out.println("可读已经就位");
            writeThread.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("开始准备写出响应");
                    List<Object> list = ServerData.synData;
                    Iterator<Object> iterator = list.iterator();
                    while (true) {
                        if (!iterator.hasNext()) {
                            synchronized (ServerData.serverDataKey) {
                                try {
                                    // 服务端无数据 则写出线程休眠，等待有数据被唤醒
                                    ServerData.serverDataKey.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        while (iterator.hasNext()) {
                            Object response = iterator.next();
                            // 写出对象
                            ByteBuffer byteBuffer = NioObjectIOUtil.toByteBuffer(response);
                            SocketChannel sc = (SocketChannel) key.channel();
                            try {
                                sc.write(byteBuffer);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            iterator.remove();
                            System.out.println("写出成功：" + response);
                        }
                    }
                }
            });
        }
    },
    ACCEPTABLE(SelectionKey.OP_ACCEPT) {
        @Override
        public void opt(SelectionKey key) throws IOException {
            System.out.println("连接已经就位");
            Selector selector = key.selector();
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(socketChannel == null) {
                return;
            }
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
//            socketChannel.register(selector, SelectionKey.OP_WRITE);
            System.out.println("一个客户端连接成功");
        }
    },
    READABLE(SelectionKey.OP_READ) {
        @Override
        public void opt(SelectionKey key) throws IOException {
            System.out.println("服务端存在可读数据");
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
                //获取请求的对象
                Object request = NioObjectIOUtil.toObject(data);
                System.out.println("Get a request = " + request);
                // TODO 处理 request
                ServerData.synData.add(request);
                Selector selector = key.selector();
                sc.register(selector, SelectionKey.OP_WRITE);
                // TODO 如果是工作线程处理，则需要在工作线程代码中执行notify
                synchronized (ServerData.serverDataKey) {
                    ServerData.serverDataKey.notifyAll();
                }
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

    private ServerOperation(Integer keyInt) {
        this.keyInt = keyInt;
    }

    public Integer keyInt() {
        return keyInt;
    }

    public static ServerOperation get(SelectionKey key) {
        if (key.isWritable())
            return WRITABLE;
        if(key.isReadable())
            return READABLE;
        if(key.isAcceptable())
            return ACCEPTABLE;
        else return null;
//        ServerOperation[] opts = ServerOperation.values();
//        for (ServerOperation opt : opts) {
//            Integer keyInt = opt.keyInt();
//            if ((key.readyOps() & keyInt) != 0) {
//                return opt;
//            }
//        }
//        return null;
    }

    public abstract void opt(SelectionKey key) throws IOException;

}
