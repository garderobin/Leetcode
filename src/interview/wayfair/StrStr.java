package interview.wayfair;

public class StrStr {

	/*
	 * Brute-force. O(MN) time.
	 */
    public int strStr(String haystack, String needle) {
        if(haystack == null || needle == null)  return -1;
        
        int i, j, h = haystack.length(), n = needle.length(), limit = h - n + 1;
        for(i = 0; i < limit; i++) { // i tracks valid start in haystack and j tracks valid end in needle
            for(j = 0; j < n && haystack.charAt(i + j) == needle.charAt(j); j++) {}
            if(j == n) return i;
        }
        return -1;
    }
    
    /*
     * KMP algorithm. O(N) time, O(N) space.
     * 最后补充一点：由于KMP算法只预处理B串，因此这种算法很适合这样的问题：给定一个B串和一群不同的A串，问B是哪些A串的子串。
     */
    public String strStrKMP(String haystack, String needle) {
        //KMP algorithms
        if(needle.equals("")) return haystack;
        if(haystack.equals("")) return null;
        char[] arr = needle.toCharArray();
        int[] next = makeNext(arr);

        for(int i = 0, j = 0, end = haystack.length(); i < end;){
            if(j == -1 || haystack.charAt(i) == arr[j]){
                j++;
                i++;
                if(j == arr.length) return haystack.substring(i - arr.length);
            }
            if(i < end && haystack.charAt(i) != arr[j]) j = next[j];
        }
        return null;
    }

    private int[] makeNext(char[] arr){
        int len = arr.length;
        int[] next = new int[len];

        next[0] = -1;
        for(int i = 0, j = -1; i + 1 < len;){
            if(j == -1 || arr[i] == arr[j]){
                next[i+1] = j+1;
                if(arr[i+1] == arr[j+1]) next[i+1] = next[j+1];
                i++;
                j++;
            }
            if(arr[i] != arr[j]) j = next[j];
        }

        return next;
    }
}
