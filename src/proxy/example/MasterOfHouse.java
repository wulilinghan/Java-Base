package proxy.example;

/**
 * Created By gao_e on 2020/3/16 10:21
 * 房主
 */
public class MasterOfHouse implements HouseSeller {
    private Integer howMuchW;
    public MasterOfHouse(Integer howMuchW) {
        this.howMuchW = howMuchW;
    }
    @Override
    public void sellHouse() {
        System.out.println("一口价"+this.howMuchW+"万");
        System.out.println("收钱");
        System.out.println("过户");
    }
}
