package top.b0x0.designpattern.factory;

/**
 * @author ManJiis
 * @since 2020/6/26 15:48
 * @Description:
 **/
public class ComputerFactory {

    // "dev","business"
    public static Computer getAComputerBy(String computerType) {
        if("dev".equals(computerType)) {
            return new DevComputer();
        }
        if("business".equals(computerType)){
            return new BusinessComputer();
        }
        throw new IllegalArgumentException("参数不合法");
    }

//    public static Computer getADevComputer() {
//        return new Computer(4, 16);
//    }
//
//    public static Computer getABusinessComputer() {
//        return new Computer(2, 4);
//    }

}
