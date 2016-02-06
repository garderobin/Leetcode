package interview;

import java.io.*;
import java.util.Scanner;
public class Xinli_HR_2 {
	
	public static void main(String[] args) {		
        Scanner in = new Scanner(System.in);
        int caseNum = in.nextInt();
        int[][] cases = new int[caseNum][3];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < caseNum; i++) {
        	for (int j = 0; j < 3; j++) {
        		cases[i][j] = in.nextInt();
        	}
        	sb.append(countBeers(cases[i][0], cases[i][1], cases[i][2]));
        	sb.append('\n');
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
	}

	private static int countBeers(int N, int B, int M) {
		int total = N / B, surplus = total, increment; 
		do {
			increment = surplus / M;
			surplus = (surplus % M) + increment;
			total += increment;
		} while (surplus >= M);
		return total;
	}
}
