package interview.google;

import java.util.HashMap;

public class StrobogrammaticNumber {
	public boolean isStrobogrammatic(String num) {
		if (num == null || num.length() == 0) { return true; }
        HashMap<Character, Character> map= new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
//        map.put('2', '5');
//        map.put('5', '2');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
        
        for (int startIndex = 0, endIndex = num.length() - 1; startIndex <= endIndex; startIndex++, endIndex--) {
        	char cs = num.charAt(startIndex), ce = num.charAt(endIndex);
        	if (!map.containsKey(cs) || map.get(cs) != ce ) { return false; }
        }
        
        return true;
    }
	
	/**
	 * 万一输入里面有非法字符尤其是空格，这个算法就不能处理了。
	 * @param num
	 * @return
	 */
	public boolean discuss(String num) {
		for (int i=0, j=num.length()-1; i <= j; i++, j--)
	        if (!"00 11 88 696".contains(num.charAt(i) + "" + num.charAt(j)))
	            return false;
	    return true;
	}
}
