package algorithm;

public class PalindromeNumber {
	public static boolean isPalindrome(int x) {
		if (x < 0) {
			return false;
		}
        int len = (int)(Math.log10(x)) + 1;
        if (len == 1) {
        	return true;
        }
        int h, t, head, tail;
        h = (int)Math.pow(10, len-1);
        t = 1; 
        while (h > t) {
        	head = (x / h) % 10;
        	tail = (x / t) % 10;
        	if (head != tail) {
        		return false;
        	}
        	h /= 10;
        	t *= 10;
        }
        
		return true;
    }
	
	public static void main(String[] args) {
		int x = 16461;
		int h = 232;
		//System.out.println(isPalindrome(h));
		System.out.println(Integer.MAX_VALUE);
	}
}
