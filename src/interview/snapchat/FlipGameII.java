package interview.snapchat;

import java.util.ArrayList;
import java.util.List;

import algorithm.Utils;

public class FlipGameII {
	
	public static void main(String[] args) {
		String s = "++--+-++++-+-++++++---+++++++++-";
		System.out.println(canWinV2ForTest(s));
	}
	
	/**
	 * DP. O(N^2) time.
	 * https://leetcode.com/discuss/64344/theory-matters-from-backtracking-128ms-to-dp-0ms
	 * Since we can Theorem 2 (Sprague-Grundy Theorem): The S-G function of game x = (s1, s2, ..., sk) equals the XOR of all its subgames s1, s2, ..., sk. e.g. g((s1, s2, s3)) = g(s1) XOR g(s2) XOR g(s3).only flip ++ to --, then apparently, we only need to write down the lengths of consecutive ++'s of length >= 2 to define a board. For instance, ++--+-++++-+----- can be represented as (2, 4).
	 * 
	 * @param s
	 * @return
	 */
	public static boolean canWinV2(String s) {
	    s = s.replace('-', ' ');
	    int G = 0;
	    List<Integer> g = new ArrayList<>();
	    for (String t : s.split("[ ]+")) {
	        int p = t.length();
	        if (p == 0) continue;
	        while (g.size() <= p) {
	            char[] x = t.toCharArray();
	            int i = 0, j = g.size() - 2;
	            while (i <= j)
	                x[g.get(i++) ^ g.get(j--)] = '-';
	            g.add(new String(x).indexOf('+'));
	        }
	        G ^= g.get(p);
	    }
	    return G != 0;
	}
	
	public static boolean canWinV3(String s) {
	    int G = 0;
	    List<Integer> g = new ArrayList<>();
	    for (String t : s.split("-")) {
	        int p = t.length();
	        if (p == 0) continue;
	        while (g.size() <= p) {
	            char[] x = t.toCharArray();
	            int i = 0, j = g.size() - 2;
	            while (i <= j)
	                x[g.get(i++) ^ g.get(j--)] = '-';
	            g.add(new String(x).indexOf('+'));
	        }
	        G ^= g.get(p);
	    }
	    return G != 0;
	}
	
	public static boolean canWinV2ForTest(String s) {
	    s = s.replace('-', ' ');
	    int G = 0;
	    List<Integer> g = new ArrayList<>();
	    for (String t : s.split("[ ]+")) {
	        int p = t.length();
	        if (p == 0) continue;
	        System.out.println("\nenter\tt=" + t + ",\tg=" + g + ",G=" + G);
	        while (g.size() <= p) {
	            char[] x = t.toCharArray();
	            int i = 0, j = g.size() - 2;
	            while (i <= j)
	                x[g.get(i++) ^ g.get(j--)] = '-';
	            System.out.print(Utils.charArrayToString(x));
	            System.out.print(",\tg=" + g);
	            g.add(new String(x).indexOf('+'));
	            System.out.println(",\tg=" + g);
	        }
	        G ^= g.get(p);
	        System.out.println("finish\tg[" + p + "]=" + g.get(p) + ",\tG=" + G);
	    }
	    return G != 0;
	}
	
	
	/**
	 * Backtracking version
	 * @param s
	 * @return
	 */
	public static boolean canWin(String s) {
        List<String> oneMove = generatePossibleNextMoves(s);
        if (oneMove.size() == 0) { return false;  }
        
        outer:
        for (String afterMyMove: oneMove) { //只要有一种走法能够保证接下来无论如何全胜就行
        	List<String> twoMove = generatePossibleNextMoves(afterMyMove);
        	if (twoMove.size() == 0) { return true; }
        	for (String afterYourMove: twoMove) { //针对对方出的每一种结果我们都必须保证全胜
        		if (!canWin(afterYourMove)) { continue outer; } //出现一种失败的后续结果，说明一开始的这步走法不可行。
        	}
        	return true; //我方的这步走法使得接下来针对对方出的每一种结果我们都保证了全胜，只要存在这样一种走法，我们就是胜利的。
        }
        return false; //我方不论怎么走，都无法保证接下来全胜
    }
	
	private static List<String> generatePossibleNextMoves(String s) {
		List<String> rst = new ArrayList<String>();
		if (s == null || s.length() == 0) { return rst; }
		for (int i = 0, len = s.length(), plusCount = 0; i < len; i++) {
			if (s.charAt(i) == '+') {
				if (plusCount == 0) {
					plusCount++;
				} else { // Find a new "++"
					rst.add(s.substring(0, i-1) + "--" + s.substring(i+1));
				}
			} else {
				plusCount = 0;
			}
		}
		return rst;
    }
}
