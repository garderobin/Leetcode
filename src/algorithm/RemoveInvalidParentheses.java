package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParentheses {
	
	public List<String> removeInvalidParenthesesDiscussion(String s) {
	    Set<String> res = new HashSet<>();
	    int rmL = 0, rmR = 0;
	    for(int i = 0; i < s.length(); i++) {
	        if(s.charAt(i) == '(') rmL++;
	        if(s.charAt(i) == ')') {
	            if(rmL != 0) rmL--;
	            else rmR++;
	        }
	    }
	    DFS(res, s, 0, rmL, rmR, 0, new StringBuilder());
	    return new ArrayList<String>(res);  
	}

	public void DFS(Set<String> res, String s, int i, int rmL, int rmR, int open, StringBuilder sb) {
	    if(i == s.length() && rmL == 0 && rmR == 0 && open == 0) {
	        res.add(sb.toString());
	        return;
	    }
	    if(i == s.length() || rmL < 0 || rmR < 0 || open < 0) return;

	    char c = s.charAt(i);
	    int len = sb.length();

	    if(c == '(') {
	        DFS(res, s, i + 1, rmL - 1, rmR, open, sb);
	        DFS(res, s, i + 1, rmL, rmR, open + 1, sb.append(c)); 

	    } else if(c == ')') {
	        DFS(res, s, i + 1, rmL, rmR - 1, open, sb);
	        DFS(res, s, i + 1, rmL, rmR, open - 1, sb.append(c));

	    } else {
	        DFS(res, s, i + 1, rmL, rmR, open, sb.append(c)); 
	    }

	    sb.setLength(len);
	}
	
	static int[] tol ; // left-side trailing open containing the current index
	static int[] aol ; // left-side accumulate open count
	static int[] acl ; // left-side accumulate close count
	static int[] tcr ; // right-side trailing close containing the current index
	static int[] acr ; // right-side accumulate close count
	static int[] aor ; // right-side accumulate open count
	static int[] bl ;  // balance counting from left;
	static int[] br ;  // balance counting from left;
	
	public static void main(String[] args) {
		String[] test = new String[20];
//		test[0] = "()(()((";
//		test[1] = "(r(()()(";
//		test[2] = "))n((i()";
//		test[3] = "x(";
//		test[4] = ")x";
//		test[5] = "(";
//		test[6] = ")(f";
//		test[7] = "))((f";
//		test[8] = "((()())p";
//		test[9] = ")(a)))())))";
//		test[10] = "p(r)";
//		test[11] = "(j))(";
//		test[12] = "a)o(v(";
//		test[13] = ")(()c))(";
//		test[14] = "())(((()m)(";
		test[15] = "()))))(((())";
		for (String t: test) {
			if (t == null) { continue; }
			System.out.println(t + ",\t\t" + removeInvalidParentheses(t));
		}
		
	}
	
	public static List<String> removeInvalidParentheses(String s) {
		if (s == null || s.length() == 0) { return Collections.singletonList(""); }
		StringBuilder sb = new StringBuilder();
		int n = s.length(), balance = 0, start, end;
		
		// remove left most ')' and right most '('s.
		left:
		for (start = 0; start < n; ++start) {
			switch(s.charAt(start)) {
			case ')': continue left;
			case '(': break left;
			default: sb.append(s.charAt(start));
			}
		}
		String prefix = sb.toString();
		sb = new StringBuilder();
		
		right:
		for (end = n; end > start; --end) {
			switch(s.charAt(end-1)) {
			case '(': continue right;
			case ')': break right;
			default: sb.insert(0, s.charAt(end-1));
			}
		}
		String postfix = sb.toString();
//		System.out.println(start + ", " + end + ", pre=" + prefix + ",\tpost=" + postfix);
		if (start > end) { return Collections.singletonList(postfix); }
		else if (start == end) { return Collections.singletonList(prefix + postfix); } 
		
		// initialization.
		tol = new int[n+1];
		aol = new int[n+1];
		acl = new int[n+1];
		tcr = new int[n+1];
		acr = new int[n+1];
		aor = new int[n+1];
		bl = new int[n+1];
		br = new int[n+1];
				
		for (int i = start, a = 0, t = 0, c = 0, b = 0; i < end; ++i) {
			if (s.charAt(i) == '(') { ++a; ++t; ++b;}
			else { t = 0; }
			if (s.charAt(i) == ')') { ++c; --b;}
			aol[i+1] = a;
			tol[i+1] = t;
			acl[i+1] = c;
			bl[i+1] = b;
		}
		for (int i = end, a = 0, t = 0, o = 0, b = 0; i > start; --i) {
			if (s.charAt(i-1) == ')') { ++a; ++t; --b;}
			else { t = 0; }
			if (s.charAt(i-1) == '(') { ++o; ++b; }
			acr[i] = a;
			tcr[i] = t;
			aor[i] = o;
			br[i] = b;
		}
		
		
		List<String> rst = new ArrayList<String>();
		
		if (balance == 0) {
			rst.add(prefix + s.substring(start, end) + postfix); 
			return rst;
		} else {
			Set<String> set = new HashSet<String>();
			if (balance > 0) { set.addAll(openRemover(s, start, end, balance)); }
			else  			{ set.addAll(closeRemover(s, start, end, balance)); }
			if (prefix.length() > 0) { set = mergeSet(prefix, set); }
			if (postfix.length() > 0) { set = mergeSet(set, postfix); }
			rst.addAll(set);
			
			return rst;
		}
		
    }	
	
private static Set<String> closeRemover(String s, int start, int end, int balance) {
//	System.out.println("\nwhen enter, start=" + start + ",\tbalance=" + balance);
	
		Set<String> rst = new HashSet<String>();
		if (start < 0 || start >= end) { return rst; }
		if (balance == 0) { 
			rst.add(s.substring(start, end));
			return rst;
		}
		
		boolean openFound = false, opening = false;
		String validPrefix = "";
		for (int i = start; i < end && balance < 0; i++) {
			switch (s.charAt(i)) {
			case ')': 
				if (!openFound) { 
					return mergeSet(closeRemover(s, start, i, ++balance), ")"); 
				}
				else if (opening){ // from j = 0 to maxRemove, remove j opens and keep (maxRemove - j) opens to tail
					int t = tcr[i+1], a = acr[i+1], c = aor[i+1];
					int maxRemove = Math.min(0-balance, t), minRemove = 0-balance - (a-t-c);
					
					if (t == a) { 
						rst.add(addCloseToPrefix(s, i, i+a+balance, validPrefix));
						return rst; 
					}
					for (int j = Math.max(0, minRemove), begin = i+t; j <= maxRemove; ++j) { 
//						System.out.println("\nbefore call, i=" + i + ",\tj=" + j +",\tbegin=" + begin + ",\tbalance=" + balance + ",\tvp=" + validPrefix);
						
						Set<String> right = closeRemover(s, begin, end, balance+j); 
						String v = addCloseToPrefix(s, i, i+t-j, validPrefix);
						
//						System.out.println(v + " + " + right);
						
						rst.addAll(mergeSet(v, right));
					}
					return rst;
				} else { opening = false; break; }
			case '(': 
				openFound = true;
				opening = true;
				validPrefix += s.charAt(i);
				break;
			default: 
				validPrefix += s.charAt(i); break;
			}
		}
		return rst;
	}
	
	
	
	private static Set<String> openRemover(String s, int start, int end, int balance) {
		Set<String> rst = new HashSet<String>();
		if (start < 0 || start >= end) { return rst; }
		if (balance == 0) { 
			rst.add(s.substring(start, end));
			return rst;
		}
		
		boolean closeFound = false, closing = false;
		String validTail = "";
		for (int i = end; i > start && balance > 0; i--) {
			switch (s.charAt(i-1)) {
			case '(': 
				if (!closeFound) { 
					return mergeSet(openRemover(s, start, i-1, --balance), "(" + validTail); 
				}
				else if (closing){ // from j = 0 to maxRemove, remove j opens and keep (maxRemove - j) opens to tail
					int t = tol[i], a = aol[i], c = acl[i];
					int maxRemove = Math.min(balance, t), minRemove = balance - (a-t-c);
					
					if (minRemove > maxRemove) {}
					
//					System.out.println(s.substring(start, end) + ": " + "(" + start + "," + i + "), " +
//							"\tbalance=" + balance + ",\ta=" + a + ",\tt=" + t +",\tc=" + c +
//							",\tmax=" + maxRemove + ",\tmin=" + minRemove);
					if (t == a) { 
						rst.add(addOpenToTail(s, i-a+balance, i, validTail));
						return rst; 
					}
					for (int j = Math.max(0, minRemove), stop = i-t; j <= maxRemove; ++j) { 
						Set<String> left = openRemover(s, start, stop, balance-j); 
						String tail = addOpenToTail(s, i-t+j, i, validTail);
//						System.out.println(left + " + " + tail);
						rst.addAll(mergeSet(left, tail));
					}
					return rst;
				} else { closing = false; break; }
			case ')': 
				closeFound = true;
				closing = true;
				validTail = ')' + validTail; 
				break;
			default: 
				validTail = s.charAt(i-1) + validTail; break;
			}
		}
		return rst;
	}
	
	private static String addOpenToTail(String s, int start, int end, String sb) {
		return ((start >= end) ? "" : s.substring(start, end)) + sb;
	}
	
	private static String addCloseToPrefix(String s, int start, int end, String sb) {
//		sb += '(';
		for (int i = start; i < end; i++) {
			sb += ')';
		}
		return sb;
	}
	
	private static Set<String> mergeSet(Set<String> l1, String tail) {
		Set<String> rst = new HashSet<String>();
		if (l1.size() == 0) { 
			rst.add(tail);
			return rst;
		}
		for (String s: l1) {
			rst.add(s + tail);
		}
		return rst;
	}
	
	private static Set<String> mergeSet(String front, Set<String> l2) {
		Set<String> rst = new HashSet<String>();
		if (l2.size() == 0) { 
			rst.add(front);
			return rst;
		}
		for (String s: l2) {
			rst.add(front + s);
		}
		return rst;
	}
	

	

//	private static Set<String> mergeSet(Set<String> l1, Set<String> l2) {
//		Set<String> rst = new HashSet<String>();
//		if 		(l2.size() == 0) { return l1; }
//		else if (l1.size() == 0) { return l2; }
//		for (String s1: l1) {
//			for (String s2: l2) {
//				rst.add(s1 + s2);
//			}
//		}
//		
//		return rst;
//	}
	
	
//	private static String deleteOneIndex(String s, int start, int end, int dpos) {
//	if 		(dpos == start) { return s.substring(dpos+1, end); } 
//	else if (dpos == end-1) { return s.substring(start,  dpos); } 
//	else 					{ return s.substring(start, dpos) + s.substring(dpos+1, end); }
//}
	
//		public static Set<String> remover(String s, int start, int end, int balance) {
//			Set<String> rst = new HashSet<String>();
//			if (start == s.length()) { return rst; }
//			boolean openFound = false;
//			for (int i = start; i < end; i++) {
//				switch (s.charAt(i)) {
//				case '(': 
//					openFound = true;
//					++balance; 
//					break;
//				case ')': 
////					if (--balance < 0) { 
////						Set<String> closeBefore = closeRemover(s, start, i+1);
////						rst.addAll(mergeSet(closeBefore, remover(s, i+1, end, 0)));
////						if (openFound) { // remove a close before this, this close can be chosen to be deleted or not
////							// remove this close together with one before it 
////							rst.addAll(mergeSet(closeBefore, remover(s, i+1, end, 1)));
////						} 
////						return rst;
////					}
////					else { break; }
//				default: ;
//				}
//			}
//			if (balance == 0) { 
//				rst.add(s.substring(start, end)); 
//				return rst;
//			} else { 
////				rst.add(s.substring(start, lastBalanceIndex+1));
////				return mergeSet(rst, openRemover(s, lastBalanceIndex+1, end, balance)); }
//				return openRemover(s,start, end, balance); }
//	    
//		}	
			
		
//		private static Set<String> closeRemover(String s, int start, int end) {
//			Set<String> rst = new HashSet<String>();
//			if (start == end) { return rst; }
//			boolean closing = false;
//			for (int i = start; i < end; i++) {
//				switch (s.charAt(i)) {
//				case '(': closing = false; break;
//				case ')': 
//					if (!closing) { 
//						rst.add(s.substring(start, i) + ((i+1 < end) ? s.substring(i+1, end) : "")); 
//					}
//					closing = true; 
//					break;
//				default: closing = false;
//				}
//			}
//			return rst;
//		}
		
//		private static Set<String> closeRemoverTest(String s, int start, int end, int balance, String prefix) {
//			
//			Set<String> rst = new HashSet<String>();
//			if (start < 0 || start >= end) { return rst; }
//			if (balance == 0) { 
//				rst.add(s.substring(start, end));
//				return rst;
//			}
//			
//			boolean openFound = false, opening = false;
//			String validPrefix = "";
//			String copyPrefix = validPrefix + "";
//			for (int i = start; i < end && balance < 0; i++) {
//				switch (s.charAt(i)) {
//				case ')': 
//					if (!openFound) { 
//						String copy = validPrefix + "";
//						return mergeSet(closeRemover(s, start, i, ++balance, validPrefix), copy); 
//					}
//					else if (opening){ // from j = 0 to maxRemove, remove j opens and keep (maxRemove - j) opens to tail
//						int t = tcr[i+1], a = acr[i+1], c = aor[i+1];
//						int maxRemove = Math.min(0-balance, t), minRemove = 0-balance - (a-t-c);
//						
//						if (t == a) { 
////							System.out.println(s.substring(i, end) + ": " + "(" + i + "," + end + "), " +
////									"\tbalance=" + balance + ",\ta=" + a + ",\tt=" + t +",\tc=" + c +
////									",\tmax=" + maxRemove + ",\tmin=" + minRemove + ",\tpre=" + validPrefix);
////							String copy = validPrefix + "";
//							System.out.print("last   call, i=" + i + ",\tr=" + (i+a+balance) + ",\t\t\tvp=" + validPrefix + ",\tsub=" + s.substring(i, i+a+balance));
//							rst.add(addCloseToPrefix(s, i, i+a+balance, validPrefix));
////							validPrefix = copy;
//							System.out.print(",\trst=[");
//							for (String str: rst) { System.out.print(str + ", "); }
//							System.out.println("]");
//							return rst; 
//						}
//						for (int j = Math.max(0, minRemove), begin = i+t; j <= maxRemove; ++j) { 
//							System.out.println("\nbefore call, i=" + i + ",\tj=" + j +",\tbegin=" + begin + ",\tvp=" + validPrefix);
//							String mmm = validPrefix;
//							Set<String> right = closeRemover(s, begin, end, balance+j, validPrefix); 
//							validPrefix = mmm;
//							System.out.println("after  call, i=" + i + ",\tj=" + j + ",\ti+t-j=" + (i+t-j) + ",\tvp=" + validPrefix + ",\tsub=" + s.substring(i, i+t+j));
//							
//							prefix = copyPrefix;
//							String v = addCloseToPrefix(s, i, i+t-j, validPrefix);
//							
//							
//							System.out.print("done a call, i=" + i + ",\tj=" + j + ",\ti+t-j=" + (i+t-j) + ",\tnvp=" + v + ",\tright=[");
//							for (String str: right) {
//								System.out.print(str + ", ");
//							}
//							System.out.println();
//							rst.addAll(mergeSet(v, right));
//						}
//						return rst;
//					} else { opening = false; break; }
//				case '(': 
//					openFound = true;
//					opening = true;
////					validPrefix += '('; 
////					balance++;
//					break;
//				default: 
//					validPrefix += s.charAt(i); break;
//				}
//			}
//			return rst;
//		}
		
		
}
