package rpc.socket.bserver.rpcServer;

import org.apache.commons.collections4.CollectionUtils;
import rpc.socket.common.Request;
import rpc.socket.common.Response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author GY
 * @since 2019年11月18日
 * @说明: 服务提供方工作组
 */
public class ServerWork {

    /**
     * @author GY
     * @since 2019年11月18日
     * @说明: 开始工作
     */
    public static void startWork() {
        ConcurrentHashMap.KeySetView<String, Request> requestIds = ServerDataQueue.requestMap.keySet();
        // 轮询就位请求
        synchronized (ServerDataQueue.requestMap) {
            while (true) {
                if (CollectionUtils.isEmpty(requestIds)) {
                    try {
                        ServerDataQueue.requestMap.wait();
                        continue;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (String requestId : requestIds) {
                    // 使用工作组线程执行请求任务
                    new HandlerTask(requestId).doTask();
                    // 移除请求，避免被重复处理
                    ServerDataQueue.requestMap.remove(requestId);
                }
            }
        }
    }
}

/**
 * @author GY
 * @since 2019年11月18日
 * @说明: 
 */
class HandlerTask implements Runnable {
    /**
     * 线程池处理远程调用任务
     */
    private static ThreadPoolExecutor workThreadPool = new ThreadPoolExecutor(3, 6, 3,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(200),
            new ThreadPoolExecutor.CallerRunsPolicy());
    /**
     * 请求id
     */
    private String requestId;
    /**
     * 请求协议对象，包含请求参数...
     */
    private Request request;

    public HandlerTask(String requestId) {
        this.requestId = requestId;
        this.request = ServerDataQueue.requestMap.get(requestId);
    }

    /**
     * 处理request
     */
    @Override
    public void run() {
        // 从服务注册表中获取用于处理该请求的对象实体
        Object service = ServiceLoader.registedServiceMap.get(request.getServiceId());
        try {
            // 处理任务
            Object result = this.handle(service);
            // 封装响应
            Response response = new Response();
            response.setRequestId(requestId);
            response.setData(result);
            response.setRequestTimeStamp(request.getRequestTimeStamp());
            // 添加进入 响应队列 等待被写出
            synchronized (requestId) {
                ServerDataQueue.responseMap.put(requestId, response);
                requestId.notifyAll();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射执行调用 处理远程请求
     */
    private Object handle(Object service)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String interfaceName = this.request.getItfcName();
        Class<?> serviceInterfaceClass = Class.forName(interfaceName);
        String methodName = this.request.getMethodName();
        Object[] data = this.request.getArgs();
        Class<?>[] parameterTypes = this.request.getMethodParameTypes();
        Method method = serviceInterfaceClass.getMethod(methodName, parameterTypes);
        Object result = method.invoke(service, data);
        return result;
    }

    /**
     * @author GY
     * @since 2019年11月18日
     * @说明: 执行多线程处理请求任务
     */
    public void doTask() {
        workThreadPool.execute(this);
    }
}
