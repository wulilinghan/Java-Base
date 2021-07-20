package rpc.socket.contract;

import java.io.Serializable;

/**
 * 请求 数据 实体
 */
public class ParamDTO implements Serializable {
    private Integer num1;
    private Integer num2;

    public ParamDTO(Integer num1, Integer num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public ParamDTO() {
    }

    public Integer getNum1() {
        return num1;
    }

    public void setNum1(Integer num1) {
        this.num1 = num1;
    }

    public Integer getNum2() {
        return num2;
    }

    public void setNum2(Integer num2) {
        this.num2 = num2;
    }
}
