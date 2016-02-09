package algorithm;

import java.util.HashMap;

public class BullsAndCows {
	
	/**
	 * 精妙之处在于把两个数组合二为一，逢情况A加，逢情况B减，
	 * 如果值为负说明A出现次数多于B，反之B出现次数多于A。
	 * 非常适合处理对等消灭的情况。
	 * @param secret
	 * @param guess
	 * @return
	 */
	public String getHintDiscussion(String secret, String guess) {
	    int bulls = 0;
	    int cows = 0;
	    int[] numbers = new int[10];
	    for (int i = 0; i<secret.length(); i++) {
	        int s = Character.getNumericValue(secret.charAt(i));
	        int g = Character.getNumericValue(guess.charAt(i));
	        if (s == g) bulls++;
	        else {
	            if (numbers[s] < 0) cows++;
	            if (numbers[g] > 0) cows++;
	            numbers[s] ++;
	            numbers[g] --;
	        }
	    }
	    return bulls + "A" + cows + "B";
	}
	
	public static String getHint(String secret, String guess) {
		int bulls = 0, cows = 0, len = secret.length();
        HashMap<Character, Integer> ms = new HashMap<>();
        HashMap<Character, Integer> mg = new HashMap<>();
        Character cs, cg;
        boolean smatch = false, gmatch = false;
        for (int i = 0; i < len; i++) {
        	smatch = false; 
        	gmatch = false;
        	cs = secret.charAt(i);
        	cg = guess.charAt(i);
        	if (cs == cg) {
        		++bulls; 
        		continue;
        	} 
        	
        	if (ms.containsKey(cg) && ms.get(cg) > 0) {
        		++cows;
        		ms.put(cg, ms.get(cg) - 1);
        		gmatch = true;
        	}
        	
        	if (mg.containsKey(cs) && mg.get(cs) > 0) {
        		++cows;
        		mg.put(cs, mg.get(cs) - 1);
        		smatch = true;
        	}
        	
        	if (!smatch) {
        		if (ms.containsKey(cs)) {
            		ms.put(cs, ms.get(cs) + 1);	
            	} else {
            		ms.put(cs, 1);
            	}
        	}
        	
        	if (!gmatch) {
        		if (mg.containsKey(cg)) {
            		mg.put(cg, mg.get(cg) + 1);	
            	} else {
            		mg.put(cg, 1);
            	}
        	}
        	
        }
        
        
        return bulls + "A" + cows + "B";
    }
	
	public static void main(String[] args) {
		String secret = "2962";
		String guess =  "7236";
		System.out.println(getHint(secret, guess));
	}
}
