package basic;

import java.util.Random;

/**
 * Created By gao_e on 2020/5/29 18:14
 */
public class ForkJoin5000w {

    static int count = 50000000;
    static int[] nums = new int[50000000];
    static Random random = new Random();

    static {
        for (int i = 0; i < 50000000; i++) {
            int i1 = random.nextInt(50000000);
            nums[i] = i1;
        }
    }
     /**
      * @author ManJiis
      * @Description // TODO
      * @since 2020/6/5 10:25
      * @Param
      * @return
      **/
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        long sum = 0;
        for (int i : nums) {
            sum += i;
        }
        System.out.println(sum);
        System.out.println(System.currentTimeMillis() - l);
    }

}
