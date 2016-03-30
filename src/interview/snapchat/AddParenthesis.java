package interview.snapchat;

import java.util.*;

/*
 * Ask: allow signs in expression like 2 + -3 or -2 + 3? 
 * Ask: allow negative numbers in parenthesis? like 2 + (-3)?
 * Ask: allow possible output or part of output out of Integer's Max and Min allowed value?
 * This version does not allow any of them.
 */
public class AddParenthesis {
	static long count = 0;
	
	public static void main(String[] args) {
		String[] testDiff = {
//				"1 - 2 + 3 * 4 / 5", 
//				"2 - 3 + 4 * 5 / 2",
//				"2 - 3 + 4 * 5 / 0 - 2",
//				"2 - 3 + 4 * 5 / 2 - 2",
//				"1 + 2 + 3", 
				"1 - 2 + 3 - 4 + 5 - 6 + 7 - 8 + 9 - 10 + 11 - 12 + 13 - 14 + 15 - 16 + 17",
				
		};
		StringBuilder sb = new StringBuilder();
		for (String s: testDiff) {
			for (int i = 1; i <= 64; i <<= 1) {
				sb = new StringBuilder();
				for (int j = 0; j < i; ++j) {
					sb.append(s);
				}
				count = 0;
				maxComputeAddParenDFSCaching(sb.toString());
				System.out.println("caching count = " + count);
				count = 0;
				maxComputeAddParen(sb.toString());
				System.out.println("no-cach count = " + count);
			}
//			count = 0;
//			List<Integer> rst = diffWaysToCompute(s);
////			System.out.println(rst);
//			Set<Integer> set = new HashSet<>();
//			set.addAll(rst);
//			rst.clear();
//			rst.addAll(set);
//			rst.sort((Integer o1, Integer o2)-> o1 - o2);
//			System.out.println(rst);
			
//			System.out.println(maxComputeAddParen(s));
//			System.out.println("count = " + count);
//			count = 0;
//			System.out.println(s);
//			System.out.println(maxComputeAddParenDFSCaching(s));
//			System.out.println("count = " + count);
		}
	}
	
	public static int maxComputeAddParen(String input) {
		if (input == null || input.length() == 0) return 0;
		else {
			int[] vals = evalSeqDFS(input, 0, input.length());
			return (vals == null) ? 0 : vals[0];
		}
	}
	
	
	
	public static int maxComputeAddParenDFSCaching(String input) {
		if (input == null || input.length() == 0) return 0;
		else {
			int[][][] dp = new int[1 + input.length()/2][1 + input.length()/2][2];
			char[] ops = new char[input.length()/2];
			int m = 0, num = 0; // m is operator count
			
			// Initialize each max with INT_MIN and each min with INT_MAX, dp[i][i] = ith number
			for (int i = 0; i < dp.length; ++i) 
				for (int j = i; j < dp[i].length; ++j) 
					dp[i][j] = new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};
			
			boolean numNotFinish = false;
			for (int i = 0, n = input.length(); i < n; ++i) {
				char c = input.charAt(i);
				if (Character.isDigit(c)) {
					numNotFinish = true;
					num = num * 10 + c - '0';
					if (i == n - 1) dp[m][m] = new int[]{num, num}; 
				} else {
					if (numNotFinish) dp[m][m] = new int[]{num, num};
					numNotFinish = false;
					num = 0;
					if (c == '-' || c == '*' || c == '+' || c == '/') ops[m++] = c;
					else {} // currently we do not have invalid input
				}
			}
			
			int[] vals = evalSeqDFSCaching(dp, ops, 0, m);
			
			return (vals[0] < vals[1]) ? 0 : vals[0];
		}
	}

	public static List<Integer> diffWaysToCompute(String input) {
		if (input == null || input.length() == 0) return new ArrayList<Integer>();
		else return diffWaysDFS(input, 0, input.length());
    }
	
	
	
	/**
	 * Evaluate a string sequence by DFS with memory cache.
	 * O(N^3) time, O(N^3) space.
	 * @return 	[max_res, min_res] 	- the maximum and minimum value of the valid evaluations by adding parenthesis
	 * 		   	[INT_MIN, INT_MAX] 	- the sequence can not be evaluated (currently the only reason is dividing a zero)
	 */
	private static int[] evalSeqDFSCaching(int[][][]dp, char[] ops, int start, int end) { //end is inclusive
		++count;
		if (dp[start][end][0] >= dp[start][end][1]) return dp[start][end]; // already computed
		
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		for (int i = start; i < end; ++i) { // i is the pointer for operator, not numbers, so i can not reach end which may be the last number counter = opCount + 1
			int[] left = evalSeqDFSCaching(dp, ops, start, i);
			int[] right = evalSeqDFSCaching(dp, ops, i+1, end);
			if (left[0] < left[1] || right[0] < right[1]) continue; // divide by zero happens
			
            int cur = 0, curMax = Integer.MIN_VALUE, curMin = Integer.MAX_VALUE; // record the borders of the 4 computes: left max and minimum operates on right max and minimum
			for (int p1 = 0; p1 < 2; ++p1) {
				if (p1 > 0 && left[p1] == left[p1-1]) break; //same max and min
				
				for (int p2 = 0; p2 < 2; ++p2) {
					if (p2 > 0 && right[p2] == right[p2-1]) break; // same max and min
					int x = left[p1], y = right[p2];
					if (ops[i] == '/' && y == 0) { continue; } // divide by zero, returns [INT_MIN, INT_MAX]
					else {
						switch (ops[i]) {
							case '+': cur = x + y; break;
							case '-': cur = x - y; break;
							case '*': cur = x * y; break;
							case '/': cur = x / y; break; //java default way, possible loss of accuracy.
							default: cur = 0; // never enters this line
						}
						if (curMax < cur) curMax = cur; // use curMax and curMin to prevent max and min modified by grand children threads.
						if (curMin > cur) curMin = cur; 
					}
                }
            }
            if (curMax >= curMin) { 
             	if (max < curMax) max = curMax;
                if (min > curMin) min = curMin;
            }
		}
		dp[start][end][0] = max; dp[start][end][1] = min;
		return dp[start][end];
	}

//	
//	if (start == 0 && end == 4) {
//		System.out.println(dp[start][start][0] + " \tto\t" + 
//		dp[end][end][0] + "\t = \t" + dp[start][end][0] + ", \t" + dp[start][start][1]);	
//    }
	
	
	/**
	 * Evaluate a string sequence by DFS.
	 * O(N*N*logN) time, O(NlogN) space.
	 * @return 	[max_res, min_res] 	- the sequence has different evaluations by adding parenthesis
	 * 		   	[res] 	  			- the sequence has only on evaluation result
	 * 		   	null 	  			- the sequence can not be evaluated (currently the only reason is dividing a zero)
	 */
	private static int[] evalSeqDFS(String input, int start, int end) {
		if (start == end) return new int[]{0};
		
		boolean divideByZero = false;
		int num = 0, cur = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		
		for (int i = start; i < end; ++i) {
			char c = input.charAt(i);
			if (Character.isWhitespace(c) || Character.isSpaceChar(c)) { continue; } // ignore space
            else if (Character.isDigit(c)) { num = num * 10  + c - '0'; }
            else if (c == '-' || c == '*' || c == '+' || c == '/') {
                int[] left = evalSeqDFS(input, start, i);
                int[] right = evalSeqDFS(input, i+1, end);
                if (left == null || right == null) continue; // divide by zero happens
                
                int curMax = Integer.MIN_VALUE, curMin = Integer.MAX_VALUE; // record the borders of the 4 computes: left max and min operates on right max and min
                for (int p1: left) {
                    for (int p2: right) {
                    	if (c == '/' && p2 == 0) { // never allow dividing by zero, continue inner for
                    		divideByZero = true; continue;
                    	} else {
                    		divideByZero = false;
                    		switch (c) {
	                            case '+': cur = p1 + p2; break;
	                            case '-': cur = p1 - p2; break;
	                            case '*': cur = p1 * p2; break;
	                            case '/': cur = p1 / p2; break; //java default way, possible loss of accuracy.
	                            default: cur = 0; // never enters this line
	                        }
	                        if (cur > curMax) curMax = cur; // use curMax and curMin to prevent max and min modified by grand children threads.
	                        if (cur < curMin) curMin = cur; 
                    	}
                    }
                }
                
                if (curMax >= curMin) { 
                	if (curMax > max) max = curMax;
                    if (curMin < min) min = curMin;
                }
                
                num = 0;
            } else {} // invalid input, currently we do not handle it
			
        }
		
		return (max < min) ? (divideByZero ? null : new int[]{num}) : new int[]{max, min};
	}

	
	/**
	 * @return the expression number of null if the expression is dividing by zero.
	 */
	private static List<Integer> diffWaysDFS(String input, int start, int end) {
		List<Integer> rst = new ArrayList<>();
		if (start == end) return Collections.singletonList(0);
		int num = 0;
		boolean divideByZero = false;
		for (int i = start; i < end; ++i) {
			char c = input.charAt(i);
			if (Character.isWhitespace(c) || Character.isSpaceChar(c)) { continue; } // ignore space
            else if (Character.isDigit(c)) { num = num * 10  + c - '0'; }
            else if (c == '-' || c == '*' || c == '+' || c == '/') {
                List<Integer> left = diffWaysDFS(input, start, i);
                List<Integer> right = diffWaysDFS(input, i+1, end);
                if (left == null || right == null) continue; // divide by zero happens
                
                for (Integer p1: left) {
                    for (Integer p2: right) {
                        switch (c) {
                            case '+': rst.add(p1 + p2); break;
                            case '-': rst.add(p1 - p2); break;
                            case '*': rst.add(p1 * p2); break;
                            case '/': 
                            	if (p2 == 0) {
                            		divideByZero = true; 
                            		continue; // never allow dividing by zero
                            	} else {
                            		divideByZero = false;
                            		rst.add(p1 / p2); 
                            		break; //java default way, possible loss of accuracy.
                            	}
                            default: rst.add(0);
                        }
                    }
                }
                
                num = 0;
            } else {} // invalid input, currently we do not handle it
        }
		
		
        if (rst.isEmpty()) {
        	if (num == 0 && divideByZero) return null; // expression failed by dividing a zero
        	else rst.add(num); //only one number, no operator.
        }
        
        return rst;
	}

	/*
	 * 这个版本不处理除法
	 */
	public static List<Integer> diffWaysToComputeWithoutDividing(String input) {
        List<Integer> ret = new LinkedList<Integer>();
        for (int i = 0, len = input.length(); i < len; ++i) {
            if (input.charAt(i) == '-' ||
                input.charAt(i) == '*' ||
                input.charAt(i) == '+' ) {
                String part1 = input.substring(0, i);
                String part2 = input.substring(i + 1);
                List<Integer> part1Ret = diffWaysToComputeWithoutDividing(part1);
                List<Integer> part2Ret = diffWaysToComputeWithoutDividing(part2);
                for (Integer p1: part1Ret) {
                    for (Integer p2: part2Ret) {
                        int c = 0;
                        switch (input.charAt(i)) {
                            case '+': c = p1 + p2; break;
                            case '-': c = p1 - p2; break;
                            case '*': c = p1 * p2; break;
                            default:;
                        }
                        ret.add(c);
                    }
                }
            }
        }
        if (ret.size() == 0) { //只有一个数字没有操作符的情况
            ret.add(Integer.valueOf(input));
        }
        return ret;
    }
}
