package top.b0x0.math;
// 计算兔子数量，第三个月开始生兔子，以后每个月生一对兔子，问一对兔子n个月后会一共有多少对兔子
public class RabbitCount {

	public static void main(String[] args) {
		System.out.println(f(9));
	}

	public static int f(int month) {
		if (month < 3) {
			return 1;
		}
		// 初始值 为 第三个月 时的 数据
		int m0 = 1, m1 = 0, m2 = 0, m3 = 1, sum = 0;
		for (int i = 4; i <= month; i++) {
			m3 += m2;
			m2 = m1;
			m1 = m0;
			m0 = m3;
		}
		sum = m3 + m2 + m1 + m0;
		System.out.println("刚出生:" + m0);
		System.out.println("1个月了:" + m1);
		System.out.println("2个月了:" + m2);
		System.out.println("已经生育了:" + m3);
		return sum;
	}

}
