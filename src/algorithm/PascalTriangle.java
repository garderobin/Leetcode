package algorithm;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
	public static List<List<Integer>> generate(int numRows) {
		List<List<Integer>> triangle = new ArrayList<List<Integer>>();
		if (numRows <= 0) {
			return triangle;
		} 		
		
		ArrayList<Integer> row0 = new ArrayList<Integer>(1);
		row0.add(1);
		triangle.add(row0);
		if (numRows == 1) {
			return triangle;
		}
		
		ArrayList<Integer> row1 = new ArrayList<Integer>(2);
		row1.add(1);
		row1.add(1);
		triangle.add(row1);
		if (numRows == 2) {
			return triangle;
		}
		
		int i, j;
		for (i = 2; i < numRows; i++) {
			ArrayList<Integer> cur = new ArrayList<Integer>();
			cur.add(1); //start
			ArrayList<Integer> prev = (ArrayList<Integer>) triangle.get(i - 1);
			for (j = 0; j < i - 1; j++) {
				cur.add(prev.get(j) + prev.get(j+1));
			}			
			cur.add(1); //end
			triangle.add(cur);
		}
		
		return triangle;
        
    }
	
	public static List<Integer> getRow(int rowIndex) {
		ArrayList<Integer> cur = new ArrayList<Integer>(1);
		cur.add(1);
		if (rowIndex == 0) {
			return cur;
		}
		
		ArrayList<Integer> prev = new ArrayList<Integer>(2);
		prev.add(1);
		prev.add(1);
		if (rowIndex == 1) {
			return prev;
		}		

		int i, j;
		for (i = 2; i <= rowIndex; i++) {
			cur = new ArrayList<Integer>(i+1);
			cur.add(1); //start
			for (j = 0; j < i - 1; j++) {
				cur.add(prev.get(j) + prev.get(j+1));
			}			
			cur.add(1); //end
			prev = cur;
			
		}				
		
		return cur;
		
    }
	
	public static void main(String[] args) {
		List<List<Integer>> triangle = generate(3);
		for (int i = 0; i < triangle.size(); i++) {
			ArrayList<Integer> cur = (ArrayList<Integer>) triangle.get(i);
			System.out.print("[");
			for (int j = 0; j < cur.size(); j++) {
				System.out.print(cur.get(j) + ",\t");
			}
			System.out.println("]");
		}
	}
}
