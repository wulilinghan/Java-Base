package top.b0x0.rpc.tcp.server;

import top.b0x0.rpc.tcp.Service;

public class ServiceImpl implements Service {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

}
