package algorithm;

public class ImplementStrStr {
	
	/**
	 * 必须掌握的暴力解法。我还不会移植。
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public int strStrDiscussionBruteForce(String haystack, String needle) {
		if(haystack == null || needle == null) {
            return -1;
        }
        int i, j, h = haystack.length(), n = needle.length();
        for(i = 0; i < h - n + 1; i++) {
            for(j = 0; j < n; j++) {
                if(haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            if(j == n) {
                return i;
            }
        }
        return -1;
	}
	
	// ""
	
	/**
	 * 退火算法
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public String strStrDiscussionKMP(String haystack, String needle) {
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
	
	/**
	 * 自己写的，有问题。
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public static int strStr(String haystack, String needle) {
		if ((haystack == null && needle == null) || (needle == null || needle.length() == 0)) {
			return 0;
		}
		if (haystack == null) {
			return -1;
		}		
        int i = 0, j = 0, h = haystack.length(), n = needle.length();
        if (h < n) {
        	return -1;
        }
        char ch, cn;
        for (; i < h && j < n; i++) {
        	if ( (ch = haystack.charAt(i)) == (cn = needle.charAt(j)) ) {
        		j++;
        	} else {
        		j = 0;
        		i--;
        	}
        }        
        return (j == n) ? i - n : -1;
    }
	
	public static void main(String[] args) {
		System.out.println(strStr("mississippi", "issip"));
	}

	
}
