package top.b0x0.rpc.socket.aserver.rpcclient;

import top.b0x0.rpc.socket.common.Request;
import top.b0x0.rpc.socket.common.Response;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.LocalDateTime;
import java.util.UUID;

public class RpcClientInvocationHandler implements InvocationHandler {
    private RpcClientInvocationHandler() {
    }

    private static final RpcClientInvocationHandler INVOCATION_HANDLER = new RpcClientInvocationHandler();

    /**
     * @param proxy
     * @param method
     * @param args
     * @return 执行结果
     * 这里基于JDK动态代理，并且没有被代理对象
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        // 获取接口全名
        String interfaceName = method.getDeclaringClass().getName();
        // 方法名称
        String methodName = method.getName();
//        System.out.println("interfaceName.methodName = " + interfaceName + "."
//                + methodName);
        // 获取方法参数类型集合
        Class<?>[] parameterTypes = method.getParameterTypes();
        // 从服务注册信息中获取，接口对应的服务提供者service的serviceId
        HostAndPortAndServiceId hostAndPortAndServiceId = ServerRegistry.serverMap.get(interfaceName);
        if(hostAndPortAndServiceId == null) {
            throw new RuntimeException("无可用服务");
        }
        //请求id 唯一区分
        String uuid = UUID.randomUUID().toString();
        // 封装Request对象，把数据放入请求队列
        synchronized (ClientDataQueue.requestMap) {
            ClientDataQueue.requestMap.put(uuid, getRequestBy(uuid, interfaceName, methodName, parameterTypes, args, hostAndPortAndServiceId.getServiceId()));
            ClientDataQueue.requestMap.notifyAll();
        }
        // 循环获取响应队列数据
        Response response;
        int second = LocalDateTime.now().getSecond();
//        System.out.println("当前时间秒数=" + second);
        // 当前线程同步等待
        synchronized (uuid) {
            while ((response = ClientDataQueue.responseMap.get(uuid)) == null) {
                try {
                    uuid.wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (LocalDateTime.now().getSecond() - second > 5) {
                    // TODO 响应队列数据要定时清理，根据请求时间判断，略...
                    throw new RuntimeException("请求超时了！");
                }
            }
        }
        // 从响应队列将响应数据移除
        ClientDataQueue.responseMap.remove(uuid);
        Object data = response.getData();
        // 返回结果
        return data;
    }

    /**
     * 封装请求对象
     * @param requestId
     * @param itfcName
     * @param methodName
     * @param methodParameTypes
     * @param args
     * @param serviceId
     * @return Request
     */
    public Request getRequestBy(String requestId, String itfcName, String methodName, Class<?>[] methodParameTypes, Object[] args, Integer serviceId) {
        Request request = new Request(requestId, itfcName, methodName, methodParameTypes, args, serviceId);
        return request;
    }

    /**
     * @param cls
     * @param <T>
     * @return
     * 根据JDK动态代理，获取代理对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getProxyInstance(Class<T> cls) {
        Class<?>[] interfaces = new Class[]{cls};
        T proxyInstance = (T) Proxy.newProxyInstance(cls.getClassLoader(),
                interfaces, INVOCATION_HANDLER);
        System.out.println("代理对象创建成功");
        return proxyInstance;
    }
}
