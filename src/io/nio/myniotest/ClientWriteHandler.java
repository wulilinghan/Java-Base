package io.nio.myniotest;

import org.apache.commons.collections4.CollectionUtils;
import rpc.socket.common.Request;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: GY.
 * @Description:
 * @since:Created in 2019/11/21 0021.
 * @Modified By:
 */
public class ClientWriteHandler implements Runnable {
    private SocketChannel sc;

    public ClientWriteHandler(SocketChannel sc) {
        this.sc = sc;
    }

    private static ExecutorService clientWriteThread = Executors.newSingleThreadExecutor();

    @Override
    public void run() {
        ConcurrentHashMap.KeySetView<String, Request> strings = ClientDataQueue.requestMap.keySet();
        while (true) {
            if (CollectionUtils.isEmpty(strings))
                continue;
            for (String requestId : strings) {
                Request request = ClientDataQueue.requestMap.get(requestId);
                ByteBuffer byteBuffer = NioObjectIOUtil.toByteBuffer(request);
                try {
                    sc.write(byteBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ClientDataQueue.requestMap.remove(requestId);
            }
        }
    }

    public void start() {
        clientWriteThread.execute(this);
    }

}
