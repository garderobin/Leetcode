package interview.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupShiftedStrings {
	public static List<List<String>> groupStrings(String[] strings) {
		List<List<String>> rst = new ArrayList<List<String>>();
		if (strings == null || strings.length == 0) { return rst; }
		Arrays.sort(strings); //先排序，省得后面麻烦。
		HashMap<String, Integer> groupOrder = new HashMap<>();
		int orderCounter = 0;
		for (String s: strings) {
			if (s == null) { continue; }
			String pattern = getShiftingPattern(s);
			if (groupOrder.containsKey(pattern)) {
				rst.get(groupOrder.get(pattern)).add(s); //这样直接修改列表嵌套是没有问题的
			} else {
				List<String> group = new ArrayList<String>();
				group.add(s);
				groupOrder.put(pattern, orderCounter++);
				rst.add(group);
			}
		}
		return rst;
    }
	
	/**
	 * 做成首字母是a的移动结果，后面的每一位
	 * 26个字母之间不是一个单向链表，而是一个圆环，比如z既可以由a+25,也可以由a-1组成，
	 * 这里我规定，首字母之后的每一位与首字母的关系都取+，而不取减。
	 * @param s must be not null
	 * @return
	 */
	private static String getShiftingPattern(String s) {
		if (s.length() == 0) { return s; }		
		int shift = s.charAt(0) - 'a', len = s.length();
		StringBuilder sb = new StringBuilder(len);
		sb.append('a');
		for (int i = 1; i < len; i++) {
			sb.append((char)((s.charAt(i) - shift + 26) % 26));
		}
		return sb.toString();
	}
	
	public static void main(String args[]) {
//		System.out.println((-10+26)%26);
		String[] test = {"az", "yx"};
		List<List<String>> rst = groupStrings(test);
		for (List<String> group: rst) {
			System.out.print("[");
			for (String s: group) {
				System.out.print(s + ",\t");
			}
			System.out.println("]");
		}
		
//		String[] my = [["fpbnsbrkbcyzdmmmoisaa","rbnzendwnoklpyyyauemm"],["cpjtwqcdwbldwwrryuclcngw","huoybvhibgqibbwwdzhqhslb"],["a","l","i","d"],["fnuqwejouqzrif","aiplrzejplumda"],["js","nw"],["qcpr","eqdf"],["zghmdiaqmfelr","yfglchzpledkq"],["iedda","zvuur"],["dgwlvcyubde","ilbqahdzgij"],["lpt","txb"],["qzq","sbs"],["zkddvitlk","lwpphufxw"],["xbogegswmad","gkxpnpbfvjm"],["mkndeyrh","wuxnoibr"],["llofdjckor","kknecibjnq"],["lebzshcb","ohecvkfe"],["firomjjlidqpsdeqyn","oraxvssurmzybmnzhw"],["dclpiqbypjpfafukqmjnjg","yxgkdlwtkekavapflheieb"],["lbpabjpcmkyivbtgdwhzlxa","wmalmuanxvjtgmerohskwil"]]
	}
	
	/**
	 * s1 and s2 has same length.
	 * @param s1
	 * @param s2
	 * @return
	 */
//	private static boolean sharesShiftingPattern(String s1, String s2) {
//		for (int i = 1, len = s1.length(), delta = s1.charAt(0) - s2.charAt(0) ; i < len; i++) {
//			if (s1.charAt(i) - s2.charAt(i) != delta) { return false; }
//		}
//		return true;
//	}
}
