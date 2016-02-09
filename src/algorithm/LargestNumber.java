package algorithm;

import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {
	
	/**
	 * 1. 比较器的用法
	 * 要知道String本身就是有内在比较器的；
	 * 这里巧用compare(str1+str2, str2+str1)来判断s1和s2的先后顺序。
	 * 然后就可以利用内置的排序函数，用不着我们自己动手了。 
	 * 2. num to string的转化，这里直接用num[i] + ""， 反正string自己也可以比较和排序。
	 * @param num
	 * @return
	 */
	public String largestNumberDiscussion(int[] num) {
	    if(num==null || num.length==0) return "";
	    String[] Snum = new String[num.length];
	    for(int i=0;i<num.length;i++) { Snum[i] = num[i]+""; }

	    Comparator<String> comp = new Comparator<String>(){
	        @Override
	        public int compare(String str1, String str2){
	            String s1 = str1+str2;
	            String s2 = str2+str1;
	            return s1.compareTo(s2);
	        }
	    };

	    Arrays.sort(Snum,comp);
	    
	    if(Snum[Snum.length-1].charAt(0)=='0') return "0";

	    StringBuilder sb = new StringBuilder();
	    for(String s: Snum) { sb.insert(0, s); }

	    return sb.toString();

	}
	
	/**
	 * nums[i].toString()这种写法是不合语法的
	 * @param nums
	 * @return
	 */
	public static String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) { return "0"; }        
        int i, j, temp, pos = 0;
        String[] strs = new String[nums.length];
        
        // First convert all integer elements to strings
        for (i = 0; i < nums.length; i++) {
        	strs[i] = Integer.toString(nums[i]);
        }        
        StringBuilder sb = new StringBuilder(strs[0]);
        
        // Bubble Sort
        for (i = 1; i < nums.length; i++) {
        	pos = sb.length();
        	String s = strs[i]; //这里绝对不能在下面直接用strs[i],因为冒泡过程随时可能改变i这个位置的内容
        	temp = i;
        	for (j = i - 1; j >= 0 && compareTwoNumStr(strs[j], s); j--) {
        		pos -= strs[j].length();
    			swap(strs, temp, j);
    			temp--;
        	}
        	sb.insert(pos, s);
        }
        
        // The final string can not be just lots of zeros.
        if (sb.charAt(0) == '0' && sb.length() > 1) { return "0"; }
        
        return sb.toString();
    }
	
	public static void swap(String[] strs, int i, int j) {
		String temp = strs[i];
		strs[i] = strs[j];
		strs[j] = temp;
	}
	
	/**
	 * return if s2 should be put in front of s1
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean compareTwoNumStr(String s1, String s2) {		
		char[] ch1 = s1.toCharArray(), ch2 = s2.toCharArray();
		int len = Math.min(ch1.length, ch2.length);
		int i;
		for (i = 0; i < len; i++) {
			if (ch1[i] == ch2[i]) { continue; } 
			return ch1[i] < ch2[i];
		}
		if (i < ch2.length) { return (compareTwoNumStr(s2, s2.substring(i) + s1)); }
		if (i < ch1.length) { return (compareTwoNumStr(s1.substring(i) + s2, s1)); }
		return false;
	}
	
	public static void testCompare() {
		System.out.println(compareTwoNumStr("354", "3"));
		System.out.println(compareTwoNumStr("312", "3"));
		System.out.println(compareTwoNumStr("3", "312"));
		System.out.println(compareTwoNumStr("123", "3"));		
	}
	
	public static void testSwap() {
		String[] strs = {"123", "354", "355", "4"};
		swap(strs, 0, 3);
		for (String s : strs) {
			System.out.print(s + "\t");
		}
	}
	
	public static void main(String[] args) {
//		int[] nums = {824,938,1399,5607,6973,5703,9609,4398,8247};
		int[] nums = {128, 12};
		System.out.println(largestNumber(nums));
		//System.out.println(compareTwoNumStr("824", "8247"));
	}
}
