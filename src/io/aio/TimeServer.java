package io.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang.StringUtils;

public class TimeServer {
    public static void main(String[] args) {
        int port = 7777;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (Exception e) {
                // 略
            }
        }
        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        // 用主线程也可以
        new Thread(timeServer, "AIO-AsyncTimeServerHandler-001").start();
    }
}

class AsyncTimeServerHandler implements Runnable {
    private int port;
    CountDownLatch latch;
    // AIO服务端channel模型
    // 异步
    AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    public AsyncTimeServerHandler(int port) {
        this.port = port;
        try {
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            asynchronousServerSocketChannel.bind(new InetSocketAddress(this.port));
            System.out.println("The Time Server is start in port : " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        doAccept();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void doAccept() {
        // 添加接收请求事件回调处理机制
        asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());
    }
}

class AcceptCompletionHandler
        implements CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {

    @Override
    public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
        // 因为一个asynchronousServerSocketChannel可以接收成千上万个客户端，所以需要继续调用其accept方法
        attachment.asynchronousServerSocketChannel.accept(attachment, this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 添加读取数据事件回调处理机制
        result.read(buffer, buffer, new ReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
        exc.printStackTrace();
        attachment.latch.countDown();
    }

}

class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel channel;

    public ReadCompletionHandler(AsynchronousSocketChannel channel) {
        if (this.channel == null) {
            this.channel = channel;
        }
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        byte[] body = new byte[attachment.remaining()];
        attachment.get(body);
        try {
            String req = new String(body, "UTF-8");
            System.out.println("the time server receive order : " + req);
            String currentTime = "QUERY TIME ORDER".equals(req)
                    ? new Date(System.currentTimeMillis()).toString()
                    : "BAD ORDER";
            doWrite(currentTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doWrite(String resp) {
        if (StringUtils.isNotEmpty(resp)) {
            byte[] bytes = resp.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            // 添加写出数据事件回调处理机制
            channel.write(writeBuffer, writeBuffer, new WriteCompletionHandler(channel));
        }
    }
}

class WriteCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousSocketChannel channel;

    WriteCompletionHandler(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        if (buffer.hasRemaining()) {
            // 如果没有发送完成，继续发送
            channel.write(buffer, buffer, this);
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}