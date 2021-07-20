package io.socket.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {

    public static Integer port = 10000;
    private static ThreadPoolExecutor tPool = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(), 10, 2, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(200), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        if (args != null && args.length > 0) {
            port = Integer.valueOf(args[0]);
        }
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            Socket socket = null;
            while (true) {
                socket = server.accept();
                tPool.execute(new ServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
