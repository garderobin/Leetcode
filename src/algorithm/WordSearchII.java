package algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dataStructure.Trie;

/**
 * 用words作为字典，board从任意点出发向任意方向走不重复路线组成的string作为检索对象
 * 用dfs，或者说backtracking来控制不走后半截冤枉路。
 * @author jasmineliu
 *
 */
public class WordSearchII {
//	Set<String> res = new HashSet<String>();

    public List<String> findWords(char[][] board, String[] words) {
    	Set<String> res = new HashSet<String>();
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = dfs(board, visited, "", i, j, trie, res);
            }
        }

        return new ArrayList<String>(res);
    }

    public Set<String> dfs(char[][] board, boolean[][] visited, String str, int x, int y, Trie trie, Set<String> res) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return res;
        if (visited[x][y]) return res;

        str += board[x][y];
        if (!trie.startsWith(str)) return res;

        if (trie.has(str)) {
            res.add(str);
        }

        visited[x][y] = true;
        dfs(board, visited, str, x - 1, y, trie, res);
        dfs(board, visited, str, x + 1, y, trie, res);
        dfs(board, visited, str, x, y - 1, trie, res);
        dfs(board, visited, str, x, y + 1, trie, res);
        visited[x][y] = false;
        return res;
    }
    
}
