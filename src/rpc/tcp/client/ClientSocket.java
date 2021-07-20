package rpc.tcp.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import rpc.tcp.Constant;
import rpc.tcp.Request;

public class ClientSocket {

    private static Socket socket;
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;

    public static void startSocket() throws UnknownHostException, IOException {
        socket = new Socket(Constant.IP, Constant.PORT);
        System.out.println("与服务端连接已建立");
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
        // 设置inputStream阻塞超时时间
        // socket.setSoTimeout(2000);
    }

    public static void close() throws IOException {
        if (ois != null)
            ois.close();
        if (oos != null)
            oos.close();
        if (socket != null)
            socket.close();
        System.out.println("client closed");
    }

    /**
     * @author GY
     * @date 2018年10月7日
     * @功能: 处理一次RPC远程方法调用
     */
    public static Object doARpc(Request request)
            throws ClassNotFoundException, IOException {
        oos.writeObject(request);
        oos.flush();
        System.out.println("数据已发送: " + request);
        // read()或readObject()方法会导致程序阻塞，直到inputStream收到对方发过来的报文消息，有内容可读，程序才会继续往下执行
        return ois.readObject();
    }

}
