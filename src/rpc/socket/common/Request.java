package rpc.socket.common;

import java.io.Serializable;

// 请求实体 —— 协议层
public class Request implements Serializable {
    public Request(String requestId, String itfcName, String methodName, Class<?>[] methodParameTypes, Object[] args, Integer serviceId) {
        this.requestId = requestId;
        this.itfcName = itfcName;
        this.methodName = methodName;
        this.methodParameTypes = methodParameTypes;
        this.args = args;
        this.serviceId = serviceId;
    }
    private long requestTimeStamp = System.currentTimeMillis();
    public Request() {
    }

    public long getRequestTimeStamp() {
        return requestTimeStamp;
    }

    /**
     * 目标serviceId——服务提供方具体实现的serviceId
     */
    private Integer serviceId;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * 请求id
     */
    private String requestId;

    /**
     * 接口名称(全名)
     */
    private String itfcName;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 方法参数类型(因为有重载)
     */
    private Class<?>[] methodParameTypes;

    /**
     * 方法执行需要的参数
     */
    private Object[] args;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getItfcName() {
        return itfcName;
    }

    public void setItfcName(String itfcName) {
        this.itfcName = itfcName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getMethodParameTypes() {
        return methodParameTypes;
    }

    public void setMethodParameTypes(Class<?>[] methodParameTypes) {
        this.methodParameTypes = methodParameTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
