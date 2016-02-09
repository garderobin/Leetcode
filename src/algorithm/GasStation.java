package algorithm;

public class GasStation {
	
	/**
	 * One-pass也能解决累积性问题，关键看变量如何设计
	 * 这里用tank < 0 来标记原来的start是否可以维持，不能维持则start往前推。
	 * 它利用的前提是，只要sumGas >= sumCost一定存在一个start, 且因为start唯一，
	 * 从零开始计算到start之前的tank一定是负数，因为负数总会清零，所以从这个真正的start开始
	 * 每一步的tank都是正数，从而维持判断。
	 * 适当清掉累计值是个绝妙的想法！
	 * @param gas
	 * @param cost
	 * @return
	 */
	public int canCompleteCircuitDiscussion(int[] gas, int[] cost) {
		int sumGas = 0, sumCost = 0, start = 0, tank = 0;
	    for (int i = 0; i < gas.length; i++) {
	        sumGas += gas[i];
	        sumCost += cost[i];
	        tank += gas[i] - cost[i];
	        if (tank < 0) { //用来判断start是否可以维持下去
	            start = i + 1;
	            tank = 0; //关键思路
	        }
	    }
	    if (sumGas < sumCost) {
	        return -1;
	    } else {
	        return start;
	    }
    }
}
