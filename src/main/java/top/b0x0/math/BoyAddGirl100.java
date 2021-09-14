package top.b0x0.math;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BoyAddGirl100 {

	public static void main(String[] args) {
		Random random = new Random();
		int[] arr = new int[200];
		for (int i = 0; i <= 199; i++) {
			int c;
			c = random.nextInt(99) + 1;
			if (c == 50 || c == 0) {
				i--;
				continue;
			}
			arr[i] = c;
		}
		Set<Integer> set = new HashSet<>(200);
		for (int i : arr) {
			Integer lover = 100 - i;
			if (set.contains(lover)) {
				System.out.println(i + "," + (100 - i));
				break;
			}else {
				set.add(i);
			}
		}
		//先排序
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j+1]) {
					arr[j+1] = (arr[j+1] + arr[j]) - (arr[j] = arr[j+1]);
				}
			}
		}
		int first = 0;
		int last = arr.length-1;
		while(first != last) {
			int num = arr[first]+arr[last];
			if(num == 100) {
				System.out.println(arr[first] + "," + arr[last]);
				break;
			}
			if(num > 100) {
				last--;
				continue;
			}
			if(num < 100) {
				first++;
				continue;
			}
		}
	}

}
