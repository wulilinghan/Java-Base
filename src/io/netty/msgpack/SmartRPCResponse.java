package io.netty.msgpack;

import org.msgpack.annotation.Message;

import java.io.Serializable;

/**
 * Created By gao_e on 2020/3/8 15:10
 */
@Message
public class SmartRPCResponse implements Serializable {

    private Integer id;
    private String data;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SmartRPCResponse{");
        sb.append("id=").append(id);
        sb.append(", data='").append(data).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
