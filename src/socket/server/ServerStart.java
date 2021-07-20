package socket.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerStart {

    private static ThreadPoolExecutor tPool = new ThreadPoolExecutor(2, 4, 2, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(500), new ThreadPoolExecutor.CallerRunsPolicy());
    static {
        tPool.allowCoreThreadTimeOut(true);
    }

    public static void main(String[] args) throws IOException {
        int port = 10000;
        if (args != null & args.length > 0) {
            port = Integer.valueOf(args[0]);
        }
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            Socket socket = null;
            System.out.println("server started");
            while (true) {
                socket = server.accept();
                System.out.println("一个客户端连接了");
                tPool.execute(new ServerHandler(socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(server != null) {
                server.close();
                System.out.println("server closed");
            }
        }
    }

}
