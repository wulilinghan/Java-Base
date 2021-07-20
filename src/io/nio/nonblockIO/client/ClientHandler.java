package io.nio.nonblockIO.client;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.concurrent.*;

public class ClientHandler implements Runnable {
    private SelectionKey key;
//    private static ThreadPoolExecutor threadPoolExecutor =
//            new ThreadPoolExecutor(1, 1, 2,
//                    TimeUnit.SECONDS, new ArrayBlockingQueue<>(200), new ThreadPoolExecutor.CallerRunsPolicy());
    private static ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
    public ClientHandler(SelectionKey key) {
        this.key = key;
    }

    @Override
    public void run() {
        if(!key.isValid())
            return;
        ClientOperation operation = ClientOperation.get(key);
        if(operation == null)
            return;
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
