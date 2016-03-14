package interview.others;

public class XinliHackerRank {
	
	static String isPangram(String[] strings) {		
		StringBuilder sb = new StringBuilder();
		for (String s: strings) {
			sb.append(isPangramLine(s));
		}
		return sb.toString(); 
	}
	
	static int isPangramLine(String s) {
		boolean[] check = new boolean[26];
		for (int i = 0, len = s.length(); i < len; i++) {
			char c = s.charAt(i);
			if (c == ' ') { continue; }
			check[c - 'a'] = true;
		}
		for (boolean b: check) {
			if (!b) { return 0; }
		}
		return 1;
	}
	
	public static void main(String args[]) {
		System.out.println(isPangramLine("e promptly judged antique ivory buckles for the next prize"));
	}
	
}
