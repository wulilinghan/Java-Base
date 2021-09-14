package top.b0x0.math;

/**
 * @author ManJiis
 * @since 2018年10月5日
 * @说明:递归台阶走法计算
 */
public class CountStage {

    public static void main(String[] args) {
        try {
            System.out.println(f(10));// 稍多点台阶就无法计算出来了
            System.out.println(f2(10));// 秒出结果
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author ManJiis
     * @since 2018年10月5日
     * @功能:10层台阶，可以跨一层，可以跨两层，走完10层共有多少种走法
     * @说明:要么从第九层跨入第十层、要么从第八层跨入第十层
     */
    static int f(int n) throws IllegalAccessException {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        if (n < 1 || n > 40)
            throw new IllegalArgumentException("参数不合法：n=" + n);
        return f(n - 1) + f(n - 2);
    }

    /**
     * @author ManJiis
     * @since 2018年10月5日
     * @功能:算法优化，使用迭代代替递归
     * @说明:
     */
    static long f2(int n) {
        if (n < 1)
            throw new IllegalArgumentException("参数不合法：n=" + n);
        if (n <= 2)
            return n;
        long n_2 = 1, n_1 = 2;
        long n_ = 0;
        // 第三层的走法等于第二层的走法+第一层的走法
        // 第四层的走法等于第三层的走法+第二层的走法
        // ...逐层迭代
        for (int i = 3; i <= n; i++) {
            n_ = n_2 + n_1;
            n_2 = n_1;
            n_1 = n_;
        }
        return n_;
    }

}
