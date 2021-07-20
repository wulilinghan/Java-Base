package rpc.tcp.server;

import java.io.IOException;

public class ServerStart {

    static {
        try {
            // 加载类静态资源(main中有调用该类可省略)
            Class.forName("rpc.tcp.server.ProviderServiceSocket", true,
                    Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new ProviderServiceSocket().serverStart();
    }

}
