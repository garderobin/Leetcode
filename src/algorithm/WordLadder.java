package algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
	
	public static int ladderLengthDiscussion2(String beginWord, String endWord, Set<String> wordDict) {
		// Use the queue to help BFS
		Queue<String> queue = new LinkedList<String>();
		queue.add(beginWord);
		queue.add(null); // to size the level; instead of using 2 queues.
		
		// Mark visited word
		Set<String> visited = new HashSet<String>();
		visited.add(beginWord);
		int level = 1;
		while (!queue.isEmpty()) {
			String str = queue.poll();
			
			if (str != null) {
				// Modify str's each character (so word distance is 1)
				for (int i = 0; i < str.length(); i++) {
					char[] chars = str.toCharArray();
					
					for (char c = 'a' ; c <= 'z'; c++) {
						chars[i] = c;
						String word = new String(chars);
						
						//Found the end word
						if (word.equals(endWord)) {
							return level + 1;
						}
						
						// Put it to the queue
						if (wordDict.contains(word) && !visited.contains(word)) {
							queue.add(word);
							visited.add(word);
						}
					}
				}
			} else {
				level++;
				
				if (!queue.isEmpty()) {
					queue.add(null);
				}
			}
		}
		return 0;
	}
	
	public static int ladderLengthDiscussion1(String start, String end, Set<String> dict) {
	    LinkedList<String> queue = new LinkedList<String>();
	    queue.add(start);
	    dict.add(end);
	    int step = 0;
	    while (!queue.isEmpty()) {
	        LinkedList<String> level = new LinkedList<String>();
	        step++;
	        while (!queue.isEmpty()) {
	            String q = queue.pollFirst();
	            if (q.equals(end))
	                return step;
	            for (int i = 0; i < start.length(); i++) {
	                for (char c = 'a'; c <= 'z'; c++) {
	                    String s = q.substring(0, i) + c + q.substring(i + 1, start.length());
	                    if (dict.contains(s)) {
	                        level.add(s);
	                        dict.remove(s);
	                    }
	                }
	            }
	        }
	        queue = level;
	    }
	    return 0;
	}

	
	private static final int ZPLUS = (int)'z' + 1;
	private static final int NUM_A = (int)'a';
	
	@SuppressWarnings("rawtypes")
	public static int ladderLength(String beginWord, String endWord, Set<String> wordDict) {		
		if (beginWord == null  && endWord == null) {
			return 0;
		}
		if (beginWord == null || endWord == null || beginWord.length() != endWord.length()) {
			return -1;
		}
		if (beginWord.equals(endWord)) {
			return 0;
		}
		wordDict.add(endWord);
//		int wordLen = beginWord.length();
		int curLen = 1;
		HashMap<String, Integer> curLayer = changeOneChar(beginWord, wordDict, -1);
//		nextLayer = new HashMap<String, Integer>();
		
		//for (int i = -1; i < wordLen; i++) {
		while (curLen < 100) {
			if (curLayer.containsKey(endWord)) {
				return curLen;
			}
			Iterator iter = curLayer.entrySet().iterator(); 
			while (iter.hasNext()) { 
				Map.Entry entry = (Map.Entry) iter.next(); 
				String curWord = (String)entry.getKey(); 
				int unchangeIndex = (int)entry.getValue(); 
				curLayer.putAll(changeOneChar(curWord, wordDict, unchangeIndex));     //这一步似乎可以优化
			} 								
			curLen++;
		}
		
		return curLen;
    }
	
	/**
	 * String and its unchangeIndex in the next round.
	 * @param curWord
	 * @param wordDict
	 * @param unchangeIndex
	 * @return
	 */
	public static HashMap<String, Integer> changeOneChar(String curWord, Set<String> wordDict, int unchangeIndex) {
		HashMap<String, Integer> rst = new HashMap<String, Integer>();
		int wl = curWord.length();
		int i, j;
		//char c, d;
		String s;
		char[] ch = curWord.toCharArray();
		for (i = 0; i < wl; i++) {
			if (i == unchangeIndex) {
				continue;
			}
			for (j = NUM_A; j < ZPLUS; j++) {
				ch[i] = (char)j;
				s = new String(ch);
				if (wordDict.contains(s)) {				
					rst.put(s, i);
				}				
			}			
		}
		return rst;
	}
	
//	public static boolean containsEndWord(ArrayList<String> curLayer, String endWord) {
//		for (String e : curLayer) {
//			if ()
//		}
//	}
	
	public static void main(String[] args) {
		Set<String> wordDict = new HashSet<String>();
		wordDict.add("hot");
		wordDict.add("dot");
		wordDict.add("dog");
		wordDict.add("lot");
		wordDict.add("log");
		String start = "hit";
		String end = "cog";
		System.out.println(ladderLength(start, end, wordDict));
	}
	
	
}
