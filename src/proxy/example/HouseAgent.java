package proxy.example;

/**
 * Created By gao_e on 2020/3/16 10:27
 * 房屋中介
 */
public class HouseAgent implements HouseSeller {
    private HouseSeller houseSeller;
    public HouseAgent(HouseSeller houseSeller) {
        this.houseSeller = houseSeller;
    }
    @Override
    public void sellHouse() {
        this.doSomethingOnBefore();
        this.houseSeller.sellHouse();
        this.doSomethingOnAfter();
    }
    private void doSomethingOnBefore() {
        System.out.println("售前...");
    }
    private void doSomethingOnAfter() {
        System.out.println("售后...");
    }
}
