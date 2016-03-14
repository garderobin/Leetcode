package interview.amazon;

import java.util.Scanner;
public class AmazonSummerHackerrank {
	
public static void main(String args[] ){
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */
    int n, a, i, j, cols;    
    Scanner in = new Scanner(System.in);
    n = in.nextInt();
    if (n == 1) {
        System.out.println(in.nextInt());
    }
    int[][] matrix = new int[n][n];
    if (n == 0) {
        System.out.println("");
    }
    cols = 0;
    for (j = 0; j < n; j++) {
        a = in.nextInt();
        matrix[0][j] = a;
        cols++;
    }
    if (cols != n) {
        System.out.println("ERROR");
        System.exit(0);
    }
    for (i = 1; i < n; i++) {
        for (j = 0; j < n; j++) {
            a = in.nextInt();
            matrix[i][j] = a;
        }
    }
    
    matrix = shift(matrix);
    for (j = 0; j < n; j++) {
            System.out.print(matrix[0][j] + " ");
    }
    for (i = 1; i < n; i++) {
        System.out.println();
        for (j = 0; j < n - 1; j++) {
            System.out.print(matrix[i][j] + " ");
        }
        System.out.print(matrix[i][j]);
    }
    
    in.close();
}
    public static int[][] shift(int[][] matrix) {
        
        int x = 0, y = 0, m = matrix.length, n = matrix[0].length;
        //int i, t;
        while (m > 0 && n > 0) {
            int tmp = matrix[x+1][y];
            if (m == 1 || n == 1) {
                break;
            }
            for (int i = 0; i < n-1; i++) {
                int t = tmp;
                tmp = matrix[x][y];
                matrix[x][y] = t;
                y++;
            }
            for (int i = 0; i < m-1; i++) {
                int t = tmp; 
                tmp = matrix[x][y];
                matrix[x][y] = t;
                x++;
            }
            for (int i = 0; i < n-1; i++) {
                int t = tmp;
                tmp = matrix[x][y];
                matrix[x][y] = t;
                y--;
            }
            for (int i = 0; i < m-1; i++) {
                int t = tmp;
                tmp = matrix[x][y];
                matrix[x][y] = t;
                x--;
            }
            x++;
            y++;
            m -= 2;
            n -= 2;
        }
        
        return matrix;
    }
   
}
