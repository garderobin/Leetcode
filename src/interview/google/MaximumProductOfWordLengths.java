package interview.google;

import java.util.Arrays;
import java.util.Comparator;

public class MaximumProductOfWordLengths {
	public static int maxProductDiscussion(String[] words) {
		int max = 0;

        Arrays.sort(words, new Comparator<String>(){
            public int compare(String a, String b){
                return b.length() - a.length();
            }
        });

        int[] masks = new int[words.length]; // alphabet masks

        for(int i = 0; i < masks.length; i++){
            for(char c: words[i].toCharArray()){
                masks[i] |= 1 << (c - 'a');
            }
//            System.out.println(words[i] + "\t=\t" + Integer.toBinaryString(masks[i]));
        }

        for(int i = 0; i < masks.length; i++){
            if(words[i].length() * words[i].length() <= max) break; //prunning
            for(int j = i + 1; j < masks.length; j++){
                if((masks[i] & masks[j]) == 0){ //为什么这样做一定能保证两者不含相同char????
                    max = Math.max(max, words[i].length() * words[j].length());
                    break; //prunning
                }
            }
        }

        return max;
    }
	
	public static void main(String[] args) {
		String[] words = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
		System.out.println(maxProductDiscussion(words));
	}
}
