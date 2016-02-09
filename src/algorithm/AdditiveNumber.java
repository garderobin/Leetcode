package algorithm;

import java.math.BigInteger;

public class AdditiveNumber {
	public static boolean isAdditiveNumber(String num) {
		if (num == null || num.length() == 0)  { return false; }
        int len = num.length(), i, j;
        if (len < 10) {
        	int sum = 0, n1, n2;
        	try {
	        	for (i = 1; i < len; i++) {
	        		if (num.charAt(i) =='0') { //第二个数字不能是零开头
	        			if (intHelper(num, Integer.parseInt(num.substring(0, i)), 0, i+1)) { return true;}
	        		} else {
	        			n1 = Integer.parseInt(num.substring(0, i));
		        		for (j = i+1; j <= len; j++) {
		        			n2 = Integer.parseInt(num.substring(i, j));
		        			if (intHelper (num, n1, n2, j)) { return true; }
		        		}
	        		}
	        		
	        	}
	        	return false;
        	} catch (NumberFormatException e) {
        		return false;
        	}
        } else if (len < 20) { //long最长用十进制表示是19位数
        	long sum = 0, n1, n2;
        	try {
	        	for (i = 1; i < len; i++) {
	        		if (num.charAt(i) =='0') { //第二个数字不能是零开头
	        			if (longHelper(num, Long.parseLong(num.substring(0, i)), 0, i+1)) { return true;}
	        		} else {
		        		n1 = Long.parseLong(num.substring(0, i));
		        		for (j = i+1; j < len; j++) {
		        			n2 = Long.parseLong(num.substring(i, j));
		        			if (longHelper (num, n1, n2, j)) { return true; }
		        		}
	        		}
	        	}
	        	return false;
        	} catch (NumberFormatException e) {
        		return false;
        	}
        } else {
        	String s1, s2;
        	try {
	        	for (i = 1; i < len; i++) {
	        		if (num.charAt(i) =='0') { //第二个数字不能是零开头
	        			if (bigIntegerHelper(num, new BigInteger(num.substring(0, i)), new BigInteger("0"), i+1)) { return true;}
	        		} else {
	        			s1 = num.substring(0, i);
		        		for (j = i+1; j < len; j++) {
		        			s2 = num.substring(i, j);
		        			if (bigIntegerHelper(num, new BigInteger(s1), new BigInteger(s2), j)) { return true; }
		        		}
	        		}
	        		
	        	}
	        	return false;
        	} catch (NumberFormatException e) {
        		return false;
        	}
        }
    }
	
	public static boolean intHelper(String num, int n1, int n2, int start) {
		if (start >= num.length()) { return false; }
		int cur = n1 + n2, curLen = (int)Math.log10(cur), end = start + curLen + 1, temp;
		if (end > num.length()) { return false; }
		try{
			temp = Integer.parseInt(num.substring(start, end));
		} catch (NumberFormatException e) {
    		return false;
    	}
		if (cur != temp) { return false; }
		if (end == num.length()) { return true; }
		return intHelper (num, n2, cur, end);
	}
	
	public static boolean longHelper(String num, long n1, long n2, int start) {
		int totalLen = num.length();
		if (start >= totalLen) { return false; }
		long cur = n1 + n2, temp;
		int curLen = (int)Math.log10(cur), end = start + curLen + 1;
		if (end > totalLen) { return false; }
		try{
			temp = Long.parseLong(num.substring(start, end));
		} catch (NumberFormatException e) {
    		return false;
    	}
		if (cur != temp) { return false; }
		if (end == totalLen) { return true; }
		return longHelper (num, n2, cur, end);
		
	}
	
	public static boolean bigIntegerHelper(String num, BigInteger n1, BigInteger n2, int start) {
		BigInteger cur = n1.add(n2), temp;
		int totalLen = num.length(), curLen = cur.toString().length(), end = start + curLen;
		if (start >= totalLen || end > totalLen) { return false; }
		
		try{
			temp = new BigInteger(num.substring(start, end));
		} catch (NumberFormatException e) {
    		return false;
    	}
		if (!cur.equals(temp)) { return false; }
		if (end == totalLen) { return true; }
		return bigIntegerHelper(num, n2, cur, end);
	}
	
	public static void main(String[] args) {
		System.out.println(isAdditiveNumber("101123581321345589144"));
	}
}
