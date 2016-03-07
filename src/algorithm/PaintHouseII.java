package algorithm;

public class PaintHouseII {
	public static int minCostII(int[][] costs) {
		if (costs == null || costs.length == 0 || costs[0].length == 0) { return 0; }
		int n = costs.length, k = costs[0].length, color = 0, min = 0, sec = 0;
		for (int i = 0; i < n; ++i) {
			int prevMin = min, prevSec = sec, prevColor = color;
			min = Integer.MAX_VALUE; // 分层次greedy中非常重要的一点，决不能把前一层和后一层的放在一起计算
			sec = Integer.MAX_VALUE; // 所以prev的出现和min, sec的重设非常重要！！
			for (int j = 0; j < k; ++j) {
				int c = costs[i][j] + ((prevMin != prevSec && j == prevColor) ? prevSec : prevMin);
				if (c < min) {
					sec = min;
					min = c;
					color = j;
				} else if (c == min) { sec = min; }
				else if (c < sec) { sec = c;  }
				else {}
			}
		}
		
		return min;
 	}
	
	public static void main(String[] args) {
					//     0	1	2	3	4	5	6	7	8	9	10
		int[][] costs = { {3,	20,	7,	7,	16,	8,	7,	12,	11,	19,	1	},
		                  {10,	14,	3,	3,	9,	13,	4,	12,	14,	13,	1	},
		                  {10,	1,	14,	11,	1,	16,	2,	7,	16,	7,	19	},
		                  {13,	20,	17,	15,	3,	13,	8,	10,	7,	8,	9	},
		                  {4,	14,	18,	15,	11,	9,	19,	3,	15,	12,	15	},
		                  {14,	12,	16,	19,	2,	12,	13,	3,	11,	10,	9	},
		                  {18,	12,	10,	16,	19,	9,	18,	4,	14,	2,	4	}};
		System.out.println(minCostII(costs));
	}
	
	public static int minCostIIDP(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) { return 0; }
        int n = costs.length, k = costs[0].length, rst = Integer.MAX_VALUE;
        int[][] f = costs;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                int min = Integer.MAX_VALUE;
                for (int c = 0; c < k; c++) {
                    if (c == j) { continue; }
                    min = Math.min(min, f[i-1][c]);
                }
                f[i][j] = min + costs[i][j];
            }
        }
        
        for (int j = 0; j < k; j++) {
            rst = Math.min(rst, f[n-1][j]);
        }
        return rst;
    }
}
