package top.b0x0.basic.enumtest;

/**
 * @author ManJiis
 * @since 2020/6/10 10:47
 * @Description: 枚举类
 **/
public enum RealEnum {
    A_BEAN("A_BEAN"){
        @Override
        public void f() {
            System.out.println("第一种行为");
        }
    },
    B_BEAN("B_BEAN"){
        @Override
        public void f() {
            System.out.println("第二种行为");
        }
    },
    C_BEAN("C_BEAN"){
        @Override
        public void f() {
            System.out.println("第三种行为");
        }
    };
    public abstract void f();
    private String message;
    private RealEnum(String message){
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
