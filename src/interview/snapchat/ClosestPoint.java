package interview.snapchat;

import java.util.ArrayList;

/*
 * 我们有一个data sets, 然后有一个graph是类似10 * 10的grid,写一个function, input是一个query point，求出离它最近的点
 * brute force是每个点distance求一下，后来改成了类似quad tree分成四个area的做法
 */
public class ClosestPoint {
	
	public static void main(String[] args) {
		ArrayList<char[][]> grids = new ArrayList<>();
		grids.add(new char[][] {{'0','0','1','1','1'},{'0','0','0','0','0'},{'1','1','1','0','0'},{'0','0','0','0','1'}});
		int[] rst = nearestNeighbor(grids.get(0), 1, 1);
		System.out.println("(" + rst[0] + ", " + rst[1] + ")");
	}
	
	/**
	 * Quad tree search. O(logN) time, O(logN) space.
	 * @param grid
	 * @param sx
	 * @param sy
	 * @return
	 */
	public static int[] nearestNeighbor(char[][] grid, int sx, int sy) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) return new int[2];
		long minDis = Long.MAX_VALUE;
		Node root = new Node(0, grid.length - 1, 0, grid[0].length - 1);
		long[] rst = singleDFS(grid, sx, sy, root, minDis);
		
		return (rst == null) ? null : new int[]{(int)rst[0], (int)rst[1]};
	}
	
	/**
	 * @return [x, y, dis]
	 */
	private static long[] singleDFS(char[][] grid, int sx, int sy, Node node, long minDis) {
		System.out.println("enter singleDFS: x=[" + node.xmin + "," + node.xmax + "], y=[" + node.ymin + "," + node.ymax + "], minDis=" + minDis);
		if (node.size == 1) {
			int ex = node.xmin, ey = node.ymin;
			if (grid[ex][ey] == '1') {
				long dis = (ex - sx) * (ex - sx) + (ey - sy) * (ey - sy);
				return (dis < minDis) ? new long[]{ex, ey, dis} : null;
			} else return null;
		} else {
			long[] rst = null, cur;
			for (Node child: node.children) {
				if ((cur = singleDFS(grid, sx, sy, child, minDis)) != null) {
					minDis = cur[2];
					rst = new long[]{cur[0], cur[1], cur[2]};
				}
			}
			return rst;
		}
	}
	
	
	static class Node {
		int xmin, xmax, ymin, ymax;              // x-axis and y-axis coordinates
		int size;
		ArrayList<Node> children;   // four subtrees
        char value;           // associated data
        
        Node(int xmin, int xmax, int ymin, int ymax) {
            this.xmin = xmin;
            this.xmax = xmax;
            this.ymin = ymin;
            this.ymax = ymax;
            this.value = '-';
            this.size = (ymax - ymin + 1) * (xmax - xmin + 1); // 一定要记得+1！！！
            children = new ArrayList<Node>(4);
            if (xmax - xmin > 0 || ymax - ymin > 0) {
            	int xPivot = xmin + (xmax - xmin) / 2, yPivot = ymin + (ymax - ymin) / 2;
            	children.add(new Node(xmin, xPivot, Math.min(ymax, yPivot+1), ymax)); 	// NE
                children.add(new Node(xmin, xPivot, ymin, yPivot));						// NW
                children.add(new Node(Math.min(xmax, xPivot+1), xmax, ymin, yPivot));	// SW
                children.add(new Node(Math.min(xmax, xPivot+1), xmax, Math.min(ymax, yPivot+1), ymax));	// SE
            }
            
        }
	}
	
	//
//	public static int[] nearestKNeighbors(char[][] grid, int k, int sx, int sy) {
//		if (k < 1) return new int[0];
//		if (grid == null || grid.length == 0 || grid[0].length == 0) return new int[k];
//		
//		// Initialize the root QuadTree
//		Node root = new Node(0, grid.length, 0, grid[0].length);
//		
//		//TODO
//		return null;
//	}
//	
//	private static int[] nearestKNeighbors(char[][] grid, int k, Node root) {
//		//TODO
//		return null;
//	}
	
}

