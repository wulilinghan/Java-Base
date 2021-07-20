package io.nio.myniotest;

public class NioServerStarter {
    public static final int serverPort = 9000;
    public static void main(String[] args) {
        // 启动业务处理线程组处理请求业务
        new ServerBusinessHandler().start();
        // 主线程打开NIO,并作为轮询线程
        new NioServer(serverPort).start();
    }
}
