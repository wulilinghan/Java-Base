package io.netty.test;

/**
 * Created By gao_e on 2020/3/10 15:43
 */
public class Client {
    public static void main(String[] args) {
        try {
            new ServerClient().connect(8500,"127.0.0.1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
