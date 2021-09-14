package top.b0x0.rpc.socket.aserver.rpcclient;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServerRegistry {
    // key——为接口全路径名，value——ip、端口、接口的实现ServiceId
    public static Map<String, HostAndPortAndServiceId> serverMap = new HashMap<>(256);
    // 存储具体服务列表  host,port
    public static Set<String> hostAndPort = new HashSet<>(64);
    static {
        // 加载注册中心数据到serverMap
        File file = new File("D:\\repository.txt");
        BufferedReader br = null;
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(new FileInputStream(file), "utf-8");
            br = new BufferedReader(isr);
            String s;
            while ((s = br.readLine()) != null) {
                // s 的模板:*****.****.接口名,host,port,serviceId
                String[] split = s.split(",");
                serverMap.put(split[0], new HostAndPortAndServiceId(split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3])));
                hostAndPort.add(split[1] + "," + split[2]);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class HostAndPortAndServiceId {
    /**
     * 主机地址
     */
    private String host;
    /**
     * 端口
     */
    private Integer port;
    /**
     *  具体服务的提供者的serviceID
     */
    private Integer serviceId;

    public HostAndPortAndServiceId(String host, Integer port, Integer serviceId) {
        this.host = host;
        this.port = port;
        this.serviceId = serviceId;
    }

    public HostAndPortAndServiceId() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}
