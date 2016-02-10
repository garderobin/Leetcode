package GoogleOA;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a function to compute all possible states of the string after one valid move.
 * If there is no valid move, return an empty list [].
 * @author jasmineliu
 *
 */
public class FlipGame {
	public List<String> generatePossibleNextMoves(String s) {
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
