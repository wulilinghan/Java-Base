package basic.enumtest;
/**
 * @Author G_Y
 * @Date 2020/6/10 9:45
 * @Description: 提示信息封装
 **/
public enum EResultCode {

    /**
     * "操作成功",2000,Boolean.TRUE
     */

    SUCCESSED("操作成功",2000,Boolean.TRUE){
        @Override
        public void f() {

        }
    },
    ILLEGAL_PARAMETER("非法参数",5001,Boolean.FALSE){
        @Override
        public void f() {

        }
    };
    // 继续添加...

    private String message;
    private Integer code;
    private Boolean success;
    private EResultCode(String message,Integer code,Boolean success){
        this.code = code;
        this.message = message;
        this.success = success;
    }
    public void f(){}


    public String getMessage() {
        return message;
    }
    public Integer getCode() {
        return code;
    }
    public Boolean getSuccess() {
        return success;
    }
}
