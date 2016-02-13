package GoogleOA;

import java.util.ArrayList;
import java.util.List;

public class FlipGameII {
	
	/**
	 * 
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
	
//	private static boolean mustLose(String s) {
//		List<String> oneMove = generatePossibleNextMoves(s);
//        if (oneMove.size() == 0) {
//        	return true;
//        }
//        return false;
//	}
	
//	private static boolean helper(String s, ) {
//		
//	}
	
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
