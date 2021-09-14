package top.b0x0.rpc.tcp.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

import top.b0x0.rpc.tcp.Request;

public class ServerHandler implements Runnable {

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        Request request = null;
        try {
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
            ois = new ObjectInputStream(inputStream);
            oos = new ObjectOutputStream(outputStream);
            while ((request = (Request) ois.readObject()) != null) {
                System.out.println("请求数据:" + request);
                Object result = null;
                result = handle(request);
                oos.writeObject(result);
                oos.flush();
                System.out.println("处理结果数据:" + result);
            }
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        } catch (SecurityException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (IllegalArgumentException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        } finally {
            System.out.println("close server top.b0x0.juc.thread top.b0x0.socket");
            try {
                outputStream.close();
                inputStream.close();
                ois.close();
                oos.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Object handle(Request request)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String interfaceName = request.getItfcName();
        Class<?> serviceInterfaceClass = Class.forName(interfaceName);
        String methodName = request.getMethodName();
        Object[] data = request.getArgs();
        // Method[] methos = Class.forName(interfaceName).getDeclaredMethods();
        Class<?>[] parameterTypes = request.getMethodParameTypes();
        Method method = serviceInterfaceClass.getMethod(methodName, parameterTypes);
        Object result = method.invoke(ProviderServiceSocket.getMap().get(interfaceName), data);
        return result;
    }

}
