package GoogleOA;

import java.util.HashSet;
import java.util.Set;

//you can also use imports, for example:
//import java.util.*;

//you can write to stdout for debugging purposes, e.g.
//System.out.println("this is a debug message");
import java.util.Stack;

class Solution {
	final static int mod = 1000000007;
	public static int solution(String S) {
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
	
	public static void main (String args[]) {
		String[] test = {"dir1\n dir11\n dir12\n  picture.jpeg\n  dir121\n  longlonglong.txt\ndir2\n file2.gif",
				"dir2\n file2.png", 
				"dir2\n file1.png\n file2.jpeg\n file33.gif\n",
				"a.gif",
				"gif\n dir11\n dir12\n  pictures.jpeg\n  dir121\n   1.png\ndir2\n file2.gif",
				"dir1\n dir11\n dir12\n  dir122\n   pictures.png\n  pictures.jpeg\n  dir121\n   1.png\ndir2\n file2.gif\n1234567890123456789012345678901234567890.png"
				};
		for (String s: test) {
			System.out.println(solution(s));
			
		}
//		String s = "/gif/dir12/pictures.jpeg";
//		System.out.println(s.length());
//		String s = "dir1\n dir11\n dir12\n  picture.jpeg\n  dir121\n  longlonglong.txt\ndir2\n file2.gif";
//		testKangran(s);
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