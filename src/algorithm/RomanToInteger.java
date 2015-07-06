package algorithm;

public class RomanToInteger {
	public static int romanToInt(String s) {
		int i, v1, v2;
		int sum = 0;
		char c1, c2;
		int limit = s.length() - 1;
		for (i = 0; i < limit; i++) {
			c1 = s.charAt(i);
			c2 = s.charAt(i+1);
			v1 = symbolToVal(c1);
			v2 = symbolToVal(c2);
			if (v1 < v2) {
				sum += v2 - v1;
				i++;
			} else {
				sum += v1;
			}
		}
		
		// If still a number left, add it to the sum;
		if (i == limit) {
			c1 = s.charAt(limit);
			v1 = symbolToVal(c1);
			sum += v1;
		}
		
		return sum;
        
    }
	
	//I（1）、V（5）、X（10）、L（50）、C（100）、D（500）and M（1000）
	public static int symbolToVal(char s) {
		int v = 0;
		if (Character.isLowerCase(s)) {
			s = Character.toUpperCase(s);
		}
		switch(s) {
			case 'I': v = 1; break;
			case 'V': v = 5; break;
			case 'X': v = 10; break;
			case 'L': v = 50; break;
			case 'C': v = 100; break;
			case 'D': v = 500; break;
			case 'M': v = 1000; break;
			default: break;
		}
		return v;
	}
	
	public static char valToSymbol(int v) {
		char c = ' ';		
		switch(v) {
			case 1: c = 'I'; break;
			case 5: c = 'V'; break;
			case 10: c = 'X'; break;
			case 50: c = 'L'; break;
			case 100: c = 'C'; break;
			case 500: c = 'D'; break;
			case 1000: c = 'M'; break;
			default: break;
		}
		return c;
	}
	
	public static void main(String[] args) {
		String s = "DCXXI";
		System.out.println(romanToInt(s));
		
	}
	
	public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int[] v = {1000, 500, 100, 50, 10, 5, 1};
        char[] c = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        int gap, temp;
//        while(num > 0) {
//        	if (num >= 1000) {
//            	num -= 1000;
//            	sb.append('M');
//            	continue;
//            } else if (num >= 500) {
//            	num -= 500;
//            	sb.append('D');
//            }        	
//        }
        for (int i = 0; i < v.length && num > 0; i++) {
        	temp = i/2 + 1;
    		gap = temp * 2;    		 
    		if (i < v.length - 1 && num < v[i] && num >= v[i] - v[gap]) {  // num starts with 4 or 9
    			num -= (v[i] - v[gap]);
        		sb.append(c[gap]);
        		sb.append(c[i]);
    		}
    		
        	while (num >= v[i]) {
        		num -= v[i];
        		sb.append(c[i]);
        	}
        	
        	// if the left starts with 4 or 9
//        	if (i < v.length - 1 && ((v[i] / v[i+1] > 2 && num > v[i] - v[i+1]) ||
//        			(v[i] / v[i+1] == 2 && num > (v[i]/10)*9)) ) {
//        		
//        		
//        	}         	
        	        	
        }
        
        
        return sb.toString();
    }
}
