package algorithm;

import java.util.HashMap;
import java.util.HashSet;

public class ValidSudoku {
	
	static boolean[][] rows = new boolean[9][9];
	static boolean[][] cols = new boolean[9][9];
	static boolean[][] area = new boolean[9][9];               
	static boolean[][][] valid = new boolean[9][9][9]; //valid[i][j][n] = false means integer (n+1) is invalid in position (i,j).
    
	
	// Not completed
	public static boolean isValidSudokuByHash(char[][] board) {
		if (board == null || board.length != 9 || board[0].length != 9) {
			return false;
		}
			
		int i, j, k;
        HashMap<Integer, HashSet<Character>> rows = new HashMap<Integer, HashSet<Character>>(9);
        HashMap<Integer, HashSet<Character>> cols = new HashMap<Integer, HashSet<Character>>(9);
        HashMap<Integer, HashSet<Character>> area = new HashMap<Integer, HashSet<Character>>(9);
        HashSet<Character> rlist = new HashSet<Character>(9);
        HashSet<Character> clist = new HashSet<Character>(9);
        HashSet<Character> alist = new HashSet<Character>(9);
        char cur = '.';
        for (i = 0; i < 9; i++) {
        	if (board[i].length != 9) {
        		return false;
        	}
        	rlist = rows.get(i);
        	if (rlist == null) {
    			rlist = new HashSet<Character>(9);        			
    		} 
        	for (j = 0; j < 9; j++) {
        		cur = board[i][j];        		
        		clist = cols.get(j);
        		k = getAreaNumberByPosition(i, j);
        		alist = area.get(k);
        		
        		if (rlist.contains(cur)) {
        			return false;        			 
        		}
        		
        		if (alist == null) {
        			alist = new HashSet<Character>(9);        			
        		} else if (alist.contains(cur)) {
        			return false;        			 
        		}
        		    		
        		if (clist == null) {
        			clist = new HashSet<Character>(9);        			
        		} else if (clist.contains(cur)) {
        			return false;        			 
        		}        		

        		if (Character.isDigit(cur) && cur!='0') {
    				rlist.add(cur);
    				clist.add(cur);
    				alist.add(cur);
    				cols.put(j, clist);
    				area.put(k, alist);
    			}  			
    			
        	}
        	rows.put(i, rlist);
        }
        
        return true;
    }
	
	public static void main(String[] args) {
		String[] strs = {"....5..1.", ".4.3.....",".....3..1","8......2.","..2.7....",".15......",".....2...",".2.9.....","..4......"};
		//String[] strs = {".87654321","2........","3........","4........","5........","6........","7........","8........","9........"};
		char[][] board = StringArrayToBoard(strs);
		System.out.println(isValidSudoku(board));
	}
	
	public static int getAreaNumberByPosition(int i, int j) {
		int x = i / 3;
		int y = j / 3;		
		return 3 * x  + y;
	}
	
	public static char[][] StringArrayToBoard(String[] in) {
		if (in == null || in[0] == null || in[0].length() == 0) {
			return null;
		}
		char[][] board = new char[in.length][in[0].length()];
		int i, j, len;
		String line = "";
		for (i = 0; i < in.length; i++) {			
			line = in[i];
			len = line.length();
			for (j = 0; j < len; j++) {
				board[i][j] = line.charAt(j);
			}
		}
		
		return board;
	}
	
	public static boolean isValidSudoku(char[][] board) {
		if (board == null || board.length != 9 || board[0].length != 9) {
			return false;
		}
			
		int i, j, k, temp;
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] area = new boolean[9][9];               
        char cur = '.';
        
        for (i = 0; i < 9; i++) {
        	if (board[i].length != 9) {
        		return false;
        	}
        	
        	for (j = 0; j < 9; j++) {
        		cur = board[i][j];        		        		        		
        		if (cur > '0' && cur <= '9') {
        			k = getAreaNumberByPosition(i, j);
        			temp  = (int)cur - 49;
        			if (rows[i][temp] || cols[j][temp] || area[k][temp]) {
        				return false;
        			} 
        			rows[i][temp] = true;
        			cols[j][temp] = true;
        			area[k][temp] = true;
        		}        		    			
        	}
        }        
        return true;
    }
	
	public void solveSudoku(char[][] board) {
        if (!isValidSudoku(board)) {
        	return;
        }
        int i, j, k, x, temp;
        char cur = '.';
        
        // Initialization
        for (i = 0; i < 9; i++) {      	
        	for (j = 0; j < 9; j++) {
        		cur = board[i][j];        
        		k = getAreaNumberByPosition(i, j);   		
        		if (cur=='.') {   // Visit every node in related col, row, area     			
        			for (x = 0; x < 9; x++) { 
        				valid[i][j][x] = !(rows[i][x] || cols[j][x] || area[k][x]);
//        				if (valid[i][j][x] && board[i][j]=='.') { // Pick up the first one;
//        					board[i][j] = (char)(x + 49);
//        				}
        			}
        		} else { // Put the given, fixed numbers into the array.
        			temp  = (int)cur - 49;        			
        			rows[i][temp] = true;
        			cols[j][temp] = true;
        			area[k][temp] = true;
        			valid[i][j][temp] = true;        	
        			for (x = 0; x < 9; x++) {
        				if (x != temp) {
        					valid[i][j][x] = false;
        				}
        			}
        		}
        	}
        }        
        
        // Backtracing
        // i = 0; j = 0;
        backTracingSudoku(0, 0, board);
        
    }
	
	public static void backTracingSudoku(int i, int j, char[][] board) {
		int k, x, temp;
        char cur = '.';
		cur = board[i][j];        
		k = getAreaNumberByPosition(i, j);   		
		if (cur=='.') {   // Visit every node in related col, row, area     			
			for (x = 0; x < 9; x++) { 
				valid[i][j][x] = !(rows[i][x] || cols[j][x] || area[k][x]);
//				if (valid[i][j][x] && board[i][j]=='.') { // Pick up the first one;
//					board[i][j] = (char)(x + 49);
//				}
			}
		}
	}
}
