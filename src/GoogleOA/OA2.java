package GoogleOA;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OA2 {
	
	/**
	 * Task 2 in first round OA.
	 * Using two stacks.
	 * Picture extentions: .jpeg, .png, .gif
	 * @param S
	 * @return
	 */
	private static int solutionUsingOneStack(String S) {
		Stack<Integer> pathLen = new Stack<>();
		int dirLevel = 0, maxLen = 0;
		String[] lines = S.split("\\r?\\n");
		for (int i = 0, curLen = 1; i < lines.length; i++) {
			String str = lines[i];
			int level = getLevelOfLine(str), dirLen = str.length() - level;			
			int indexOfSpot = str.indexOf('.');
			
			if (indexOfSpot < 0 || isPictureExtension(str, indexOfSpot)) {
				while (dirLevel > level) {
					dirLevel--;
					pathLen.pop();			
				}
				curLen = ((pathLen.isEmpty()) ? 0: pathLen.peek()) + 1 + dirLen;
				if (indexOfSpot < 0) { // this line is a path
					pathLen.push(curLen); // path + '/dir'
					dirLevel++;
				} else { // this line is a file name
					maxLen = (maxLen > curLen) ? maxLen : curLen;
				}
				
			}
			
		}
		
		return maxLen;
	}
	
	private static int longestPath(String path) {
		String[] strs = path.split("\n");
		Stack<Integer> parentIndent = new Stack<>(), parentLength = new Stack<>();
		Pattern pattern = Pattern.compile("\\.(gif|png|jpeg)$");
		int length = strs.length, max = 0;
		int[] selfLength = new int[length];
		parentIndent.push(0);
		parentLength.push(0);
		for (int i = 0; i < length; i++) {
			int strLen = strs[i].length(), trimLen = strs[i].trim().length(), parentInd = parentIndent.peek();
			if (strLen - trimLen > parentInd) {
				parentIndent.push(parentInd + 1);
				parentLength.push(selfLength[i-1]);
			} else if (strLen - trimLen < parentInd) {
				while (strLen - trimLen != parentIndent.peek()) {
					parentIndent.pop();
					parentLength.pop();
				}
			}
			selfLength[i] = parentLength.peek() + trimLen + 1;
			Matcher matcher = pattern.matcher(strs[i]);
			if (matcher.find() && selfLength[i] > max) {
				max = selfLength[i];
			}
		}
		return max;
	}
	
	/**
	 * Task 2 in first round OA.
	 * Using two stacks.
	 * Picture extentions: .jpeg, .png, .gif
	 * @param S
	 * @return
	 */
	private static int solutionUsingTwoStack(String S) {
		Stack<Integer> pathLen = new Stack<>();
		Stack<Integer> dirLevel = new Stack<>();
		String[] lines = S.split("\\r?\\n");
		int maxLen = 0;
		for (int i = 0, curLen = 1; i < lines.length; i++) {
			String str = lines[i];
			int level = getLevelOfLine(str), dirLen = str.length() - level;			
			int indexOfSpot = str.indexOf('.');
			
			if (indexOfSpot < 0 || isPictureExtension(str, indexOfSpot)) {
				while (!pathLen.isEmpty() && dirLevel.peek() >= level) {
					dirLevel.pop();
					pathLen.pop();			
				}
				curLen = ((pathLen.isEmpty()) ? 0: pathLen.peek()) + 1 + dirLen;
				if (indexOfSpot < 0) { // this line is a path
					pathLen.push(curLen); // path + '/dir'
					dirLevel.push(level);
				} else { // this line is a file name
					maxLen = (maxLen > curLen) ? maxLen : curLen;
				}
				
			}
			
		}
		
		return maxLen;
	}
	
	/**
	 * 可能需要修改，因为目前并不知道startsWith(s, index)的复杂度
	 * @param str
	 * @param index
	 * @return
	 */
	private static boolean isPictureExtension(String str, int index) {
		return str.startsWith(".jpeg", index) || str.startsWith(".png", index) || str.startsWith(".gif", index);
	}
	
	
	private static int getLevelOfLine(String line) {
		int i = 0;
		for (int len = line.length(); i < len && line.charAt(i) == ' '; i++) {}
		return i;
	}

	public static void testSolutionUsingOneStack(String s) {
		long startTime1 = System.nanoTime();
		for (int i = 0; i < 500000; i++) {
			solutionUsingOneStack(s);
		}
		long endTime1 = System.nanoTime();
		System.out.println((endTime1 - startTime1)/1000000.0 + " ms");
	}
	
	public static void testSolutionUsingTwoStack(String s) {
		long startTime1 = System.nanoTime();
		for (int i = 0; i < 500000; i++) {
			solutionUsingTwoStack(s);
		}
		long endTime1 = System.nanoTime();
		System.out.println((endTime1 - startTime1)/1000000.0 + " ms");
	}
	
	public static void testKangran(String s) {
		long startTime1 = System.nanoTime();
		for (int i = 0; i < 500000; i++) {
			longestPath(s);
		}
		long endTime1 = System.nanoTime();
		System.out.println((endTime1 - startTime1)/1000000.0 + " ms");
	}
	
	public static void main (String args[]) {
		String[] test = {"dir1\n dir11\n dir12\n  picture.jpeg\n  dir121\n  longlonglong.txt\ndir2\n file2.gif",
				"dir2\n file2.png", 
				"dir2\n file1.png\n file2.jpeg\n file33.gif\n",
				"a.gif",
				"gif\n dir11\n dir12\n  pictures.jpeg\n  dir121\n   1.png\ndir2\n file2.gif",
				"dir1\n dir11\n dir12\n  dir122\n   pictures.png\n  pictures.jpeg\n  dir121\n   1.png\ndir2\n file2.gif\n1234567890123456789012345678901234567890.png"
				};
		for (String s: test) {
			System.out.println(solutionUsingOneStack(s) + ",\t" + solutionUsingTwoStack(s) + ",\t" + longestPath(s));
			
		}
//		String s = "/gif/dir12/pictures.jpeg";
//		System.out.println(s.length());
//		String s = "dir1\n dir11\n dir12\n  picture.jpeg\n  dir121\n  longlonglong.txt\ndir2\n file2.gif";
//		testKangran(s);
	}

}
