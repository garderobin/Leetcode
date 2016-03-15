package interview.wayfair;

public class IsPalindrome {
	
	public boolean isPalindromeV1(String s) {
        if (s == null || s.length() == 0)  return true;
		
        for(int head = 0, tail = s.length() - 1; head < tail; ++head, --tail) {
        	for (; !isAlphanumeric(s.charAt(head)) && tail < head; ++head) {}
        	for (; !isAlphanumeric(s.charAt(tail)) && tail < head; --tail) {}
        	
        	if (head > tail)  return true;
        	if (Character.toLowerCase(s.charAt(head)) != Character.toLowerCase(s.charAt(tail))) return false;
        }
		return true;
    }
	
	private boolean isAlphanumeric(char c) {
		return Character.isLetter(c) || Character.isDigit(c);
	}
	
	/*
	 * Using regular expression to filter all ignoring characters.
	 * StringBuilder's reverse to compare palindrome.
	 */
	public boolean isPalindromeV2(String s) {
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuffer(actual).reverse().toString();
        return actual.equals(rev);
    }
}
