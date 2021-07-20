package rpc.tcp.server;

import rpc.tcp.Service;

public class ServiceImpl implements Service {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

}
