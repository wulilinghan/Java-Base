package rpc.socket.bserver;

import rpc.socket.bserver.rpcServer.Service;
import rpc.socket.contract.ParamDTO;
import rpc.socket.contract.Result;
import rpc.socket.contract.SimpleServiceFacade;

/**
 * 接口服务实现类
 */
@Service
public class SimpleService implements SimpleServiceFacade {
    @Override
    public Result addNumOf(ParamDTO dto) {
        Result result = new Result();
        result.setSum(dto.getNum1() + dto.getNum2());
        System.out.println("service方法实现具体业务处理————" + dto.getNum1() + "+" + dto.getNum2() + "=" + result.getSum());
        return result;
    }
}
