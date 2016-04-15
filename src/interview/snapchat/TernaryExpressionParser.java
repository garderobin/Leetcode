package interview.snapchat;

/**
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=182651&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26sortid%3D311
 * 题目： ternary expression paser （三目运算符）， input是一个string，其中 'T' 表示true， 'F' 表示false， 要求返回最后运算的结果。
 * 乍一看题目很直接， bool expression ? first result : second result ，但实际上我们通常都用的都是非常简单的形式，但每一部分都可以无限嵌套。例如：
 * T ? T : F ? T ? 1 : 2 : F ? 3 : 4; 会转化成T : 1 : 4，然后返回1
 * 原本妹子没让我考虑bool expression部分也嵌套的情况，结果我本着把问题分析清楚的原则，成功把问题弄的复杂了，她后来觉得这可以作为follow up。。。
 * Ask: 允许空格吗？暂时我允许有。
 * Ask: 是否有非法输入？ 暂时我按照没有来算的。
 * Ask:能把T当成1，F当成0来算吗？
 * Ask:如果允许bool expression出现嵌套，那么多种解析方式可能存在吗？
if does not allow nesting in bool expression part.
	T ? T : [F ? (T ? 1 : 2 ): (F ? 3 : 4)]
= 	T ? T : [(F ? 3 : 4)]
= 	T ? T : 4
= 	T (which is 1?)

allowing nesting in bool expression part
(T ? T : F) ? (T ? 1 : 2) : (F ? 3 : 4)
= 	T ? 1 : 4
= 	1
 */
public class TernaryExpressionParser {
	public static void main(String[] args) {
		String[] test = {
				"T ? T : F ? T ? 1 : 2 : F ? 3 : 4",
				"F ? T : F"
		};
		for (String s: test) {
			System.out.println(eval(s));
		}
	}
	
	// does not allow nesting in bool expression
	// 先用string做，稍后有时间再发展一个状态机版本
	public static String eval0(String s) {
		s = s.trim();
		int colonIdx = s.indexOf(':');
		if (colonIdx < 0) return s;
		else return s.charAt(0) == 'T' ? eval0(s.substring(s.indexOf('?') + 1, colonIdx)) : eval0(s.substring(colonIdx, s.length()));
	}
	
	// does not allow nesting in bool expression
	public static String eval(String s) {
		if (s == null || s.isEmpty()) return "";
		
		// Delete all blanks
		StringBuilder sb = new StringBuilder();
		for (int i = 0, len = s.length(); i < len; ++i) {
			char ch = s.charAt(i);
			if (!Character.isWhitespace(ch) && !Character.isSpaceChar(ch)) sb.append(ch);
		}
		s = sb.toString();
		return evalHelper(s, 0, s.length());
	}
	
	private static String evalHelper(String s, int start, int end) {
		if (end - start < 5) return s.substring(start, end);
		int colonIdx = s.indexOf(':', start);
		if (colonIdx < 0) return s.substring(start, end);
		else return s.charAt(start) == 'T' ? evalHelper(s, 2, colonIdx) : evalHelper(s, colonIdx+1, end);
	}
}
