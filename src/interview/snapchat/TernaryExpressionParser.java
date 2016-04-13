package interview.snapchat;

/**
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=182651&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26sortid%3D311
 * 题目： ternary expression paser （三目运算符）， input是一个string，其中 'T' 表示true， 'F' 表示false， 要求返回最后运算的结果。
 * 乍一看题目很直接， bool expression ? first result : second result ，但实际上我们通常都用的都是非常简单的形式，但每一部分都可以无限嵌套。例如：
 * T ? T : F ? T ? 1 : 2 : F ? 3 : 4; 会转化成T : 1 : 4，然后返回1
 * 原本妹子没让我考虑bool expression部分也嵌套的情况，结果我本着把问题分析清楚的原则，成功把问题弄的复杂了，她后来觉得这可以作为follow up。。。
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
	
	public static int eval0(String s) {
		return 0;
	}
	
	public static int eval(String s) {
		return 0;
	}
}
