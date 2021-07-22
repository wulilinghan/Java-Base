package JUC;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExchangerLean {

    static ThreadPoolExecutor threadPoolExecutorRequest =
            new ThreadPoolExecutor(3,5,1, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(100),
                    new ThreadPoolExecutor.CallerRunsPolicy());
    static ThreadPoolExecutor threadPoolExecutorResponse =
            new ThreadPoolExecutor(3,5,1, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(100),
                    new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        Exchanger<AllData> exchanger = new Exchanger<>();
        threadPoolExecutorRequest.execute(
                new MyTaskExchangerRequest(exchanger,
                        new AllData(new Param(1,2),null,1)
                )
        );
        threadPoolExecutorResponse.execute(
                new MyTaskExchangerResponse(exchanger,
                        new AllData(null,new Result(3),1)
                )
        );
    }

}
class MyTaskExchangerRequest implements Runnable {
    private Exchanger<AllData> exchanger;
    private AllData allData;
    public MyTaskExchangerRequest(Exchanger<AllData> exchanger, AllData allData) {
        this.exchanger = exchanger;
        this.allData = allData;
    }
    @Override
    public void run() {
        AllData exchange = null;
        try {
            exchange = exchanger.exchange(allData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("This is MyTaskExchangerRequest exchanged = " + exchange);
    }
}
class MyTaskExchangerResponse implements Runnable {
    private Exchanger<AllData> exchanger;
    private AllData allData;
    public MyTaskExchangerResponse(Exchanger<AllData> exchanger, AllData allData) {
        this.exchanger = exchanger;
        this.allData = allData;
    }
    @Override
    public void run() {
        try {
            AllData exchange = exchanger.exchange(allData);
            System.out.println("This is MyTaskExchangerResponse exchanged = " + exchange);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class AllData {
    private Param param;
    private Result result;
    private Integer requestId;
    public Param getParam() {
        return param;
    }
    public void setParam(Param param) {
        this.param = param;
    }
    public Result getResult() {
        return result;
    }
    public void setResult(Result result) {
        this.result = result;
    }
    public Integer getRequestId() {
        return requestId;
    }
    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }
    public AllData(Param param, Result result, Integer requestId) {
        this.param = param;
        this.result = result;
        this.requestId = requestId;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("param", param)
                .append("result", result)
                .append("requestId", requestId)
                .toString();
    }
}
class Param {
    private Integer num1;
    private Integer num2;
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
    public Param(Integer num1, Integer num2) {
        this.num1 = num1;
        this.num2 = num2;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("num1", num1)
                .append("num2", num2)
                .toString();
    }
}
class Result {
    private Integer sum;
    public Result(Integer sum) {
        this.sum = sum;
    }
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

