package algorithm;

public class RegularExpressionMatching {
	 /**
     * This solution is assuming s has no regular expressions.
     * 
     * dp: res[i][j]=is s[0,...,i-1] matched with p[0,...,j-1];
     * 
     * If p[j-1]!='*', res[i][j] = res[i-1][j-1] && (s[i-1]==p[j-1]||p[j-1]=='.').
     * Otherwise, res[i][j] is true if
     * res[i][j-1] or res[i][j-2] or
     * res[i-1][j] && (s[i-1]==p[j-2]||p[j-2]=='.'),  这里res[i-1][j]怎么理解？？
     *
     * and notice the third 'or' case includes the first 'or'.
     * 
     * 
     * Boundaries: res[0][0]=true;//s=p="". res[i][0]=false, i>0.
     * res[0][j]=is p[0,...,j-1] empty, j>0, and so res[0][1]=false, 因为*不能作为开头
     * res[0][j]=p[j-1]=='*'&&res[0][j-2].
     * 
     * O(n) space is enough to store a row of res.
     */

	public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[] res = new boolean[n + 1]; //rest[j]意味着j的左边完全匹配
        res[0] = true;

        int i, j;
        for (j = 2; j <= n; j++) { res[j] = res[j - 2] && p.charAt(j - 1) == '*'; } // 这是考察如果s完全不往前走，而p的前面一部分哪些可以忽略

        char pc, sc, tc;
        boolean pre, cur; // pre=res[i - 1][j - 1], cur=res[i-1][j]

        for (i = 1; i <= m; i++) {
            pre = res[0];
            res[0] = false; //s已经往前推进了至少1位，p一位也没有推进，这是不可能匹配的
            sc = s.charAt(i - 1);

            for (j = 1; j <= n; j++) {
                cur = res[j];
                pc = p.charAt(j - 1);
                if (pc != '*')
                    res[j] = pre && (sc == pc || pc == '.');
                else {
                    // pc == '*' then it has a preceding char, i.e. j>1
                    tc = p.charAt(j - 2);
                    res[j] = res[j - 2] || (res[j] && (sc == tc || tc == '.'));
                    //       (0个preceding) || (s直到前一位一直是能match的 && (preceding与s的当前位匹配, 也就是再延续了preceding))
                }
                pre = cur; //而不能用pre = res[j].可以说所以用cur就是为了避免这种串套，保证pre取的是p从零到前一位与s从零到前一位匹配的结果
            }
        }

        return res[n];
    }
}
