package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class ThreeSum {
	public static int counter = 0;
	public static int c2 = 0;
	public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
        	return rst;
        }        
        
        //Arrays.sort(nums);
        nums = preprocess(nums);
        int p1 = nums.length / 2;
        int p2 = p1 + 1;
        HashSet<List<Integer>> hs = new HashSet<List<Integer>>();

        //rst = helper(nums, hs, rst, p1, p2, 0, nums.length - 1);
        rst = helper(nums, hs, rst, p1, p2);
        return rst;
    }
/*
	private static HashSet<List<Integer>> helper(int[] nums, HashSet<List<Integer>> hs,
			int p1, int p2) {
		if (p1 == nums.length || p2 == nums.length) {
			return hs;
		}
		if (p1 == p2) {
			return helper(nums, hs, p1, p2 + 1);
		}		
		int target = 0 - nums[p1] - nums[p2];
		int i = p2 + 1;
		for (; i < nums.length; i++) {
			if (nums[i] == target) {
				hs.add(Arrays.asList(nums[p1], nums[p2], nums[i]));
			}
		}
		hs = helper(nums, hs, p1, p2 + 1);
		hs = helper(nums, hs, p1 + 1, p2);
		return hs;
	}
*/
/*
	private static List<List<Integer>> helper(int[] nums, HashSet<List<Integer>> hs, List<List<Integer>> rst,
			int p1, int p2, int start, int end) {
		if (p1 < 0 || p2 >= nums.length) {
			return rst;
		}				
		int target = 0 - nums[p1] - nums[p2];		
		List<Integer> list;
		if (target <= nums[p1]) { 
			for (; start < p1; start++) {
				if (nums[start] == target) {
					list = Arrays.asList(nums[start], nums[p1], nums[p2]);
					if (hs.add(list)) {
						rst.add(list);
					}
					start++;
					break; 
				} 
				else if (nums[start] > target) {
					break;
				}
			}

		} else if (target >= nums[p2]) {
			
			for (; end > p2; end--) {
				if (nums[end] == target) {
					list = Arrays.asList(nums[p1], nums[p2], nums[end]);
					if (hs.add(list)) {
						rst.add(list);
					}
					end--;
					break; 
				} 
				else if (nums[end] < target) {
					break;
				}
			}

		}				
		else {
			rst = helper(nums, hs, rst, p1 - 1, p2, start, end);
			rst = helper(nums, hs, rst, p1, p2 + 1, start, end);
			return rst;
		}
		rst = helper(nums, hs, rst, p1 - 1, p2, start, nums.length - 1);
		rst = helper(nums, hs, rst, p1, p2 + 1, 0, end);

		return rst;
	}
*/	
	private static List<List<Integer>> helper(int[] nums, HashSet<List<Integer>> hs, List<List<Integer>> rst,
			int p1, int p2) {
		if (p1 < 0 || p2 >= nums.length) {
			return rst;
		}				
		int target = 0 - nums[p1] - nums[p2];		
		List<Integer> list;
		// bst method returns index of the search key, if it is contained in the array; otherwise, (-(insertion point) - 1). 
		int index = Arrays.binarySearch(nums, target);
		int[] sol = {nums[p1], nums[p2], 0};
		if (index > 0 && index != p1 && index != p2) {
			sol[2] = nums[index];
			Arrays.sort(sol);
			list = Arrays.asList(sol[0], sol[1], sol[2]);
			if (hs.add(list)) {
				rst.add(list);
				System.out.println("[" + sol[0] + ", " + sol[1] + ", " + sol[2] + "]");
			}
		}
		rst = helper(nums, hs, rst, p1, p2 + 1);
		rst = helper(nums, hs, rst, p1 - 1, p2);
		return rst;
	}
	public static void main(String[] args) {
		int[] test = {7,-1,14,-12,-8,7,2,-15,8,8,-8,-14,-4,-5,7,9,11,-4,-15,-6,1,-14,4,3,10,-5,2,1,6,11,2,-2,-5,-7,-6,2,-15,11,-6,8,-4,2,1,-1,4,-6,-15,1,5,-15,10,14,9,-8,-6,4,-6,11,12,-15,7,-1,-9,9,-1,0,-4,-1,-12,-2,14,-9,7,0,-3,-4,1,-2,12,14,-10,0,5,14,-1,14,3,8,10,-8,8,-5,-2,6,-11,12,13,-7,-12,8,6,-13,14,-2,-5,-11,1,3,-6};
		//counter = 0;
		//c2 = 0;
		//System.out.println(test.length);
		
		//testPreprocess();
		
		List<List<Integer>> rst = threeSum(test);
		System.out.println(rst);
//		System.out.println(rst.size());
//		int j;
//		for (List<Integer> e : rst) {	
//			System.out.print("[");
//			for (j = 0; j < 3; j++) {
//				System.out.print(e.get(j) + ",\t");
//			}
//			System.out.println("]");
//		}
		//System.out.println("counter = " + counter + "; c2 = " + c2);
		 
		
	}
	// 0   1   2   3   4   5   6   7  8  9  10 11 12 13 14 15 16
	//-5, -3, -3, -2, -2, -2, -1, -1, 0, 1, 1, 1, 2, 3, 3, 4, 4, 
	public static void testSort() {
		int[] test = {-1,-2,-3,4,1,3,0,3,-2,1,-2,2,-1,1,-5,4,-3};
		Arrays.sort(test);
		for (int e : test) {
			System.out.print(e + ", ");
		}
	}
	
	public static void testPreprocess() {
		int[] test = {7,-1,14,-12,-8,7,2,-15,8,8,-8,-14,-4,-5,7,9,11,-4,-15,-6,1,-14,4,3,10,-5,2,1,6,11,2,-2,-5,-7,-6,2,-15,11,-6,8,-4,2,1,-1,4,-6,-15,1,5,-15,10,14,9,-8,-6,4,-6,11,12,-15,7,-1,-9,9,-1,0,-4,-1,-12,-2,14,-9,7,0,-3,-4,1,-2,12,14,-10,0,5,14,-1,14,3,8,10,-8,8,-5,-2,6,-11,12,13,-7,-12,8,6,-13,14,-2,-5,-11,1,3,-6};
		int[] pre = preprocess(test);
		System.out.println(pre.length);
		for (int num: pre) {
			System.out.print(num + ", ");
		}
	}
	
	public static int[] preprocess(int[] nums) {
		Arrays.sort(nums);
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(nums[0]);
		boolean dup = false;
		int min = nums[0];
		int max = nums[nums.length - 1];
		int peek;
		for (int i = 1; i < nums.length; i++) {
			peek = stack.peek();
			if (nums[i] != peek) {
				stack.push(nums[i]);
				dup = false;
			}
			else if (!dup && ((nums[i] < 0 && nums[i] * 2 + max >= 0) || (nums[i] > 0 && nums[i] * 2 + min <= 0))) {
				stack.push(nums[i]);
				dup = true;
			} 
		}
		int rst[] = new int[stack.size()];
		for (int i = rst.length - 1; i >= 0; i--) {
			rst[i] = stack.pop();
		}
		return rst;
		
		
	}
	
//	public static List<List<Integer>> threeSumIterative(int[] nums)  {
//		
//        
//	}
//	
	public static List<List<Integer>> threeSumJiuzhang(int[] nums) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
        	return rst;
        }        
        Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {
			if (i != 0 && nums[i] == nums[i - 1]) {
				continue; // to skip duplicate numbers; e.g [0,0,0,0]
			} //为什么这里避复不会避掉-2， 0， 0， 2这样的case?

			int left = i + 1;
			int right = nums.length - 1;
			while (left < right) {
				int sum = nums[left] + nums[right] + nums[i];
				if (sum == 0) {
					ArrayList<Integer> tmp = new ArrayList<Integer>();
					tmp.add(nums[i]);
					tmp.add(nums[left]);
					tmp.add(nums[right]);
					rst.add(tmp);
					left++;
					right--;
					while (left < right && nums[left] == nums[left - 1]) { // to skip duplicates
						left++;
					}
					while (left < right && nums[right] == nums[right + 1]) { // to skip duplicates
						right--;
					}
				} else if (sum < 0) {
					left++;
				} else {
					right--;
				}
			}
		}
		return rst;
	}
	
	
}
