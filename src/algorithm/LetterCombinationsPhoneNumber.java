package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsPhoneNumber {
	
	/**
	 * 贪心算法的关键：
	 * 1.一定要注意len 和 size的控制，否则会无限循环；
	 * 2. 初始化一定要做，否则会空集无限循环；
	 * 3. tmp和rst区分，避免长度不符合的情况出现；
	 * @param digits
	 * @return
	 */
	public static List<String> letterCombinations(String digits) {
        List<String> rst = new ArrayList<String>();
        List<String> tmp = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
        	return rst;
        }
        char[] clist = digits.toCharArray();
        int i, j, k, digit, size, len, num; 
        len = digits.length();
        char c;
        StringBuilder sb;
        String s, s1;
        for (i = 0; i < clist.length; i++) {
        	digit = (int)(clist[i] - 50);
        	size = tmp.size();   
        	num = (digit == 5 || digit == 7) ? 4 : 3;
        	for (j = 0; j < size; j++) {
        		s = tmp.get(j);           		        		
        		for (k = 0; k < num; k++) {
            		sb = new StringBuilder(s);
           			c = (char)(getStartCodeByDigit(digit) + k);
           			sb.append(c);
           			s1 = sb.toString();
           			if (s1.length() == len) {
           				rst.add(s1);
           			} else {
           				tmp.add(sb.toString());                			
           			}
           			
           		}        		        		
        	}
        	if (size == 0) {        		
        		for (k = 0; k < num; k++) {
        			sb = new StringBuilder();
        			c = (char)(getStartCodeByDigit(digit) + k);
        			sb.append(c);
        			if (len == 1) {
        				rst.add(sb.toString());
        			} else {
        				tmp.add(sb.toString());
            			
        			}
        		}
        	}
        }
        
        return rst;
    }	
	
	public static char getStartCodeByDigit(int digit) {
		if (digit < 6) {
			return (char)('a' + (digit * 3));
		} else if (digit == 6) {
			return 't';
		} else if (digit == 7) {
			return 'w';
		} 
		return 'w';
	}
	
	public static void main(String[] args) {
		List<String> rst = letterCombinationsBacktrace("29");
		for (int i = 0; i < rst.size(); i++) {
			System.out.println(rst.get(i));
		}
//		String s = "abc";
//		System.out.println(s.indexOf((int)'b'));
	}
	
	public static List<String> letterCombinationsBacktrace(String digits) {
        List<String> result = new ArrayList<String>();

        if (digits == null || digits.equals("")) {
            return result;
        }

        Map<Character, char[]> map = new HashMap<Character, char[]>();
        map.put('0', new char[] {});
        map.put('1', new char[] {});
        map.put('2', new char[] { 'a', 'b', 'c' });
        map.put('3', new char[] { 'd', 'e', 'f' });
        map.put('4', new char[] { 'g', 'h', 'i' });
        map.put('5', new char[] { 'j', 'k', 'l' });
        map.put('6', new char[] { 'm', 'n', 'o' });
        map.put('7', new char[] { 'p', 'q', 'r', 's' });
        map.put('8', new char[] { 't', 'u', 'v'});
        map.put('9', new char[] { 'w', 'x', 'y', 'z' });

        StringBuilder sb = new StringBuilder();        

        return helper(map, digits, sb, result);
    }
	
	public static List<String> helper(Map<Character, char[]> map, String digits, 
	        StringBuilder sb, List<String> rst) {
		String s = sb.toString();
		if (s.length() == digits.length()) {
			rst.add(s);
			return rst;
		}

		for (char c : map.get(digits.charAt(sb.length()))) {
            sb.append(c);
            System.out.println("before helper, sb = " + sb.toString());
            helper(map, digits, sb, rst);
            sb.deleteCharAt(sb.length() - 1);
        }
		
		
		return rst;
	}
}
