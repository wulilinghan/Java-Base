package rpc.socket.bserver.rpcServer;

import rpc.socket.common.Request;
import rpc.socket.common.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcServerSocket implements Runnable {
    public RpcServerSocket(Socket socket) {
        this.socket = socket;
    }

    private Socket socket;
    /**
     * 获取请求的线程
     * TODO 可以使用静态线程池做优化
     */
    private ExecutorService singleThreadRead = Executors.newSingleThreadExecutor();
    /**
     * 写出响应的线程
     */
    private ExecutorService singleThreadWrite = Executors.newSingleThreadExecutor();

    // TODO 待NIO优化 ！！！
    @Override
    public void run() {
        try {
            // 循环监听请求数据
            W1:
            while (true) {
                InputStream inputStream = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(inputStream);
                Request request = (Request) ois.readObject();
                String requestId = request.getRequestId();
                // 获取到请求数据，将其放入 请求数据队列，让工作组线程进行处理
                synchronized (ServerDataQueue.requestMap) {
                    ServerDataQueue.requestMap.put(requestId, request);
                    ServerDataQueue.requestMap.notifyAll();
                }
                // 写出处理结果
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                Response response = null;
                int second = LocalDateTime.now().getSecond();
//                System.out.println("当前时间秒数=" + second);
                // TODO 待优化
                // 循环监听处理结果
                while ((response = ServerDataQueue.responseMap.get(requestId)) == null) {
                    synchronized (requestId) {
                        try {
                            requestId.wait(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (LocalDateTime.now().getSecond() - second > 3) {
                        response = new Response();
                        response.setSuccess(false);
                        response.setRequestTimeStamp(request.getRequestTimeStamp());
                        response.setMsg("处理超时");
                        oos.writeObject(response);
                        oos.flush();
                        continue W1;
                    }
                }
                response = ServerDataQueue.responseMap.get(requestId);
                oos.writeObject(response);
                oos.flush();
                // TODO 用两个线程分别进行写跟读存在问题
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void socketOpt() {
        this.singleThreadRead.execute(this);
    }
}
