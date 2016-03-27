package interview.snapchat;

import java.util.ArrayDeque;
import java.util.Queue;

public class ImageMerge {
	public static void main(String[] args) {
		int[][] board1 = {{0,0,1,0},{0,0,1,1},{1,1,1,1},{0,0,1,1}};  //1 W 0 B
		int[][] board2 = {{0,0,1,1},{0,0,1,1},{0,1,1,1},{0,0,1,1}};  //1 W 0 B
		QuadTreeImage img1 = buildQuadTreeImage(board1);
		QuadTreeImage img2 = buildQuadTreeImage(board2);
		QuadTreeImage merge = merge(img1, img2);
		printQuadTreeImage(merge);
	}
	
	
	public static QuadTreeImage buildQuadTreeImage(int[][] board) {
		if (board == null || board.length == 0 || board[0].length == 0) return new QuadTreeImage(ImageColor.Black, 0);
		return dfs(board, 0, board.length-1, 0, board[0].length-1);
	}		
	
	/*
	 * Merge img1 and img2 which must have same size.
	 * 白黑-白 黑白-白 白白-白 黑黑-黑
	 */
	public static QuadTreeImage merge(QuadTreeImage img1, QuadTreeImage img2) {
		if (img1 == null) return img2;
		if (img2 == null) return img1;
		ImageColor defaultColor = ImageColor.mergeSinglePixelColors(img1.color, img2.color), 
				gray = ImageColor.Gray, c1 = img1.color, c2 = img2.color;
		if (c1 != gray && c2 != gray) 	return new QuadTreeImage(defaultColor, img1.size);
		else if (c1 != gray)  			return (c1 == ImageColor.White) ? img1 : img2;
		else if (c2 != gray)			return (c2 == ImageColor.White) ? img2 : img1;
		else {
			QuadTreeImage root = new QuadTreeImage(gray, img1.size);
			root.NE = merge(img1.NE, img2.NE);
			root.NW = merge(img1.NW, img2.NW);
			root.SE = merge(img1.SE, img2.SE);
			root.SW = merge(img1.SW, img2.SW);
			
			ImageColor color = root.NW.color; // if all children share a same color, this area is done.
	       	if (root.NE.color == color && root.SW.color == color && root.SE.color == color){
	       		root.color = color; 
	       		root.NE = null; root.NW = null; root.SE = null; root.SW = null;
	       		// 如果想保持只要size大于1就要分割不管是不是颜色已经统一， 那么上一行应该删掉。
	       	}
	       	return root;
		}
		
	}
	
	public static void printQuadTreeImage(QuadTreeImage root) {
		Queue<QuadTreeImage> q = new ArrayDeque<QuadTreeImage>();
	    q.offer(root);
	    while (!q.isEmpty()) {
	        QuadTreeImage node = q.poll();
	        System.out.println(node.color + "\t" + node.size);
	        if (node.NE != null) { // 可以改成if (node.color == ImageColor.Gray) 取决于设计是否允许size大于一的叶子节点
	        	q.offer(node.NW);
		        q.offer(node.NE);
		       	q.offer(node.SW);
		       	q.offer(node.SE);
	        }
	    }
	}
	
	static QuadTreeImage dfs(int[][]board, int sx, int ex, int sy, int ey) {
        if(sx == ex && sy == ey){
        	return new QuadTreeImage(ImageColor.fromInteger(board[sx][sy]), 1);
        }
        QuadTreeImage node = new QuadTreeImage(ImageColor.Gray, (ex - sx + 1) * (ey - sy + 1));
        int midV = (sx + ex) / 2;
        int midH = (sy + ey) / 2;
        node.NW = dfs(board, sx, midV, sy, midH);
       	node.NE = dfs(board, sx, midV, midH+1, ey);
       	node.SW = dfs(board, midV+1, ex, sy, midH);
       	node.SE = dfs(board, midV+1, ex, midH+1, ey);
          
       	ImageColor color = node.NW.color;
       	if (node.NE.color == color && node.SW.color == color && node.SE.color == color){
       		node.color = color; 
       		node.NE = null; node.NW = null; node.SE = null; node.SW = null;
       		// 如果想保持只要size大于1就要分割不管是不是颜色已经统一， 那么上一行应该删掉。
       	} 
       	return node;
 
	}
	
	static QuadTreeImage mergeHelper(QuadTreeImage img1, QuadTreeImage img2){
        if(img1.color == ImageColor.White)
            return img1;
        return img2;
    }
	
	
}


class QuadTreeImage {
	ImageColor color;
	int size;
	QuadTreeImage NE, NW, SE, SW;
	
	QuadTreeImage(ImageColor c, int s) {
		color = c;
		size = s;
	}
	
	
}


enum ImageColor {
	Black, White, Gray;
	
	public static ImageColor fromInteger(int x) {
        switch(x) {
        case 0: return Black;
        case 1: return White;
        case 2: return Gray;
        default: return Black;
        }
    }
	
	public static ImageColor mergeSinglePixelColors(ImageColor c1, ImageColor c2) {
		return (c1 == Black && c2 == Black) ? Black : White;
	}
	
}

