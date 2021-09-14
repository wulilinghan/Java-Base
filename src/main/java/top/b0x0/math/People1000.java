package top.b0x0.math;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Created By gao_e on 2020/5/17 16:43
 */
public class People1000 {
    static People[] peoples = new People[100];

    static {
        for (int i = 0; i < 100; i++) {
            peoples[i] = new People(i + 1);
        }
    }

    /*public static void main(String[] args) {
        People p1 = null;
        People p2 = null;
//        boolean success = false;
       F1: for (int i = 0; i < peoples.length-1; i++) {
            Integer count = peoples[i].getCount();
            for (int j=i+1;j< peoples.length-1-i; j++){
                Integer count1 = peoples[j].getCount();
                if(count+count1==100){
                    p1 = peoples[i];
                    p2 = peoples[j];
                    break F1;
                }
            }
//            if(success){
//                break;
//            }
        }
        System.out.println(p1);
        System.out.println(p2);
    }*/

    public static void main(String[] args) {
        // 冒泡
//        for (int i = 0; i < peoples.length - 1; i++) {
//            for (int j = 0; j < peoples.length - 1 - i; j++) {
//                if (peoples[j + 1].getCount() < peoples[j].getCount()) {
//                    People temp = peoples[j + 1];
//                    peoples[j+1] = peoples[j];
//                    peoples[j] = temp;
//                }
//            }
//        }
        Arrays.sort(peoples, new PeopleComparator());
//        Arrays.parallelSort(peoples,new PeopleComparator());// java8新增的并行排序算法，基于fork/join框架。
        // 双指针算法
        int firstP = 0;
        int lastP = 99;
        People p1 = null;
        People p2 = null;
        while (true) {
            if(firstP == lastP){
                break;
            }
            if(peoples[firstP].getCount()+peoples[lastP].getCount() == 100){
                p1 = peoples[firstP];
                p2 = peoples[lastP];
                break;
            }
            if(peoples[firstP].getCount()+peoples[lastP].getCount() > 100){
                lastP--;
                continue;
            }
            if(peoples[firstP].getCount()+peoples[lastP].getCount() < 100){
                firstP++;
                continue;
            }
        }
        System.out.println(p1  +"--------" + p2);

    }


}
class PeopleComparator implements Comparator<People> {
    @Override
    public int compare(People o1, People o2) {
        int result = 0;
        Integer count1 = o1.getCount();
        Integer count2 = o2.getCount();
        if (count1 > count2) {
            result = 1;
        }
        if (count1 < count2) {
            result = -1;
        }
        return result;
    }
}

class People {
    private static Random random = new Random();
    private Integer id;
    private Integer count = random.nextInt(100);

    public People(Integer id) {
        this.id = id;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("People{");
        sb.append("random=").append(random);
        sb.append(", id=").append(id);
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }

}
