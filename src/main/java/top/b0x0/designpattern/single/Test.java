package top.b0x0.designpattern.single;

/**
 * Created By gao_e on 2020/5/5 16:36
 */
public class Test {
    /*public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("top.b0x0.designpattern.single.Service",true,Thread.currentThread().getContextClassLoader());
        Service.getInstance3();

        EService e = EService.E_SERVICE_SINGLE;

    }*/


    public static void main(String[] args) {
//        getInstance();
        try {
            Class<?> aClass = Class.forName("top.b0x0.designpattern.single.SingleBean", true,
                    Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        SingleBean.getInstance();
    }

}
