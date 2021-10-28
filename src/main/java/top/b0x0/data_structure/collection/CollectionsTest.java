package top.b0x0.data_structure.collection;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Collections工具类
 * 1. Collections.sort 排序
 * 2. Collections.binarySearch 二分查找
 * 3. Collections.shuffle 洗牌算法
 * 4. Collections.rotate 旋转算法
 */
public class CollectionsTest {
    public static void main(String[] args) {
        rotate();
    }

    /**
     * 数组旋转 [1,2,3,4,5] -> [4,5,1,2,3]
     */
    public static void rotate() {
        ArrayList<Integer> ls = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
        }};
        // [1,2,3,4,5] -> [4,5,1,2,3]
        Collections.rotate(ls, 2);
        System.out.println("ls = " + ls);
    }
}
