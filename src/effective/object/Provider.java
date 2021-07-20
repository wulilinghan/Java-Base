package effective.object;

/**
 * @author GY
 * 2018年9月26日
 * 说明:具体服务实例提供者
 */
public interface Provider {
    Service newInstance();
}
