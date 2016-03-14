package needAgain.copy;

public class LongestValidParentheses {
	/* 这里为什么没有discussion, 我还需要再看这道题*/
	
	public static int longestValidParentheses(String s) {
		int max = 0, i, n = s.length(), start = 0, late_opens = 1;
		int[] early_opens = new int[n]; // early_opens[i] stores how many trailing open parenthesis can be added into valid are in the left of this index.
		int[] len = new int[n+1]; //len[i] stores the length of current max valid sequence that ends at index i.
		early_opens[0] = 0;
		
		//先找到第一个开头的左括号
		for (start = 0; start < n && s.charAt(start) != '('; start++){}		
		if (start > n - 2) { return 0; }
		early_opens[start + 1] = 1;
		//开始更新
		for (i = start + 1, start = -1; i < n; i++) {
			if (s.charAt(i) == '(') {
				early_opens[i] = late_opens;
				late_opens++;		
				len[i+1] = 0;
				//System.out.println("s[" + i + "] = (");
			} else {
				if (s.charAt(i-1) == ')') {  //上一个是')'
					if (len[i] > 0 && early_opens[i - len[i-1] - 1] > 0) { // start = (i-1) - len[i-1] + 1 - 1
						len[i+1] = len[i] + 2;
						while (len[i+1] > 0 && len[i - len[i] + 1] > 0) {
							len[i + 1] += len[i - len[i] + 1];
						}
					} else {
						len[i + 1] = 0;
					}						
				} else { //上一个是'('
					early_opens[i] = 0;
					if (late_opens > 1) {
						len[i + 1] = 2;
					} else { //late_opens == 1
						len[i + 1] = len[i-1];
					}
				}
				late_opens = 0;				
				if (len[i+1] > max) { 
					max = len[i+1]; 
				}
//				System.out.println("i = " + i + ", " + s.substring(i-len[i]+1, i+1) + 
//						", max = " + max + ", len[i-1] = " + len[i-1] + ", early_openings[" + (i - len[i-1])
//						+ "] = " + early_opens[i - len[i-1]]);
			}
			
		}
		
		return max;
	}
	
	/**
	 * Wrong. 贪心算法是不可能做出来这道题的。
	 * @param s
	 * @return
	 */
	public static int longestValidParenthesesV0(String s) {
		if (s == null || s.length() == 0 ) { return 0; }
        int max = 0, i, n = s.length(), start = 0, late_opens = 1;
		int[] early_opens = new int[n]; // early_opens[i] stores how many trailing open parenthesis can be added into valid are in the left of this index.
		int[] len = new int[n]; //len[i] stores the length of current max valid sequence that ends at index i.
		early_opens[0] = 0;
		
		//先找到第一个开头的左括号
		for (start = 0; start < n && s.charAt(start) != '('; start++){}		
		if (start > n - 2) { return 0; }
		early_opens[start + 1] = 1;
		//开始更新
		for (i = start + 1, start = -1; i < n; i++) {
			if (s.charAt(i) == '(') {
				early_opens[i] = late_opens;
				late_opens++;		
				len[i] = 0;
			} else {
				if (s.charAt(i-1) == ')') {  //上一个是')'
					if (len[i-1] > 0 && early_opens[i - len[i-1]] > 0) { // start = (i-1) - len[i-1] + 1
						len[i] = len[i-1] + 2;
						while (len[i] > 0 && i > len[i] && len[i - len[i]] > 0) {
							len[i] += len[i - len[i]];
						}
					} else {
						len[i] = 0;
					}						
				} else { //上一个是'('
					early_opens[i] = 0;
					if (late_opens > 1) {
						len[i] = 2;
					} else { //late_opens == 1
						len[i] = (i > 1) ? len[i - 2] + 2 : 2;
					}
				}
				late_opens = 0;				
				if (len[i] > max) { 
					max = len[i]; 
				}

			}
			
		}
		
		return max;
    }
	
	public static void main(String[] args) {
//		String s0 = ")()())";
//		String s = ")(((((()())()()))()(()))(";
//		String s1 = "))()()((())";
		String s2 = "()(())";
		System.out.println(longestValidParenthesesV0(s2));
	}
}
