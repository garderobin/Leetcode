package GoogleOA;

import java.util.HashSet;
import java.util.Set;

//you can also use imports, for example:
//import java.util.*;

//you can write to stdout for debugging purposes, e.g.
//System.out.println("this is a debug message");
import java.util.Stack;

/**
 * 这是2016/02/10晚上实际提交上去的结果，不是最优化的。
 * 第一题: 找到连续两个相同的数位，删掉其中一个以后的最大值。
 * 第二题：计算所有含有图片的路径（不重复，不包括图片文件名）总长度模以1000000007。
 * 
 * @author jasmineliu
 *
 */
public class ActualSubmit {
	
	public static int solutionOA1Actual(int X) {
    	int trail = 10, max = 0, num = X, prev= num % trail;
    	while(trail <= num) {
    		trail *= 10;
    		int second = num % trail, 
    				first = (num - second) / trail, 
    				between = second / (trail / 10);
    		if (between != prev) {
    			prev = between;
    			continue;
    		}
    		int result = first * (trail / 10) + second % (trail / 10);
    		if(result > max) {
    			max = result;
    		}
    	}
    	return max;
    }
	
	final static int mod = 1000000007;
	public static int solutionOA2Actual(String S) {
		Stack<String> path = new Stack<String>();
		Set<String> pathSet = new HashSet<String>();
		int dirLevel = 0;
		long rst = 0;
		String[] lines = S.split("\\r?\\n");
		for (int i = 0; i < lines.length; i++) {
			String str = lines[i];
			int level = getLevelOfLine(str);			
			int indexOfSpot = str.indexOf('.');
			
			if (indexOfSpot < 0 || isPictureExtension(str, indexOfSpot)) {
				while (dirLevel > level) {
					dirLevel--;
					path.pop();
				}
				String newPath = ((path.isEmpty()) ? "" : path.peek()) + "/" + str.trim();
				if (indexOfSpot < 0) { // this line is a path					
					path.push(newPath); // path + '/dir'
					dirLevel++;
				} else { // this line is a file name
					if (path.isEmpty()) {
						pathSet.add("/");
						rst = (rst + 1) % mod;
					} else if (pathSet.add(path.peek())) {
						rst = (rst + path.peek().length()) % mod;
					}
					
				}
				
			}
			
		}
		
		return (int)rst;
	}
 
	private static boolean isPictureExtension(String str, int index) {
		return str.startsWith(".jpeg", index) || str.startsWith(".png", index) || str.startsWith(".gif", index);
	}
	
	private static int getLevelOfLine(String line) {
		int i = 0;
		for (int len = line.length(); i < len && line.charAt(i) == ' '; i++) {}
		return i;
	}
}