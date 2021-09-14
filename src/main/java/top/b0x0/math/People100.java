package top.b0x0.math;

import java.util.LinkedList;
import java.util.List;

public class People100 {
    public static void main(String[] args) {
        // System.out.println(System.currentTimeMillis());
        List<Integer> list = new LinkedList<>();
        // 初始化
        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }
        int index = 1;//当前正在报的这个数
        while (true) {
            if (index == 4) {
                index = 1;
            }
            if (list.size() == 1) {
                break;
            }
            if (index == 3) {
                // 报数 是 3
                list.remove(0);
            } else {
                // 报数 是 1、2
                list.add(list.get(0));
                list.remove(0);
            }
            index++;
        }
        // System.out.println(System.currentTimeMillis());
        System.out.println(list);
    }
}
