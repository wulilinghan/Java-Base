package top.b0x0.basic.enumtest;

/**
 * Created By gao_e on 2020/4/2 18:45
 */
public class Controller {
    public static void main(String[] args) {
        new Controller().f();
    }
    public void f() {
        new OrderService().getOrderByStatus(EOrderStatus.BVVVVVVV);
    }
}
