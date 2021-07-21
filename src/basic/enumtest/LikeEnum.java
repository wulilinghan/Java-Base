package basic.enumtest;

/**
 * @author ManJiis
 * @since 2020/6/10 10:25
 * @Description: 模拟枚举类
 **/
public abstract class LikeEnum {
    public abstract void f();
    private String message;
    private LikeEnum(String message){
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public static final LikeEnum A_BEAN = new LikeEnum("A_BEAN"){
        @Override
        public void f() {
            System.out.println("第一种行为");
        }
    };
    public static final LikeEnum B_BEAN = new LikeEnum("B_BEAN"){
        @Override
        public void f() {
            System.out.println("第二种行为");
        }
    };
    public static final LikeEnum C_BEAN = new LikeEnum("C_BEAN"){
        @Override
        public void f() {
            System.out.println("第三种行为");
        }
    };
}
