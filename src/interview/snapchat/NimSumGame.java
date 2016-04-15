package interview.snapchat;

/*
 * 两个人从1-N里面不重复地取数出来加在同一个sum上，第一个超过target的人赢，求先手是不是能赢。 写的暴力recursive
 * Ask: 等于target算不算赢? 我认为不算
 * Ask: target < 1+2+3+...+n的话，算和棋还是算先手的人输？
 * Ask: 有没有可能谁都赢不了，比如target比1到N的和都大？
 * 我认为这题有数学trick：
 * 首先，sum增大可以用target减小相应的值来表示，因此获胜的条件是current target < current max available element
 * 如果在眼下这一轮获胜条件不满足，那我们一定要选择当前能选择的最小元素来使target减小得慢一点，对手在下一轮不容易够到
 * 所以这题就转化成： 求第一个满足条件的m使得 target - (1+2+3+4+5+6+...m) < n 
 * 也就是求（1+2+3+...+m) > target - n的第一个值是奇数还是偶数，如果是奇数，先手赢，如果是偶数，后手赢
 * 
 */
public class NimSumGame {
	public static void main(String[] args) {
		int[][] test = {
				{5, 10},
				{10, 5},
				{5, 5},
				{5, 11},
				{5, 30}
		};
		for (int[] t: test) {
			System.out.println(nimSum(t[0], t[1]));
		}
		
	}
	
	public static boolean nimSum(int n, int target) {
		if (target < n) return true;
		int sum = 0;
		for (int i = 1, limit = target - n; i <= n; ++i) {
			sum += i;
			if (sum > limit) return (i&1) == 1;
		}
		return false; // max sum from 1 to n is still smaller than target
	}

	
}

