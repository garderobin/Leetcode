package algorithm;

/**
 * A string contains a-z, A-Z and spaces. Sort the string so that all lower cases are at the beginning, spaces in the middle and upper cases at the end. Original order among lower and upper cases needs to remain the same. For example: a cBd LkmY becomes ackm BLY. Is there a way in O(n) without extra space?
 * @author jasmineliu
 *
 */
public class LetterResort {
	public static String letterResort(String s) {
		char[] ch = s.toCharArray();
		int upper = ch.length - 1, lower = 0;
		for (int i = 0; i <= upper; i++) {
			while (i < upper && Character.isUpperCase(ch[i])) swap(ch, i, --upper); //这里不能改成upper--， 否则无法保持原有顺序不变，非常重要。
			while (i > lower && Character.isLowerCase(ch[i])) swap(ch, i, ++lower);
		}		
		return String.copyValueOf(ch);
	}
	
	private static void swap(char[] ch, int index1, int index2) {
		char temp = ch[index1];
		ch[index1] = ch[index2];
		ch[index2] = temp;
	}
	
	public static void main(String[] args) {
		String s = "a cBd LkmY";
		System.out.println(letterResort(s));
	}
	

}
