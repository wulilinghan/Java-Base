package top.b0x0.rpc.socket.contract;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 相应 数据 实体
 */
public class Result  implements Serializable {

    private Integer sum;

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("sum", sum)
                .toString();
    }
}
