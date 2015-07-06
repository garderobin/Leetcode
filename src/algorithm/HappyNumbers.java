package algorithm;

import java.util.HashSet;

public class HappyNumbers {
	public static boolean isHappy(int n) {
		HashSet<Integer> hs = new HashSet<Integer>();
		int digit, len;
		String s;
		
		while (n !=1  && !hs.contains(n)) {
			hs.add(n);
			s = n + "";
			len = s.length();
			n = 0;
			for (int i = 0; i < len; i++) {
				digit = s.charAt(i) - 48;
				n += digit * digit;
			}			
		}
		
        return (n==1);
    }
}
