package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 两个限制：单个元素上限255
 * 单个元素是一位，两位，三位数字；
 * 每个String集合都有且只有四个元素
 * @author jasmineliu
 *
 */
public class RestoreIPAddresses {
	public static List<String> restoreIpAddresses(String s) {
        List<String> rst = new ArrayList<String>();
        if (s == null || s.length() < 4) {
        	return rst;
        }
        return helper(s, rst, new StringBuilder(s), 0);
    }
	
	public static List<String> helper(String s, List<String> rst, StringBuilder sb, int start) {
		int len = sb.length();
		if (len == s.length() + 3) {
			try {
				int v = Integer.parseInt(sb.substring(start, len));
				if (v < 256 && (!(len - start > 1 && sb.charAt(start) == '0'))) {
					rst.add(sb.toString());
				}	
			} catch(Exception e) {

			}
			return rst;
		}
				
		for (int k = 1; k < 4; k++) {			
			if (start + k >= len || Integer.parseInt(sb.substring(start, start + k)) > 255 ||
					(sb.charAt(start) == '0' && k > 1) ) {
				break;
			}			
			start += k;
			sb.insert(start, '.');
			
			helper(s, rst, sb, start + 1);
			sb.deleteCharAt(start);
			start -= k;			
		}
		return rst;
	}
	
	public static void main(String[] args) {
		//String s = "25525511135";
		String s = "00000";
		List<String> rst = restoreIpAddresses(s);		
		for (String element : rst) {
			System.out.println(element);
		}
	}
}
