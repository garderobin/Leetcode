package algorithm;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dataStructure.Trie;

public class SubstringWithConcatenationOfAllWords {
	/**
	 * Sliding window, O(KN) time complexity.
	 */
	public List<Integer> findSubstringDiscussion(String S, String[] L) {
        List<Integer> res = new LinkedList<>();
        if (L.length == 0 || S.length() < L.length * L[0].length())   return res;
        Map<String, Integer> map = new HashMap<>(), curMap = new HashMap<>();
        for (String s : L) {
        	map.put(s, (map.containsKey(s)) ? map.get(s) + 1 : 1);
        }
        int N = S.length(), M = L.length, K = L[0].length();   
        String str = null, tmp = null;
        
        for (int i = 0; i < K; i++) { // 充分利用每个单词等长这个条件，超过K的位置会被前面的计算cover到的。
            int count = 0;  // remark: reset count 
            for (int l = i, r = i; r + K <= N; r += K) {
                str = S.substring(r, r + K);
                
                if (map.containsKey(str)) {
                	curMap.put(str, (curMap.containsKey(str)) ? curMap.get(str) + 1 : 1);
                    if (curMap.get(str) <= map.get(str)) { count++; }
                    
                    // 发现了因为单词过量而不符合条件的序列，把探索的脚步往前走，中途遇到字典中的单词也需要抹去
                    while (curMap.get(str) > map.get(str)) {
                        tmp = S.substring(l, l + K);
                        curMap.put(tmp, curMap.get(tmp) - 1); 
                        l += K;
                        if (curMap.get(tmp) < map.get(tmp)) { count--; } 
                    }
                    if (count == M) {
                        res.add(l);
                        tmp = S.substring(l, l + K);
                        curMap.put(tmp, curMap.get(tmp) - 1);
                        l += K;
                        count--;
                    }            
                }else {
                    curMap.clear();
                    count = 0;
                    l = r + K;
                }
                
            }
            
            curMap.clear();
        }
        return res;
    }
	
	/**
	 * 此方法超时。
	 * @param s
	 * @param words
	 * @return
	 */
	public static List<Integer> findSubstringV2(String s, String[] words) {
		List<Integer> rst = new ArrayList<Integer>();
		if (s == null || words == null || words.length == 0) { return rst; }
        int step = words[0].length(), len = s.length(), rest = words.length, seqLen = step * rest, j, limit = len + 1 - seqLen + step;
        if (len < seqLen) { return rst; }
        HashMap<String, Integer> map = new HashMap<>(rest), initialMap = new HashMap<>(rest), subMap = new HashMap<>(rest);
        for (String word: words) {
        	initialMap.put(word, (initialMap.containsKey(word) ? initialMap.get(word) + 1 : 1));
        }
        Deque<String> subQueue = new LinkedList<String>();
        outer:
        for (int i = step; i < limit; i++) {        	
        	String head = s.substring(i-step, i);
        	if (!map.containsKey(head)) {         		
        		continue;
        	} else {
        		subQueue = new LinkedList<String>();
        		subMap = initialMap;
        		subQueue.add(head);
        		subMap.put(head, subMap.get(head)-1);
        		for (j = i + step; j < i + seqLen; j += step) {
        			String cur = s.substring(j-step, j);
        			if (!subMap.containsKey(cur) || subMap.get(cur) == 0) { continue outer; } 
        		}
        		rst.add(i-step);
        	}
        }
        
        return rst;  
    }
	
	/**
	 * 我的想法： 一个Queue维护当前正在构造的全匹配串，一个Map维护
	 * @param s
	 * @param words
	 * @return
	 */
	public static List<Integer> findSubstring(String s, String[] words) {
		List<Integer> rst = new ArrayList<Integer>();
		if (s == null || words == null || words.length == 0) { return rst; }
        int step = words[0].length(), len = s.length(), rest = words.length, seqLen, noMatchCount = step;
        if (len < (seqLen = step * rest)) { return rst; }
        HashMap<String, Integer> map = new HashMap<>(rest), initialMap = new HashMap<>(rest);
        for (String word: words) {
        	map.put(word, (map.containsKey(word) ? map.get(word) + 1 : 1));
        	initialMap.put(word, (initialMap.containsKey(word) ? initialMap.get(word) + 1 : 1));
        }
        Deque<String> queue = new LinkedList<String>();
        String cur,head;
        for (int i = step; i < len + 1; i++) {
        	cur = s.substring(i-step, i);
        	if (!map.containsKey(cur)) { 
        		if (noMatchCount == 0) {
            		queue.clear();
            		rest = words.length;   
            		noMatchCount = step + 1;
            		map = initialMap;
        		} else {
        			noMatchCount--;
        		}
        		continue;
        	}
        	noMatchCount = step + 1;
        	int m = map.get(cur);
            map.put(cur, m-1);
            queue.add(cur);
            if (m > 0) { 
            	rest--; 
            	 for (head = queue.peek(); map.get(head) < 0; head = queue.peek()) {
                 	queue.poll();
                 	map.put(head, map.get(head) + 1);            	
                 }
            }
            else { //只要一个词出现次数过多，当前在构造的整个序列就不合格
            	for (head = queue.peek(); !head.equals(cur); head = queue.peek()) {
        			queue.poll();
//        			map.put(head, initialMap.get(head));
        			map.put(head, map.get(head) + 1); 
        			rest++;
        		}
//            	for (; map.get(cur) < 0; ) {
//                 	queue.poll();
//                 	map.put(head, map.get(head) + 1);    
//                 	rest++;
//                 }
            	queue.poll();
            	map.put(cur, 0);
//            	rest--;
            }
            
           

            if (rest == 0 && i - seqLen >= 0){
                rst.add(i-seqLen);
//                for (; i + 3 < len + 1 && s.substring(i-seqLen, i-seqLen+step).equals(s.substring(i, i+step)); rst.add((i += 3) - seqLen + step)) {}
            }
        }
        
        return rst;  
    }
	
	public static void main(String[] args) {
//		String s = "aaaaaa";
//		String[] words = {"aaa","aaa"};
		String s = "ababaab";
		String[] words = {"ab","ba","ba"};
		List<Integer> rst = findSubstring(s, words);
		for (Integer e : rst) {
			System.out.print(e + ", ");
		}
	}
}
