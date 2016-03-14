package interview.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 要能够分析这种做法的复杂度为什么是O(klog(mn)).
 */ 
public class NumberOfIslandsII {
	private int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	public List<Integer> numIslands2Discussion1(int m, int n, int[][] positions) {
        UnionFind2D islands = new UnionFind2D(m, n);
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            int x = position[0], y = position[1];
            int p = islands.add(x, y);
            for (int[] d : dir) {
                int q = islands.getID(x + d[0], y + d[1]);
                if (q > 0 && !islands.find(p, q))
                    islands.unite(p, q);
            }
            ans.add(islands.size());
        }
        return ans;
    }
		
	public List<Integer> numIslands2Discussion2(int m, int n, int[][] positions) {
	    List<Integer> result = new ArrayList<>();
	    if(m <= 0 || n <= 0) return result;

	    int count = 0;                      // number of islands
	    int[] roots = new int[m * n];       // one island = one tree
	    Arrays.fill(roots, -1);            

	    for(int[] p : positions) {
	        int root = n * p[0] + p[1];     // assume new point is isolated island
	        roots[root] = root;             // add new island
	        count++;

	        for(int[] dir : dir) {
	            int x = p[0] + dir[0]; 
	            int y = p[1] + dir[1];
	            int nb = n * x + y;
	            if(x < 0 || x >= m || y < 0 || y >= n || roots[nb] == -1) continue;

	            int rootNb = findIsland(roots, nb);
	            if(root != rootNb) {        // if neighbor is in another island
	                roots[root] = rootNb;   // union two islands 
	                root = rootNb;          // current tree root = joined tree root
	                count--;               
	            }
	        }

	        result.add(count);
	    }
	    return result;
	}

	public int findIsland(int[] roots, int id) {
		while(id != roots[id]) {
	        roots[id] = roots[roots[id]];   // only one line added
	        id = roots[id];
	    }
	    return id;
	}

}

/*
 * 这个办法使用了union by size的办法来优化复杂度。
 */
class UnionFind2D {
    private int[] id; 
    private int[] sz;
    private int m, n, count;

    public UnionFind2D(int m, int n) {
        this.count = 0;
        this.n = n;
        this.m = m;
        this.id = new int[m * n + 1]; //把二维数组展成一维，基本功。
        this.sz = new int[m * n + 1]; //这个数组相当于是NumberOfIsland里面的grid,记录
    }

    public int index(int x, int y) { return x * n + y + 1; }

    public int size() { return this.count; }

    public int getID(int x, int y) {
        if (0 <= x && x < m && 0<= y && y < n)
            return id[index(x, y)];
        return 0;
    }

    public int add(int x, int y) {
        int i = index(x, y);
        id[i] = i; sz[i] = 1;
        ++count;
        return i;
    }

    
    /*
     * 必须把find和root分开，因为find要检查两个点是不是在同一个island上
     * 而root要找到一个点所属的island.
     */
    public boolean find(int p, int q) {
        return root(p) == root(q);
    }

    public void unite(int p, int q) {
        int i = root(p), j = root(q);
        if (sz[i] < sz[j]) { //weighted quick union
            id[i] = j; sz[j] += sz[i];
        } else {
            id[j] = i; sz[i] += sz[j];
        }
        --count;
    }

    private int root(int i) {
        for (;i != id[i]; i = id[i])
            id[i] = id[id[i]]; //path compression
        return i;
    }
}

/**
 * 未完成
 */
//public List<Integer> numIslands2My(int m, int n, int[][] positions) {
//	List<Integer> rst = new ArrayList<Integer>();
//    if (positions == null || positions.length == 0 || positions[0].length == 0) { return rst; } 
//    rst.add(1);
//    if (positions.length == 1) { return rst; }	
//    char[][] grid = new char[m][n];
//    int[][] ids = new int[m][n];
//    grid[positions[0][0]][positions[0][1]] = '1';
//    ids[positions[0][0]][positions[0][1]] = 1;
//    int curNum = 1, lastStep = 1, curMax = 1;
//    int[] dx = {0,0,1,-1}, dy = {1,-1,0,0};
//    for (int i = 1; i < positions.length; i++) {
//    	int x = positions[i][0], y = positions[i][1];
//    	int up = (x > 0) ? ids[x-1][y]: -1;
//    	int down = (x < m-1) ? ids[x+1][y]: -1;
//    	int left = (y > 0) ? ids[x][y-1]: -1;
//    	int right = (y < n-1) ? ids[x][y+1]: -1;
//    	if (up <= 0 && down <= 0 && left <= 0 && right <= 0) { 
//    		rst.add(++lastStep);
//    		ids[x][y] = ++curMax;
//    	} else {
//    		if (up == down && down == left && left == right && right == up) {
//    			rst.add(lastStep);
//    			ids[x][y] = up;
//    		} else if (down == left && left == right && right == up) {
//    			
//    		} else if (down == left && left == right && right == up)
//    	}
//    }
//    
//    return rst;
//}