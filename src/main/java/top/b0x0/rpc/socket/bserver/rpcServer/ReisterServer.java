package top.b0x0.rpc.socket.bserver.rpcServer;

import top.b0x0.rpc.socket.bserver.ServerConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务注册器
 */
public class ReisterServer {

    /**
     * @author ManJiis
     * @since 2019年11月18日
     * @说明: 服务注册到文件
     */
    public static void regist() {
        ConcurrentHashMap<Integer, Object> registedServiceMap = ServiceLoader.registedServiceMap;
        ConcurrentHashMap.KeySetView<Integer, Object> integers = registedServiceMap.keySet();
        File file = new File("D:\\repository.txt");
        PrintWriter pw = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            pw = new PrintWriter(fileOutputStream, true);
            for (Integer serviceId : integers) {
                Object o = registedServiceMap.get(serviceId);
                Class<?>[] interfaces = o.getClass().getInterfaces();
                for (Class<?> cls : interfaces) {
                    String info = cls.getName();
                    pw.println(info + "," + ServerConfig.host + "," + ServerConfig.port + "," + serviceId);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(pw != null) {
                pw.close();
            }
        }
    }

}
