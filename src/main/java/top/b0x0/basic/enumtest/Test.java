package top.b0x0.basic.enumtest;

/**
 * @author ManJiis
 * @since 2020/6/10 9:47
 * @Description:
 **/
public class Test {
    public static void main(String[] args) {
        new Test().testServicePro(RealEnum.A_BEAN);
    }
    public Result testService(Integer userId) {

        // 查询数据库
        boolean isNotExist = false;

        if(isNotExist) {
            return new Result(null,EResultCode.ILLEGAL_PARAMETER);
        }

        return new Result(null,EResultCode.SUCCESSED);
    }

    public void testServicePro(RealEnum realEnum) {
        // 执行一个动作
        realEnum.f();
        // 访问数据库 string
        String message = realEnum.getMessage();
        System.out.println(message);
    }

}
