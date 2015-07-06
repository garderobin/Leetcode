package algorithm;

import java.util.Arrays;

public class RectangleArea {
	public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		int area = (C - A) * (D - B) + (G - E) * (H - F);
        
        if (C <= E || B >= H || A >= G || D <= F) {
            return area; 
        } 
        int[] rows = sortFourInt(A, C, E, G);        
        int[] cols = sortFourInt(B, D, H, F);
        int x = rows[2] - rows[1];
        int y = cols[2] - cols[1];
        return area - x * y;
    }
	
	public static int[] sortFourInt(int m, int n, int p, int q) {
		int[] res = {m, n, p, q};
		Arrays.sort(res);		
		return res;
	}
}
