package algorithm;
/**
 * The count-and-say sequence is the sequence of integers beginning as follows:
	1, 11, 21, 1211, 111221, ...
	1 is read off as "one 1" or 11.
	11 is read off as "two 1s" or 21.
	21 is read off as "one 2, then one 1" or 1211.
 * @author jasmineliu
 *
 */
public class CountAndSay {
	public static String countAndSay(int n) {
		if (n < 1) { return ""; }
        String rst = "1";
        for (int i = 1; i < n; i++) {
        	rst = helper(rst);        	
        }
        return rst;
    }
	
	private static String helper(String s) {
		StringBuilder say = new StringBuilder();
		char c1 = s.charAt(0), c2;
		int i, len = s.length(), count = 1;
		for (i = 1; i < len; i++) {
			c2 = s.charAt(i);
			if (c2 == c1) { count++; }
			else {
				say.append(count);
				say.append(c1);
				count = 1;
				c1 = c2;
			}
		}
		say.append(count);
		say.append(c1);
		
		return say.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(countAndSay(4));
	}
	
}
