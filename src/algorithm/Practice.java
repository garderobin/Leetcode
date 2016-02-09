package algorithm;

import java.util.HashMap;

public class Practice {
	public int longestConsecutiveSequence(int[] nums) {
		int res = 0, left, right, sum;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int e : nums) {
			if (!map.containsKey(e)) {
				left = (map.containsKey(e - 1)) ? map.get(e - 1) : 0;
				right = (map.containsKey(e + 1)) ? map.get(e + 1) : 0;
				sum = left + right + 1; 	//sum: 新sequence长度
				res = Math.max(sum, res);
				map.put(e - left, sum); 	//e - left: 刚好是新sequence七点
				map.put(e + right, sum); 	//e + right: 是新sequence终点
			}
		}
		return res;
	}

}
