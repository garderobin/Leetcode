package algorithm;

import java.util.List;

import dataStructure.NestedInteger;

public class NestedListWeightSum {
	public int depthSum(List<NestedInteger> nestedList) {
		if (nestedList == null || nestedList.size() == 0) return 0;
		return depthSum(nestedList, 1);
    }
	
	public int depthSum(List<NestedInteger> nestedList, int depth) {
		int sum = 0;
		for (NestedInteger e: nestedList) {
			sum += (e.isInteger()) ? depth * e.getInteger() : depthSum(e.getList(), depth + 1);
		}
        return sum;
	}
}
