package io.netty.msgpack;

import org.msgpack.annotation.Message;

import java.io.Serializable;

/**
 * Created By gao_e on 2020/3/8 15:09
 */
@Message
public class SmartRPCRequest implements Serializable {

    private Integer id;
    private Integer a;
    private Integer b;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SmartRPCRequest{");
        sb.append("id=").append(id);
        sb.append(", a=").append(a);
        sb.append(", b=").append(b);
        sb.append('}');
        return sb.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }
}
