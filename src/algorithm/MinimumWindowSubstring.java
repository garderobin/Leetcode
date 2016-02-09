package algorithm;

import java.util.HashMap;
import java.util.LinkedList;

public class MinimumWindowSubstring {
	
	/**
	 * 还没有明白！需要再看！
	 * @param s
	 * @param t
	 * @return
	 */
	public String minWindowDiscussion(String s, String t) {
        if (s.length()==0 || t.length()==0 || s.length() < t.length()) return "";
        int left = t.length(), start = -1, end = s.length();
        LinkedList<Integer> queue= new LinkedList<Integer>();
        HashMap<Character,Integer> map= new HashMap<Character,Integer>();

        for (int i = 0; i < t.length(); i++){
            char c = t.charAt(i);
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
        }

        for (int i = 0; i < s.length(); i++){
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
//        int[] f = new int[tLen]; //f[i][count] 存储 t
//    }
}
