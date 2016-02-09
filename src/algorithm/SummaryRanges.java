package algorithm;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
	
	/**
	 * 一定要记得处理最后一条边界状态。
	 * 能用a > b的坚决不用a - b > 0，因为后者可能牵涉计算超出int最大值的越界问题。
	 * @param nums
	 * @return
	 */
	public static List<String> summaryRanges(int[] nums) {
		List<String> rst = new ArrayList<String>();
        if (nums == null || nums.length == 0) {
			return rst;
		}
        int start = 0, end = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < nums.length; i++) {
        	if (nums[i] > nums[end] + 1) {  
        		sb.append((start == end) ? nums[start] + "" : nums[start] + "->" + nums[end]);
        		rst.add(sb.toString());
        		sb = new StringBuilder();
        		start = i;
        		end = start;
        	} else {
        		++end;
        	}
        }
        sb.append((start == end) ? nums[start] + "" : nums[start] + "->" + nums[end]);
		rst.add(sb.toString());
        return rst;
    }
	
	public static void main(String[] args) {
		int[] nums = {0,1};
		List<String> rst = summaryRanges(nums);
		for (String e : rst) {
			System.out.println(e);
		}
	}
}
