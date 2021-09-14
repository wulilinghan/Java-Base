package top.b0x0.rpc.tcp;

import java.io.Serializable;
import java.util.Arrays;

public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

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
    public Object[] getArgs() {
        return args;
    }
    public void setArgs(Object[] args) {
        this.args = args;
    }
    public Class<?>[] getMethodParameTypes() {
        return methodParameTypes;
    }
    public void setMethodParameTypes(Class<?>[] methodParameTypes) {
        this.methodParameTypes = methodParameTypes;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Request [itfcName=");
        builder.append(itfcName);
        builder.append(", methodName=");
        builder.append(methodName);
        builder.append(", methodParameTypes=");
        builder.append(Arrays.toString(methodParameTypes));
        builder.append(", args=");
        builder.append(Arrays.toString(args));
        builder.append("]");
        return builder.toString();
    }
    public Request(String itfcName, String methodName, Object[] args,
            Class<?>[] methodParameTypes) {
        super();
        this.itfcName = itfcName;
        this.methodName = methodName;
        this.args = args;
        this.methodParameTypes = methodParameTypes;
    }
    public Request() {
        super();
    }

}
