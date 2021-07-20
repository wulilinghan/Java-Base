package proxy.example;

/**
 * Created By gao_e on 2020/3/16 10:34
 * 卖房、买房流程
 */
public class SellHouse {
    public static void main(String[] args) {
        System.out.println("一个房主将卖房事情委托给中介，中介针对这个房子会单独创建出一个中介(代理)对象");
        HouseSeller houseSeller = new HouseAgent(new MasterOfHouse(2000));
        System.out.println("中介将房子挂出来卖，买家来买房");
        houseSeller.sellHouse();
    }
}
