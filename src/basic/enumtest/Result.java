package basic.enumtest;
/**
 * @author ManJiis
 * @since 2020/6/10 9:45
 * @Description: 返回调用方的Result对象
 **/
public class Result {
    private String message;
    private Integer code;
    private Object data;
    private Boolean success;
    private static Result result = new Result(null, EResultCode.SUCCESSED);

    public Result(Object data, EResultCode eResultCode) {
        this.data = data;
        this.success = eResultCode.getSuccess();
        this.code = eResultCode.getCode();
        this.message = eResultCode.getMessage();
    }
    public static Result noDataSuccessResult() {
        return result;
    }
}
