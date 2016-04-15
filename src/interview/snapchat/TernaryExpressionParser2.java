package interview.snapchat;

import java.util.List;

//允许bool expression出现嵌套，所以可能的解析结果都要输出到list里面去
public class TernaryExpressionParser2 {
	public static void main(String[] args) {
		String[] test = {
				"T ? T : F ? T ? 1 : 2 : F ? 3 : 4",
				"F ? T : F"
		};
		for (String s: test) {
			System.out.println(eval(s));
		}
	}

	public static List<String> eval(String s) {
		// TODO Auto-generated method stub
		return null;
	}
}
