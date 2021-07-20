package math;

public class String2Number {
	public static void main(String[] args) {
		String s = "1234wfsdfsdf23()_&*(234785h \\n ufnjikedKL:JFL:>:\"?\":\" ";
		char[] charArray = s.toCharArray();
		StringBuilder n = new StringBuilder(s.length());
		for (char c : charArray) {
			int a = c;
			n.append(a);
		}
		String string = n.toString();
		// 如果可以转Long 太长就不能转
		// Long long1 = Long.valueOf(string);
		// System.out.println(long1);
		System.out.println(string);
	}
}
