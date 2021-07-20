package io.nio.nonblockIO.server;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * nio 具体 就位key 处理
 * @Author: GY.
 */
public class ServerHandler implements Runnable {

    private static Map<Integer, ServerOperation> OPT_MAP = new HashMap();

//    private static ThreadPoolExecutor threadPoolExecutor =
//            new ThreadPoolExecutor(1, 1, 2,
//                    TimeUnit.SECONDS, new ArrayBlockingQueue<>(200), new ThreadPoolExecutor.CallerRunsPolicy());

    private static ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();

    private SelectionKey key;

    public ServerHandler(SelectionKey key) {
        this.key = key;
    }

    @Override
    public void run() {
        if(!key.isValid())
            return;
        ServerOperation operation = ServerOperation.get(key);
        if(operation == null)
            return;
//        key.isAcceptable();
        try {
            operation.opt(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handle() {
        threadPoolExecutor.execute(this);
    }
}
