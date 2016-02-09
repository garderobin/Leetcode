package algorithm;

import java.util.HashMap;

public class WordPattern {
	public boolean wordPattern(String pattern, String str) {
		if (pattern == null && str == null) {
			return true;
		}
		if (pattern == null || str == null) {
			return false;
		}
        String[] splits = str.split(" ");
        if (splits.length != pattern.length()) {
        	return false;
        }
        int n = splits.length;
        char key;
        HashMap<Character, String> map = new HashMap<Character, String>();
        for(int i = 0; i < n; i++) {
        	key = pattern.charAt(i);
        	if (map.containsKey(key)) {
        		if (!map.get(key).equals(splits[i])) {
        			return false;
        		}
        	} else if (map.containsValue(splits[i])) {
        		return false;
        	} else {
        		map.put(key, splits[i]);
        	}
        }
        return true;
        
    }
}
