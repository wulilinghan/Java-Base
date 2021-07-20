package basic.enumtest;

// 用枚举实现单例
public enum EOrderStatus {
    BVVVVVVV(3,"fffff"){
        @Override
        public void f() {
            //...
            System.out.println("BVVVVVVV");
        }
    },

    PLACE_ORDER(1,"已下单"){
        @Override
        public void f() {
            //...
            System.out.println("PLACE_ORDER");
        }
    },
    PAY_ORDER(2,"已支付"){
        @Override
        public void f() {
            System.out.println("PAY_ORDER");
        }
    }
    // ...
    ;
    public abstract void  f();

    private Integer status;
    private String desc;

    private EOrderStatus(Integer status,String desc){
        this.status = status;
        this.desc = desc;
    }

    public Integer status() {
        return status;
    }

    public String desc() {
        return desc;
    }

    public static EOrderStatus getBy(Integer status) {
        EOrderStatus[] values = EOrderStatus.values();
        for (EOrderStatus e: values) {
            if(e.status.equals(status)){
                return e;
            }
        }
        throw new IllegalArgumentException("....");
    }
}
