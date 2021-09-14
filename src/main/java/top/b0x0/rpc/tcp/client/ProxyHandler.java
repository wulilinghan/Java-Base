package top.b0x0.rpc.tcp.client;

import top.b0x0.rpc.tcp.Request;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.*;


public class ProxyHandler implements InvocationHandler {

    private ProxyHandler() {
    }

    private static final ProxyHandler INVOCATION_HANDLER = new ProxyHandler();

    private static final ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1, 2, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(20), new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        String interfaceName = method.getDeclaringClass().getName().toString();
        String methodName = method.getName();
        System.out.println("interfaceName.methodName = " + interfaceName + "."
                + methodName);
        Request request = new Request(interfaceName, methodName, args,
                method.getParameterTypes());
        // I/O 处理写出请求对象
        Future<Object> future = pool.submit(new MyRpcDo(request));
        Object result = future.get();
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getProxyInstance(Class<T> cls) {
        if (!cls.isInterface())
            throw new IllegalArgumentException("代理类型非接口类型 top.b0x0.cls = " + cls);
        Class<?>[] Interfaces = new Class[]{cls};
        T proxyInstance = (T) Proxy.newProxyInstance(cls.getClassLoader(),
                Interfaces, INVOCATION_HANDLER);
        System.out.println("代理对象创建成功");
        return proxyInstance;
    }

}

class MyRpcDo implements Callable<Object> {
    private Request request;
    public MyRpcDo(Request request){
        this.request = request;
    }
    @Override
    public Object call() throws Exception {
        Object result = ClientSocket.doARpc(request);
        return result;
    }
}