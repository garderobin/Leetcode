package interview.amazon;

/**
 * This is one of the interview questions during the Amazon SDE interview. Request your help in providing the solution. 
Question - We are interested in building a special type of sequence. for a given number N, we want to arrange the numbers {1,1,2,2,3,3,... N,N} such that they have the following property. 
For each number / in (1,N) there should be exactly / numbers between the first appearance of the number and the second appearance. Below example would clarify further. 

Input: 
A Single number N for which we want to produce the sequence. 

Output: 
A space separated list of sequence or NA if there is no possible sequence. 

Example Input: 
3 

Example Output: 
2 3 1 2 1 3 

Explanation : There is 1 number between 1s(2). There are 2 numbers between the 2's(3 1 ). There are 3 numbers between the 3's(1 2 1 ).
 * @author jasmineliu
 *
 */
public class AmazonSpaceSeparateSequence {
	public static String seperateAsNumber(int n) {
		
		return null;
	}
	
	private static String intListToString(int[] nums) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < nums.length - 1; i++) {
			sb.append(nums[i] + " ");
		}
		sb.append(nums[nums.length - 1]);
		return sb.toString();
	}
}
