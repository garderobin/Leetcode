package interview.wayfair;

/**
 * 1. Brute-force: O(MN) time, O(1) space.
 * 2. KMP: O(N) time, O(M) space.
 * 3. RK
 * 4. Boyer-Moore: O(N/M) for average runtime and O(NM) for worst case. O(1) space.
 */
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
     * KMP algorithm. O(N) time, O(M) space. (N > M)
     * 最后补充一点：由于KMP算法只预处理B串，因此这种算法很适合这样的问题：给定一个B串和一群不同的A串，问B是哪些A串的子串。
     */
    public int strStrKMP(String haystack, String needle){
        if (haystack == null || needle == null || haystack.length() < needle.length()) return -1;
        if (needle.isEmpty()) return 0;
        
        //generate next array, need O(n) time
        int i = -1, j = 0, n = haystack.length(), m = needle.length();
        int[] next = new int[m];
        next[0] = -1;
        while (j < m - 1) {
            if (i == -1 || needle.charAt(i) == needle.charAt(j))
                next[++j] = ++i;
            else 
                i = next[i];
        }
        //check through the haystack using next, need O(m) time
        for (i = 0, j = 0; i < n && j < m; ) {
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }
            else 
                j = next[j];
        }
        return (j == m) ? i - j : -1;
    }
    
    /*
     * Boyer-Moore: O(N/M) for average runtime and O(NM) for worst case. O(1) space.
     * Pruning happens when the mismatch index of needle is not the haystack differentchar's last occurrence.
     */
    public int strStrBM(String haystack, String needle) {
    	if (haystack == null || needle == null || haystack.length() < needle.length()) return -1;
    	if (needle.isEmpty()) return 0;

        int pos[] = new int[256]; // stores the last index of every single char.
        for (int i = 0; i < 256; ++i) 	 				 pos[i] = -1;
        for (int j = 0, m = needle.length(); j < m; ++j) pos[needle.charAt(j)] = j;

        return search(pos, haystack, needle); 
    }
    
    private int search(int[] pos, String haystack,String needle) {
        int n = haystack.length(), m = needle.length();
        for(int i = 0, j = 0, skip = 0, limit = n-m+1; i < limit; i += skip, skip = 0) {
            for(j = m-1; j >= 0; --j)  {
                if(needle.charAt(j) != haystack.charAt(i+j)) {
                    skip = Math.max(1, j - pos[haystack.charAt(j+i)]); // keep moving
                    break;
                }
            }
            if(skip == 0) return i; // can not move any more
        }
        return -1;

    }
   

    /*
     * Robin-Karp algorithm, uses hashing to find any one of a set of pattern strings in a text. 
     * Average and best case running time is O(N+M), worst-case time is O(MN).
     */
    public int strStrRK(String haystack, String needle) {
        if (haystack == null || needle == null || haystack.length() < needle.length()) return -1;
        if(needle.length() == 0) return 0;
        
        int n = haystack.length(), m = needle.length();
        long R = 31L, M = 10000000000000003L, RK = 1;
        long target = hash(needle, 0, m - 1, R, M, RK), hash = hash(haystack, 0, m - 1, R, M, RK);

        for (int i = 0; i < needle.length(); i++)  { RK = (RK * R) % M; }
        
        RK %= M;

        if (hash == target) return 0;
        for (int i = 1, limit = n-m+1; i < limit; ++i) {
            long cur = nextHash(hash, haystack.charAt(i - 1), haystack.charAt(i + m - 1), R, M, RK);
            if (cur == target) return i;
            hash = cur;
        }
        return -1;
    }

    long hash(String s, int start, int end, long R, long M, long RK) {
        long sum = 0;
        for (int i = start; i <= end; ++i) {
            sum = sum * R % M + (int) s.charAt(i) % M;
        }
        return sum % M;
    }

    long nextHash(long hash, char oldFirst, char next, long R, long M, long RK) {
        long a = hash * R % M;
        long b = next % M;
        long c = oldFirst % M * RK % M;

        return (a + b - c + M) % M;
    }

}