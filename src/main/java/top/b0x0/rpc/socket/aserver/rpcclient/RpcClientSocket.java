package top.b0x0.rpc.socket.aserver.rpcclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.collections4.CollectionUtils;

import top.b0x0.rpc.socket.common.Request;
import top.b0x0.rpc.socket.common.Response;

/**
 * 用一个线程专门维护服务间数据通信
 */
public class RpcClientSocket implements Runnable {
    private ExecutorService socketThreadWrite = Executors.newSingleThreadExecutor();
    private ExecutorService socketThreadRead = Executors.newSingleThreadExecutor();
    private String host;
    private Integer port;

    public RpcClientSocket(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public void createConnect() {
        this.socketThreadWrite.execute(this);
    }

    @SuppressWarnings("resource")
    @Override
    public void run() {
        // 根据服务提供者信息发起连接请求
        Socket socket = null;
        try {
            socket = new Socket(this.host, this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConcurrentHashMap.KeySetView<String, Request> requestIds = ClientDataQueue.requestMap.keySet();
        // 循环监听请求队列数据
        while (true) {
            //循环检测请求数据
            synchronized (ClientDataQueue.requestMap) {
                if (CollectionUtils.isEmpty(requestIds)) {
                    try {
                        ClientDataQueue.requestMap.wait();
                        continue;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            for (String requestId : requestIds) {
                //TODO 单个请求，可以优化为批量请求
                Request request = ClientDataQueue.requestMap.get(requestId);
                OutputStream outputStream = null;
                ObjectOutputStream oos = null;
                try {
                    outputStream = socket.getOutputStream();
                    oos = new ObjectOutputStream(outputStream);
                    oos.writeObject(request);
                    oos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 从请求队列 移除 请求
                ClientDataQueue.requestMap.remove(requestId);

                // TODO 考虑用线程池操作，创建多个socker进行数据交互
                // TODO 进一步考虑用NIO做优化
                InputStream inputStream = null;
                ObjectInputStream ois = null;
                Response response = null;
                try {
                    inputStream = socket.getInputStream();
                    ois = new ObjectInputStream(inputStream);
                    response = (Response) ois.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                synchronized (requestId) {
                    ClientDataQueue.responseMap.put(response.getRequestId(), response);
                    requestId.notifyAll();
                }
                // TODO 用两个线程操作同一个socker分别进行写跟读存在问题
            }
        }
    }
}
