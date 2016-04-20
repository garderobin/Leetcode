package algorithm;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class MinimumWindowSubstring {
	
	
	
	/**
	 * Deque + HashMap, O(N) time, O(N) space.
	 */
	public String minWindowDiscussion(String s, String t) {
        if (s == null || t == null || s.isEmpty() || t.isEmpty() || s.length() < t.length()) return "";
        
        int left = t.length(), start = -1, end = s.length(), sLen = s.length(), tLen = t.length();
        Deque<Integer> queue= new ArrayDeque<Integer>();
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();

        for (int i = 0; i < tLen; ++i) {
            char c = t.charAt(i);
            map.put(c, 1 + map.getOrDefault(c, 0));
        }

        for (int i = 0; i < sLen; ++i){
            char c = s.charAt(i);
            if (!map.containsKey(c)) { continue; }

            int n = map.get(c);
            map.put(c, n-1);
            queue.add(i);
            if (n > 0) { left--; }

            char head = s.charAt(queue.peek());
            while (map.get(head)<0){
                queue.poll();
                map.put(head, map.get(head) + 1);
                head = s.charAt(queue.peek());
            }

            if (left == 0){
                int new_length = queue.peekLast() - queue.peek() + 1;
                if (new_length < end - start) {
                    start = queue.peek();
                    end = queue.peekLast() + 1;
                } 
            }
        }
        if (left == 0)  return s.substring(start,end);
        else return "";
    }
	
//	public String minWindow(String s, String t) {
//        if (s == null || s.length() == 0 || t == null || t.length() == 0) { return  ""; }
//        int tLen = t.length(), i;
//        
//        // Processing t considering inner duplicates.
//        HashMap<Character, Integer> tmap = new HashMap<>();
//        char c;
//        for (i = 0; i < tLen; i++) {
//        	c = t.charAt(i);
//        	tmap.put(c, (tmap.containsKey(c)) ? tmap.get(c) + 1 : 1);
//        }
//        
//        int[] f = new int[tLen]; //f[i][size] 存储 t
//    }
}
