package interview.snapchat;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		ArrayList<int[][]> test = new ArrayList<int[][]>();
		test.add(new int[][]{{1,5,5,5,5,5},{5,0,0,0,0,5},{5,0,0,0,0,5},{5,0,0,0,5,9}});
		test.add(new int[][]{{1,5,5},{0,5,0},{0,0,9}});
		test.add(new int[][]{{1,5,5,0,5},{0,5,0,0,0},{0,5,0,5,0},{5,0,5,5,9}});
		
		for (int[][] t: test) System.out.println(MazeBreakWallII.maze(t, 1, 9, 0, 5));
	}
}
