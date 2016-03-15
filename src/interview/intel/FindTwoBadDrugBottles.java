package interview.intel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindTwoBadDrugBottles {
	
	/*
	 * 有十瓶药，每瓶里都装有100片药，其中有八瓶里的药每片重10克，另有两瓶里的药每片重9克。用一个蛮精确的小秤，只称一次，如何找出份量较轻的那两个药瓶？
	 */
	public static void test() {
		int[] test = {0, 1, 2, 5, 8, 50, 61, 72, 85, 98}; // 这就是分发。
		List<Integer> sum = new ArrayList<Integer>();
		for (int i = 0; i < 10; ++i) {
			for (int j = i+1; j < 10; ++j) {
				sum.add(test[i] + test[j]);
			}
		}
		Collections.sort(sum);
		System.out.println(sum);
	}
	
	public int solution() {
		return 0;
	}
}
