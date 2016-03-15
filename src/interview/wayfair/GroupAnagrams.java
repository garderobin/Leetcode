package interview.wayfair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
	
	public List<List<String>> groupAnagramsDiscussion2(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Arrays.sort(strs);
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<String>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<List<String>>(map.values());
    }
	
	
	private static final int[] PRIMES = new int[]{2, 3, 5, 7, 11 ,13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 107};

	
	/**
	 * O(M * N) algorithm using hash, without sort().
	 * @param strs
	 * @return
	 */
	public static List<List<String>> groupAnagramsDiscussion(String[] strs) {
		List<List<String>> list = new ArrayList<List<String>>();
        Map<Integer, List<String>> mapString = new HashMap<>();
        //int result = -1;
        if (strs.length == 1) {
            List<String> strings = new LinkedList<String>();
            strings.add(strs[0]);
            list.add(strings);
            return list;
        }
        for (int i = 0; i < strs.length; i++){
            int mapping = 1;
            if (strs[i].length() == 0) {
                mapping = 0;
            } else {
                for (int j = 0, max = strs[i].length(); j < max; j++) {
                    mapping *= PRIMES[strs[i].charAt(j) - 'a'];
                }
            }
            
            List<String> strings = mapString.get(mapping);
            if (strings == null) {
                strings = new LinkedList<String>();
                mapString.put(mapping, strings);
            }
            strings.add(strs[i]);
        }
        for (List<String> mapList : mapString.values()) {       
            list.add(mapList);
        }
        if (list.size() == 0) {
            List<String> strings = new LinkedList<String>();
            strings.add("");
            list.add(strings);
        }
        return list;
	}
	
//	public static List<List<String>> groupAnagrams(String[] strs) {
//        List<List<String>> rst = new ArrayList<List<String>>();
//        Hashtable<HashSet<Character>, List<String>> pool = new Hashtable<HashSet<Character>, List<String>>(); 
//        
//        for (int i = 0; i < strs.length; i++) {
//        	Iterator iter = pool.entrySet().iterator(); 
//        	while (iter.hasNext()) { 
//        	    Map.Entry entry = (Map.Entry) iter.next(); 
//        	    HashSet<Character> key = (HashSet<Character>)entry.getKey(); 
//        	    List<String> val = (List<String>)entry.getValue();
//        	    if (isAnagram(key, strs[i])) {
//        	    	val = pubAnagramInsertSort(val, strs[i]);
//        	    	pool.put(key, val);
//        	    }
//        	} 
//        }
//        Iterator iter2 = pool.entrySet().iterator(); 
//    	while (iter2.hasNext()) { 
//    	    Map.Entry entry = (Map.Entry) iter2.next(); 
//    	    List<String> val = (List<String>)entry.getValue();
//    	    rst.add(val);
//    	} 
//        
//        
//        return rst;
//    }
	
//	private static boolean isAnagram(HashSet<Character> set, String word) {
//		if (word.length() != set.size()) {
//			return false;
//		}
//		int len = word.length();
//		for (int i = 0; i < len; i++) {
//			if (set.add(word.charAt(i))) {
//				return false;
//			}
//		}
//		return true;
//	}
//	
//	private static List<String> pubAnagramInsertSort(List<String> val, String s) {
//		int len = val.size();
//		for (int i = 0; i < len; i++) {
//			if (!compareTo(val.get(i), s)) {
//				val.add(i, s);
//				break;
//			}
//		}
//		return val;
//	}
	
//	/**
//	 * 
//	 * @param s1
//	 * @param s2
//	 * @return true if s1 should be in front of s2.
//	 */
//	private static boolean compareTo(String s1, String s2) {
//		int i, len = s1.length();
//		for (i = 0; i < len; i++) {
//			if (s1.charAt(i) == s2.charAt(i)) {
//				continue;
//			} 
//		}
//		return s1.charAt(i) <= s2.charAt(i);
//	}
//	
	
}
