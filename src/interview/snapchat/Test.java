package interview.snapchat;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		// Test static
//		ArrayList<int[][]> test = new ArrayList<int[][]>();
//		test.add(new int[][]{{1,5,5,5,5,5},{5,0,0,0,0,5},{5,0,0,0,0,5},{5,0,0,0,5,9}});
//		test.add(new int[][]{{1,5,5},{0,5,0},{0,0,9}});
//		test.add(new int[][]{{1,5,5,0,5},{0,5,0,0,0},{0,5,0,5,0},{5,0,5,5,9}});
//		
//		for (int[][] t: test) System.out.println(MazeBreakWall.minWallBreak(t, 1, 9, 0, 5));
		
		// Test ood
//		MazeBreakWall<Integer> maze = new MazeBreakWall<Integer>(1, 9, 0, 5);
//		ArrayList<Integer[][]> test = new ArrayList<Integer[][]>();
//		test.add(new Integer[][]{{1,5,5,5,5,5},{5,0,0,0,0,5},{5,0,0,0,0,5},{5,0,0,0,5,9}});
//		test.add(new Integer[][]{{1,5,5},{0,5,0},{0,0,9}});
//		test.add(new Integer[][]{{1,5,5,0,5},{0,5,0,0,0},{0,5,0,5,0},{5,0,5,5,9}});
//		for (Integer[][] t: test) System.out.println(maze.minWallBreak(t));
		Maze maze = new Maze('1', '9', '0', '5', false);
//		ArrayList<char[][]> test = new ArrayList<char[][]>();
//		test.add(new char[][]{{'1','5','5','5','5','5'},{'5','0','0','0','0','5'},{'5','0','0','0','0','5'},{'5','0','0','0','5','9'}});
//		test.add(new char[][]{{'1','5','5'},{'0','5','0'},{'0','0','9'}});
//		test.add(new char[][]{{'1','5','5','0','5'},{'0','5','0','0','0'},{'0','5','0','5','0'},{'5','0','5','5','9'}});
		String[] strs = {"155555 500005 500005 500059", "155 050 009", "15505 05000 05050 50559", "05505 05100 05050 50595"};
		for (String s: strs) {
			String[] rows = s.split(" ");
			int n = rows.length;
			char[][] t = new char[n][n];
			for (int i = 0; i < n; ++i) t[i] = rows[i].toCharArray();
			System.out.println(maze.minWallBreak(t));
		}
		
	}
	
	public static void testMazeWallBreak() {
//		MazeBreakWall maze = new MazeBreakWall('1', '9', '0', '5');
		ArrayList<char[][]> test = new ArrayList<char[][]>();
		test.add(new char[][]{{'1','5','5','5','5','5'},{'5','0','0','0','0','5'},{'5','0','0','0','0','5'},{'5','0','0','0','5','9'}});
		test.add(new char[][]{{'1','5','5'},{'0','5','0'},{'0','0','9'}});
		test.add(new char[][]{{'1','5','5','0','5'},{'0','5','0','0','0'},{'0','5','0','5','0'},{'5','0','5','5','9'}});
		String[] strs = {"155555 500005 500005 500059", "155 050 009", "15505 05000 05050 50559", "05505 05100 05050 50595"};
		for (String s: strs) {
			String[] rows = s.split(" ");
			int n = rows.length;
			char[][] t = new char[n][n];
			for (int i = 0; i < n; ++i) t[i] = rows[i].toCharArray();
			System.out.println(MazeBreakWall.minWallBreak(t, '1', '9', '0', '5'));
		}
		
	}
	
	public static void testMaze(String[] args) {
		String[] test = {
				"155550",
				"050550",
				"050000",
				"050050",
				"000509"
		};
		char[][] board = new char[test.length][test[0].length()];
		for (int i = 0; i < test.length; ++i) board[i] = test[i].toCharArray();
		Maze maze = new Maze(board, '1', '9', '0', '5', false);
		System.out.println(maze.canGoThrough(board));
	}
	
}
