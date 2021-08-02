package juc.thread;

public class OrderReSort {
    int x = 0, y = 0;
    public void write() {
        for (int i = 1; i <= 2000000; i++) {
            x = i;
            y = i+i;
        }
    }
    public void read() {
        while (true) {
//            System.out.println(y);
//            System.out.println(x);
            if (y != 2 * x && y != 2 * (x-1))
                break;
        }
        System.out.println("出现了: y = "+y+",x = "+x);
    }
    public static void main(String[] args) {
        OrderReSort orderReSort = new OrderReSort();
        new Thread(new Write(orderReSort)).start();
        new Thread(new Read(orderReSort)).start();
    }
}
class Write implements Runnable {
    private OrderReSort orderReSort;
    public Write(OrderReSort orderReSort) {
        this.orderReSort = orderReSort;
    }
    @Override
    public void run() {
        orderReSort.write();
    }
}
class Read implements Runnable {
    private OrderReSort orderReSort;
    public Read(OrderReSort orderReSort) {
        this.orderReSort = orderReSort;
    }
    @Override
    public void run() {
        orderReSort.read();
    }
}
