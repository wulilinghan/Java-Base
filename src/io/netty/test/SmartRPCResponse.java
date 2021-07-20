package io.netty.test;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created By gao_e on 2020/3/8 15:10
 */
public class SmartRPCResponse {

    private long requestTimeStamp;
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 调用信息
     */
    private String msg;
    /**
     * 请求id
     */
    private String requestId;
    /**
     * 请求结果
     */
    private Object data;

    public long getRequestTimeStamp() {
        return requestTimeStamp;
    }

    public void setRequestTimeStamp(long requestTimeStamp) {
        this.requestTimeStamp = requestTimeStamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("requestTimeStamp", requestTimeStamp)
                .append("success", success)
                .append("code", code)
                .append("msg", msg)
                .append("requestId", requestId)
                .append("data", data)
                .toString();
    }

}
