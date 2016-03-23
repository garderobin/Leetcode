package interview.snapchat;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import dataStructure.Trie;
import dataStructure.TrieNode;

public class SingleWord {
// 一开始用leetcode的dp方法解决wordbreak问题，发现最后一个test case过不了。 
// 后来不用dp方法，删掉了一些不必要的情况，就按找到一个单词继续往后找的方法，就过了最后一个test case，希望对大家有帮助。
	
//	输出给的数组里面的非compound words, 要求保持输出顺序是原数组顺序。
//	思路地里都讨论的很清楚了，排序+hashset+Word break, test case都能过的，不用自己写输入输出。
	
	public static void main(String[] args) {
//		String[] words2 = {
//				"aaaaabababaaabbbababbbaaab",
//				"ababbabaa",
//				"baaaabbbbabbaabbbaababba",
//				"baabaaabaaaaabaaabaaaabbbabbb",
//				"aabbb",
//				"ababbbaaabaaaaabbbbabaababbbbbabb",
//				"bbabbbab",
//				"baaa",
//				"aabba",
//				"aaaaaabaaa",
//				"aabaabbbbabbbaabaaaaaaaaaaaa",
//				"aaaba",
//				"aaababbbabbabaa",
//				"aaabb",
//				"bbabb",
//				"bbabaababaabb",
//				"bbbbaabaabaaa",
//				"bbaaaaaaa",
//				"baab",
//				"ababbbabaaaaaa",
//				"aabbabbbabbbbabbaaaabaaaba",
//				"baaabbbbb",
//				"babaababbbabb",
//				"bbab",
//				"abbbba",
//				"bbaa",
//				"bbbabbaaaabbbbabbaaabababb",
//				"abbbbbabaa",
//				"abbabbaaab",
//				"babbaabbaaabbbaaaababaaabaaabbba",
//				"aaaaaabbbababaaabbb",
//				"abbaaaaab",
//				"baaabbbbbaabbbaabaaabbbaaaab",
//				"abbaaaaabaaabbbaaa",
//				"bbaabb",
//				"bbbabbaaababaabaabaaababbaabbaa",
//				"ababbba",
//				"aaabaaaaa",
//				"baaaabbbbbaabbbab",
//				"aababbababaabbbaab",
//				"baabb",
//				"ababaabababbaabbaa",
//				"abbbbababaabbaaaabbb",
//				"aaaaa",
//				"baaaabb",
//				"baaaaba",
//				"abbbaab",
//				"abbaababb",
//				"bbababaababaaabababbba",
//				"aaabaaaabaabbbbbbaaaaaaa",
//				"ababb",
//				"aaab",
//				"aaaa",
//				"aaaaaababbbaaaababbaababb",
//				"abaa",
//				"bb",
//				"baaabbababaabbbabaaaababaababaab",
//				"bbababa",
//				"aaabaabbabbbbaaaaabbb",
//				"aabbaabbbabbaababbaabbb",
//				"baaabbbbbaabaa",
//				"abaaaaaba",
//				"abaab",
//				"abbb",
//				"bbaaabaa",
//				"bbabaab",
//				"bbbbbbbaaa",
//				"abbbaabbbbbab",
//				"aabbabbbab",
//				"abbabbbaabaabb",
//				"bbbbbaaaabbbabaaaabbabbbababbab",
//				"aaabaa",
//				"bbababaab",
//				"baaaabbbb",
//				"abbbaabbbabbbababaabaaabaabbababaab",
//				"baaaababa",
//				"aabaabbbb",
//				"ba",
//				"baaaabbbbbbababbabaa",
//				"abbbaaaab",
//				"abbbaababbbbaaababbabaaababbbaaab",
//				"aababba",
//				"baaaabbbabaaaaababbababaabba",
//				"baabbbabbababaaa",
//				"aaaaabbabbaaab",
//				"aa",
//				"ab",
//				"abbbaaaabbababaaaaabbabbbabaaaaaaaaaaab",
//				"abbabbbbabba",
//				"aba",
//				"abb",
//				"baaabbbbbaaaaaaabaabbbaab",
//				"bbbabaababbababbaaaababaab",
//				"bba",
//				"bbb",
//				"baaaabbbbaabbababba",
//				"bbaabbaabb",
//				"bbbbaabbabbbabaababbabaabaa",
//				"aab",
//				"bbaabbbba",
//				"bbbabbaaaaaaababaababbababaaabbababaab",
//				"aaabaaaaabaaaabaabbab",
//				"baabbbab",
//				"baaaba",
//				"babbaaabab",
//				"abbabbabbaaabababbbabbababaab",
//				"baa",
//				"abaabbbaab",
//				"bab",
//				"baaaabbbbbaabaaaabbaaabbbbbbbbaaa",
//				"abbbaababbabbbab",
//				"aabaabaaabbbabba",
//				"baababaab",
//				"aabaabaaa",
//				"aaaaaab",
//				"abbabaabaabbbbaaabbabbbaab",
//				"abbaababbababbbabbaaabaaabbbbb",
//				"aababbabaa",
//				"abaaaaabaabaaaabaabbbbbbaabbbbbabaa",
//				"ababaaaabbb",
//				"babbaaaabbb",
//				"abaabaaababbababaab",
//				"aabaabbbbaabaaaabbbbaaaa",
//				"abbbbabab",
//				"abbababba",
//				"aaaaaaaaaabba",
//				"aababbbaababba",
//				"abaaab",
//				"aababbaabbabbaaabaaaaaaababbabaaaa",
//				"ababbaabbabbbabaabbabbbabbaaaaa",
//				"ababaaba",
//				"aaabaabababaaa",
//				"baaaabbb",
//				"aaaaaa",
//				"bbababaaaab",
//				"baaaabbbaabbabbaabbaaabbaaaaab",
//				"ababbbaabbbaabbaaabaaaaab",
//				"abbab",
//				"aaaaaaaaaa",
//				"aabaabbbbabbaaaaaabaaabbbbb",
//				"aabaa",
//				"abbbaaabbaaaabbbbbab",
//				"ababaabaababbabbabbabaa",
//				"abbbababbbabbbababa",
//				"abaabbb",
//				"aaabbbabaa",
//				"baabbababbbabaab",
//				"bbbabaababbbbaaabaabbbbbbbaaab",
//				"abbba",
//				"babbaabbaa",
//				"bbaabaabb",
//				"babaabab",
//				"ababbabb",
//				"bbbabba",
//				"babaabba",
//				"aaaaaaaaaaabbabbbab",
//				"bbbaaabbabbaaaaababbabaaabbbabaa",
//				"bbaabbaa",
//				"babbb",
//				"bbaaaaabaaaaaaaabbbabaa",
//				"bbbaaab",
//				"aaababbb",
//				"ababbbab",
//				"bbabbbabaabaabbbb",
//				"babbbabbababaababbb",
//				"aabaaababbbabaabaabaa",
//				"baabbaababbaaababaaabbbababa",
//				"babaab",
//				"bababaaa",
//				"abbaaaaaabbaaba",
//				"bbabbabaa",
//				"bbbbba",
//				"aabbbabaababbabbaaab",
//				"ababbbaaab",
//				"ababbbabababbaabbababaababbb",
//				"abaabbaababbaaabb",
//				"aabbabbbabbaabbbabbabaabbbababa",
//				"aababbabababaaabbbaaababbab",
//				"babaababbb",
//				"aaababbbaaaabbbaaabbbaab",
//				"aaababbbbaabbbab",
//
//		};
//		String[] words3 = {
//				"aaaaaaaaaaaaaaaaa",
//				"aaaaaaaaaaaaaaaa",
//				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
//				"a",
//				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
//				"aaaaa",
//				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
//				"aaaa",
//				"aaaaaaaaaaaaaa",
//				"aaaaaaaaaaaaaaaaaaaaa",
//				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
//				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
//				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
//				"aaaaaaaaaaaaaaaaaaaa",
//				"aaaaaaaa",
//				"aaaaaaaaaaaaaaaaaa",
//				"aa",
//				"aaaaaaaaaaaaaaaaaaaaaaa",
//				"aaa",
//				"aaaaaaaaaaa",
//				"aaaaaaa",
//				"aaaaaaaaaaaaaaa",
//				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
//				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
//				"aaaaaa",
//				"aaaaaaaaaaaaaaaaaaaaaaaaaaaa",
//				"aaaaaaaaaa",
//				"aaaaaaaaaaaaa",
//				"aaaaaaaaa",
//				"aaaaaaaaaaaaaaaaaaaaaaaaa",
//				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
//				"aaaaaaaaaaaaaaaaaaaaaa",
//				"aaaaaaaaaaaa",
//				"aaaaaaaaaaaaaaaaaaaaaaaaaaa",
//				"aaaaaaaaaaaaaaaaaaa",
//				"aaaaaaaaaaaaaaaaaaaaaaaaaa",
//				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
//
//		};
//		
		String[] words4 = {
				"a",
				"b",
				"abb",
				"ab"
		};
//		Arrays.sort(words3);
//		for (String s: words3) {
//			System.out.println(s);
//		}
		String[] simpleWords = simpleWords(words4);
		System.out.println("\nrst: ");
		for (String s: simpleWords) System.out.println(s);
		
	}
	
//	/*
//	 * 最后一个case过不了
//	 */
//	static String[] simpleWordsKangranV1(String[] words) {
//        Set<String> dict = new HashSet<>();
//        List<String> list = new LinkedList<>();
//        for(String word : words) { dict.add(word); }
////        for(String word : words) { if(!wordBreak(word, dict)) { list.add(word); } }
//        for(String word : words) { if(!wordBreakBFS(word, dict)) { list.add(word); } }
//        String[] strings = new String[list.size()];
//        int counter = 0;
//        for(String s : list) { strings[counter++] = s; }
//        return strings;
//    }

	/*
	 * DP
	 */
    static boolean wordBreak(String s, Set<String> wordDict) {
        wordDict.remove(s);
        int n = s.length();
        boolean[] canBreak = new boolean[1 + n];
        canBreak[n] = true;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j <= n; ++j) {
                if (canBreak[j] && wordDict.contains(s.substring(i, j))) {
                    canBreak[i] = true;
                    break;
                }
            }
        }
        wordDict.add(s);
        return canBreak[0];
    }
    
    static TrieNode root;
    public static String[] singleWordV2(String[] words) {
//		Map<String, Integer> posMap = new HashMap<>();
//		for(int i = 0; i < words.length; ++i) { posMap.put(words[i], i); }
//		
//		Arrays.sort(words);
//		
//		Set<String> dict = new HashSet<>();
        List<String> list = new ArrayList<>();
//        for(String word : words) { dict.add(word); list.add(word); }
//        for(String word : words) { if(!wordBreak(word, dict)) { list.add(word); } }
    	Trie trie = new Trie();
    	for (String word : words) {
            trie.insert(word);
        }
    	root = trie.getRoot();
    	for (String word: words) {
    		if (dfsCompound(root, word, 0, false)) {
    			list.add(word);
    		}
    	}
    	
    	return list.toArray(new String[list.size()]);
	}
	
    
    private static boolean dfsCompound(TrieNode node, String word, int index, boolean isRepeat) {
	    int cPos = word.charAt(index)-'a';
	    if (node.son[cPos] == null) return false;
	    
	    if (index == word.length()-1) {
	            if (node.son[cPos].isEnd) return isRepeat;
	            else return false;
	    }
	
	//    if (node.son[cPos].isEnd && dfsCompound(root, word, index+1, true)) {
	    if (node.son[cPos].isEnd && dfsCompound(root, word, index+1, true)) {
	            return true;
	    }
	    
	    return dfsCompound(node.son[cPos], word, index+1, isRepeat);        
    }
    
    public static String[] singleWordV3(String[] words) {
    	if (words.length == 1) { return words; }
		Map<String, Integer> posMap = new HashMap<>();
		Map<String, Boolean> visited = new HashMap<>();
		for(int i = 0; i < words.length; ++i) { 
			posMap.put(words[i], i); 
//			visited.put(words[i], false);
		}
		
		Arrays.sort(words, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.equals(o2)) return 0;
				else if (o1.length() == o2.length()) return o1.compareTo(o2);
				else return o1.length() - o2.length();
			}
			
		});
		
		
		
		int minSumLen = words[0].length() + words[1].length();
		List<String> dict0 = new ArrayList<>();
        List<String> list = new ArrayList<>();
//        for(String word : words) { dict.add(word); list.add(word); }
//        for(String word : words) { if(!wordBreak(word, dict)) { list.add(word); } }
        
    	for (int i = 0; i < words.length; ++i) {
    		String word = words[i];
    		dict0.add(word);
    		visited.put(word, false);
    		if (dict0.isEmpty() || word.length() < minSumLen) {
    			list.add(word);
    		} else {
    			if (!isCompound(word, dict0, visited, 0, false)) {
    				list.add(word);
    			}
    		}
    		
        }
    	Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return posMap.get(o1) - posMap.get(o2);
			}
    	});
    	
    	return list.toArray(new String[list.size()]);
	}
    
    private static boolean isCompound(String word, List<String> dict0, Map<String, Boolean> visited, int start, boolean isRepeat) {
//    	System.out.println("isCompound: " + word + ", \t" + start);
    	if (dict0.isEmpty()) 
    		return start == word.length();
    	if (start == word.length()) 
    		return isRepeat;
    	for (String prev: dict0) {
    		if (visited.get(prev)) continue;
    		int j = 0;
    		for (; j+start < word.length() && j < prev.length() && prev.charAt(j) == word.charAt(j+start); ++j) {}
    		if (j == prev.length()) {
    			visited.put(prev, true);
    			boolean compound = isCompound(word, dict0, visited, j+start, true);
    			visited.put(prev, false);
    			if (compound) 
    				return true;
    		} else continue;
    	}
    	return false;
    }
    
//    public static String[] singleWordV5(String[] words) {
//    	if (words.length == 1) { return words; }
//		Map<String, Integer> posMap = new HashMap<>();
//		for (int i = 0; i < words.length; ++i) posMap.put(words[i], i); 
//		
//		// Sort words by length
//		Arrays.sort(words, new Comparator<String>() {
//			@Override
//			public int compare(String o1, String o2) {
//				if (o1.equals(o2)) return 0;
//				else if (o1.length() == o2.length()) return o1.compareTo(o2);
//				else return o1.length() - o2.length();
//			}
//			
//		});
//		
//		// Check compounds by BFS
//		Set<String> dict = new HashSet<>();
//        List<String> simpleList = new ArrayList<>();
//    	for (int i = 0; i < words.length; ++i) {
//    		String word = words[i];
//    		if (!wordBreakBFS(word, dict)) { 
//    			simpleList.add(word); 
//    			dict.add(word);
//    		} 
//        }
//    	
//    	// Sort results by occurrence position
//    	Collections.sort(simpleList, new Comparator<String>() {
//			@Override
//			public int compare(String o1, String o2) {
//				return posMap.get(o1) - posMap.get(o2);
//			}
//    	});
//    	
//    	return simpleList.toArray(new String[simpleList.size()]);
//	}
    
    /*
     * Traverse words list from short to long and offer new words to a dictionary after a compound check progress in BFS.
     * O(N^3) time, O(N) space.
     */
    public static String[] simpleWords(String[] words) {
    	if (words.length == 1) { return words; }
		Map<String, Integer> posMap = new HashMap<>();
		for (int i = 0; i < words.length; ++i) posMap.put(words[i], i); 
		
		// Sort words by length, O(NlogN) time, O(NlogN) space
		Arrays.sort(words, (String o1, String o2) -> o1.length()-o2.length());
		
		// Check compounds by BFS, O(N^3) time, O(N) space
		List<String> dict = new ArrayList<>();
    	for (int i = 0; i < words.length; ++i) {
    		String word = words[i];
    		if (!wordBreakBFS(word, dict)) { 
    			dict.add(word); // our dictionary only stores simple words
    		} 
        }
    	
    	// Sort results by occurrence position, O(NlogN) time, O(NlogN) space
    	dict.sort((String o1, String o2) -> posMap.get(o1) - posMap.get(o2));
    	
    	return dict.toArray(new String[dict.size()]);
	}
    
    /*
     * Check word compound by BFS approach.
     * Use dynamic growing queue to limit space growth.
     * Use a set to record checked index to avoid repeated work to reduce the running time to O(N^2).
     */
    static boolean wordBreakBFS(String s, List<String> dict) {
	    Queue<Integer> queue = new ArrayDeque<Integer>();
	    queue.offer(0);
	    Set<Integer> visited = new HashSet<Integer>();
	    visited.add(0);
	    while (!queue.isEmpty()) {
	        int curIdx = queue.poll();
	        for (int i = curIdx+1; i <= s.length(); i++) {
	            if (visited.contains(i)) continue;
	            if (dict.contains(s.substring(curIdx, i))) {
	                if (i == s.length()) return true;
	                queue.offer(i);
	                visited.add(i);
	            }
	        }
	    }
	    return false;
	}
    
    
    
}
