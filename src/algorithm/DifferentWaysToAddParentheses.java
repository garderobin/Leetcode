package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DifferentWaysToAddParentheses {
	/**
	 * 
	 * @param input
	 * @return
	 */
	public List<Integer> diffWaysToComputeDiscussion(String input) {
        List<Integer> ret = new LinkedList<Integer>();
        for (int i=0; i<input.length(); i++) {
            if (input.charAt(i) == '-' ||
                input.charAt(i) == '*' ||
                input.charAt(i) == '+' ) {
                String part1 = input.substring(0, i);
                String part2 = input.substring(i+1);
                List<Integer> part1Ret = diffWaysToCompute(part1);
                List<Integer> part2Ret = diffWaysToCompute(part2);
                for (Integer p1 :   part1Ret) {
                    for (Integer p2 :   part2Ret) {
                        int c = 0;
                        switch (input.charAt(i)) {
                            case '+': c = p1+p2;
                                break;
                            case '-': c = p1-p2;
                                break;
                            case '*': c = p1*p2;
                                break;
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
	
	/**
	 * 我的想法：预处理然后递归
	 * 括号合法的要求：
	 * 1. 只能在运算符前后出现，这是为了防止有比较大的数字参与运算；
	 * 2. 不能只扩单独的数字或运算符；
	 * 3. 不能交叉
	 * 
	 * @param input
	 * @return
	 */
	public static List<Integer> diffWaysToCompute(String input) {
        List<Integer> rst = new ArrayList<Integer>();
        if (input == null || input.length() == 0) {
        	return rst;
        }
        int len = input.length();
        ArrayList<Integer> nums = new ArrayList<Integer>(len / 2);
        ArrayList<Character> ops = new ArrayList<Character>(len / 2);
        
        // Preprocessing
        char[] c = input.toCharArray();
        int curNum = 0, curDigit = 0;
        for (int i = 0; i < len; i++) {
        	if (c[i] == '*' || c[i] == '-' || c[i] == '+') {
        		ops.add(c[i]);
        		nums.add(curNum);
        		curNum = -1;
        	} else if (Character.isDigit(c[i])) {
        		curDigit = (int)c[i] - '0';
        		curNum = curNum * 10 + curDigit;        		
        	} else {
        		return rst; // Invalid input.
        	}
        }
        nums.add(curNum);
        
        // Divide and Conquer
        rst.addAll(addParentheses(rst, nums, ops, 0, nums.get(0)));
        
        return rst;
    }
	
	private static List<Integer> addParentheses(List<Integer> rst, ArrayList<Integer> nums, ArrayList<Character> ops, int start, int prev) {
		List<Integer> temp = new ArrayList<Integer>();
		int num1 = nums.get(start);
		int num2 = nums.get(start + 1);
		char op = ops.get(start);
		// Last operator
		if (start == ops.size() - 1) {
			rst.add(calcSingleAndSingle(prev, num1, op));
			return rst;
		}
		
		temp = calcSingleAndArray(prev, addParentheses(rst, nums, ops, start + 1, num2), op);
		prev = calcSingleAndSingle(prev, num2, op);
		temp.addAll(addParentheses(rst, nums, ops, ++start, prev));		
		return temp;
	}
	
	private static int calcSingleAndSingle(int prev, int after, char op) {
		switch (op) {
		case '+' : 
			return prev + after;
		case '-' : 
			return prev - after;
		default: //case '*' : 
			return prev * after;
		}
	}
	
	private static List<Integer> calcSingleAndArray(int prev, List<Integer> list, char op) {
		int len = list.size();
		switch (op) {
		case '+' : 
			for (int i = 0; i < len; i++) {
				list.set(i, list.get(i) + prev);
			}
			break;
		case '-' : 
			for (int i = 0; i < len; i++) {
				list.set(i, prev - list.get(i));
			}
			break;
		case '*' : 
			for (int i = 0; i < len; i++) {
				list.set(i, list.get(i) * prev);
			}
			break;
		}
		return list;
	}
	
//	private static void testPreprocessing() {
//		String input1 = "2-14-1";   
//        int len = input1.length();
//        ArrayList<Integer> nums = new ArrayList<Integer>(len / 2);
//        ArrayList<Character> ops = new ArrayList<Character>(len / 2);
//        
//        // Preprocessing
//        char[] c = input1.toCharArray();
//        int curNum = -1, curDigit = 0;
//        for (int i = 0; i < len; i++) {
//        	if (c[i] == '*' || c[i] == '-' || c[i] == '+') {
//        		ops.add(c[i]);
//        		nums.add(curNum);
//        		curNum = -1;
//        	} else if (Character.isDigit(c[i])) {
//        		curDigit = (int)c[i] - '0';
//        		curNum = (curNum == -1) ? curDigit : curNum * 10 + curDigit;        		
//        	} else {
//        		System.out.println("Invalid!");
//        		System.exit(0);
//        	}
//        }
//        nums.add(curNum);
//        
//        for (int num: nums) {
//        	System.out.print(num + ",\t");
//        }
//        System.out.println();
//        for (char op: ops) {
//        	System.out.print(op + ",\t");
//        }
//        
//	}
	
	public static void main(String[] args) {
		String input = "2*3-4*5";
		List<Integer> rst = diffWaysToCompute(input);
		for (int e : rst) {
			System.out.print(e + ", ");
		}
	}
}
