package algorithm;

public class ValidPalindrome {
	public static boolean isPalindrome(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		int len = s.length();
        int head = 0;
        int tail = len - 1;
        char h, t;
        while(head < tail) {
        	while (!(Character.isLetter(s.charAt(head)) || Character.isDigit(s.charAt(head)))) {
        		head++;
        		if (head > tail) {
            		return true;
            	}
        	}        	
        	while (!(Character.isLetter(s.charAt(tail)) || Character.isDigit(s.charAt(tail)))) {
        		tail--;
        		if (head > tail) {
            		return true;
            	}
        	}        	
        	if (head > tail) {
        		return true;
        	}        	        	
        	h = Character.toLowerCase(s.charAt(head));
        	t = Character.toLowerCase(s.charAt(tail));
        	if (h == t) {
        		head++;
        		tail--;
        		continue;
        	} else {
        		return false;
        	}
        }
		return true;
    }
	
	public static void main(String[] args) {
		String s1 = "A man, a plan, a canal: Panama";
		System.out.println(isPalindrome(".,"));
		String s2 = "race a car";
		System.out.println(isPalindrome(s2));
	}
}
