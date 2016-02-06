package interview;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
	public List<String> generateParenthesis(int n) {
        List<String> rst = new ArrayList<String>();
        if (n < 1) {
            return rst; 
        }
        if (n == 1) {
        	rst.add("()");
        	return rst;
        }
        
        helper(n, n, n, "", rst);
        return rst;
    }
    
    private static void helper(int ul, int ur, int n, String str, List<String> rst)  {		
		if (str.length() == n + n) {
            rst.add(str);
			return;
		}
		if (ul > 0) {					
				helper(ul-1, ur, n, str + "(", rst);
		}			
		if (ur > ul) { // 关键代码，控制出现错误逻辑的情况例如())))(((					
				helper(ul, ur-1, n, str + ")", rst);
		}
	}
}