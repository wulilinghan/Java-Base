package io.netty.test;

/**
 * Created By gao_e on 2020/3/10 15:43
 */
public class Server {
    public static void main(String[] args) {
        new ServerStartHandler(8500).useInnerPrivateThreadPoolExecutor();
    }
}
