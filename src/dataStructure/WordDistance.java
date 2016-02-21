package dataStructure;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class WordDistance {
	Hashtable<String, List<Integer>>map;
	
	public WordDistance(String[] words) {
        map = new Hashtable<>();
        for (int i = 0; i < words.length; i++) {
        	if (map.containsKey(words[i])) {
        		map.get(words[i]).add(i);
        	} else {
        		List<Integer> list = new ArrayList<Integer>();
        		map.put(words[i], list);
        	}
        	
        }
    }

    public int shortest(String word1, String word2) {
    	List<Integer> list1 = map.get(word1), list2 = map.get(word2);
    	int rst = Integer.MAX_VALUE;
    	for (int i = 0, j = 0, len1 = list1.size(), len2 = list2.size(); i < len1 && j < len2; ) {
    		int p1 = list1.get(i), p2 = list2.get(j), dis = Math.abs(p1 - p2);
    		if (p1 == p2) { return 0; }
    		else if (p1 < p2) {
    			rst = Math.min(dis, rst);
    			i++;
    		} else {
    			rst = Math.min(dis, rst);
    			j++;
    		}
    	}
        return rst;
    }
}
