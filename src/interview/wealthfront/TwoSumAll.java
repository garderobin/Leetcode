package interview.wealthfront;

import java.util.*;

/*
 * Ask; return positions or indexes or values?
 * Ask: each output pair in original order? (yes)
 * Ask: handle duplicates in array? (no)
 * Ask: input element range limit (INT_MIN, INT_MAX)
 * Ask: input array size limit? (INT_MIN, INT_MAX)
 */
public class TwoSumAll {
	
	public static void main(String[] args) {
		ArrayList<int[]> numsList = new ArrayList<int[]>();
		// expect []
		numsList.add(null);								// target = 0, null array
		numsList.add(new int[0]);  						// target = 0, empty array
		
		// expect []
		numsList.add(new int[]{-3,2,-1,0,1,-2,3,6,5,4});// target = -6, target too small
		numsList.add(new int[]{-3,2,-1,0,1,-2,3,6,5,4});// target = 12, target too big
		
		// expect [[1,4],[2,3]]
		numsList.add(new int[]{1,2,3,4,5,6,7});			// target = 5, sorted,  all positive elements, expect [[1,4],[2,3]]
		numsList.add(new int[]{5,2,4,1,3,7,6});			// target = 5, unsorted,all positive elements, expect [[0,4],[2,3]]
		
		// expect [[3,10],[3,8],[4,7],[5,6]]
		numsList.add(new int[]{-3,-2,-1,0,1,2,3,4,5,6});// target = 5, sorted,  contains non-positive elements
		
		// expect [[1,6],[2,7],[3,8],[4,9]]
		numsList.add(new int[]{-3,2,-1,0,1,-2,3,6,5,4});// target = 5, unsorted, contains non-positive elements 
		
		int[] targets = {0, 0, -6, 12, 5, 5, 5, 5};
		List<List<List<Integer>>> expects = new ArrayList<List<List<Integer>>>();
		expects.add(new ArrayList<List<Integer>>());
		expects.add(new ArrayList<List<Integer>>());
		expects.add(new ArrayList<List<Integer>>());
		expects.add(new ArrayList<List<Integer>>());
		expects.add(Arrays.asList(Arrays.asList(1,4), Arrays.asList(2,3)));
		expects.add(Arrays.asList(Arrays.asList(2,5), Arrays.asList(3,4)));
		expects.add(Arrays.asList(Arrays.asList(3,10), Arrays.asList(4,9),Arrays.asList(5,8), Arrays.asList(6,7)));
		expects.add(Arrays.asList(Arrays.asList(2,7), Arrays.asList(3,8),Arrays.asList(4,9), Arrays.asList(5,10)));
		
//		for (int i = 0; i < targets.length; ++i) {
//			System.out.print("test case " + i + ":\t");
//			System.out.print("expect = :\t" + expects.get(i) + "\t");
//			System.out.println("output = :\t" + twoSumByHashMap(numsList.get(i), targets[i]));
//		}
		
		System.out.println("output = :\t" + twoSumByHashMap(numsList.get(5), targets[5]));
	}
	
	/**
	 * Find all pairs in an array whose sum equals to the target.
	 * 输出值可以，输出次序不能保证按原始顺序。
	 * 
	 * O(NlogN) time, O(NlogN) space.
	 * @return position pairs in each pair the sum of two equals to target. (returned positions are 1-based!!!)
	 * 
	 */
	public static List<List<Integer>> twoSumBySortingArray(int[] nums, int target) {
		if (nums == null || nums.length < 2) return new ArrayList<List<Integer>>();
		
		Arrays.sort(nums);
	    List<List<Integer>> rst = new ArrayList<>();
	    
	    int left = 0, right = nums.length - 1;
	    while (left < right) {
	        int v = nums[left] + nums[right];
	        if (v == target) {
//	        	rst.add(Arrays.asList(left, right)); // 0-based
	            rst.add(Arrays.asList(left+1, right+1)); // 1-based
	            ++left;
//	            break;
	        } else if (v > target) {
	            --right;
	        } else {
	            ++left;
	        }
	    }
	    return rst;
	}
	
	/**
	 * Find all pairs in an array whose sum equals to the target.
	 * 
	 * O(N) time, O(NlogN) space.
	 * @return position pairs in each pair the sum of two equals to target. (returned positions are 1-based!!!)
	 * 
	 */
	public static List<List<Integer>> twoSumByHashMap(int[] nums, int target) {
		if (nums == null || nums.length == 0) return new ArrayList<List<Integer>>();
		List<List<Integer>> rst = new ArrayList<List<Integer>>();
        Map<Integer, Integer> expect = new HashMap<>(); // key: expect_value, small_index
        for (int i = 0; i < nums.length; i++) {
    		if (expect.containsKey(nums[i])) {
    			rst.add(Arrays.asList(expect.get(nums[i]), i+1));
    		} else {
    			expect.put(target - nums[i], i+1);
    		}
    	}
        return rst;
    }
	
	public void sort(int inputArr[]) {
        int length = inputArr.length;
        doMergeSort(inputArr, new int[length], 0, length - 1);
    }
 
    private void doMergeSort(int[] array, int[] tempMergeArr, int lowerIndex, int higherIndex) {
         
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(array, tempMergeArr, lowerIndex, middle);
            // Below step sorts the right side of the array
            doMergeSort(array, tempMergeArr, middle + 1, higherIndex);
            // Now merge both sides
            mergeParts(array, tempMergeArr, lowerIndex, middle, higherIndex);
        }
    }
 
    private void mergeParts(int[] array, int[] tempMergeArr, int lowerIndex, int middle, int higherIndex) {
 
        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergeArr[i] = array[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergeArr[i] <= tempMergeArr[j]) {
                array[k] = tempMergeArr[i];
                i++;
            } else {
                array[k] = tempMergeArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            array[k] = tempMergeArr[i];
            k++;
            i++;
        }
 
    }

	int partition(int arr[], int left, int right) {
	      int i = left, j = right, tmp, pivot = arr[(left + right) / 2];
	     
	      while (i <= j) {
	            while (arr[i] < pivot) ++i;
	            while (arr[j] > pivot) --j;
	            if (i <= j) {
	                  tmp = arr[i];
	                  arr[i] = arr[j];
	                  arr[j] = tmp;
	                  ++i;
	                  --j;
	            }
	      };
	     
	      return i;
	}
	 
	void quickSort(int arr[], int left, int right) {
	      int index = partition(arr, left, right);
	      if (left < index - 1) quickSort(arr, left, index - 1);
	      if (index < right) quickSort(arr, index, right);
	}

	
	/*
	 * Counting Sort 
	 * O(N) time, O(N) space, two pass.
	 */
	public static void countingSort(int[] nums) {
		int high = Integer.MIN_VALUE, low = Integer.MAX_VALUE;
		for (int x: nums) {
			if (x > high) high = x;
			if (x < low) low = x;
		}
		if (Math.ceil(high/2) + Math.ceil(low/2) >= Integer.MAX_VALUE) {}
		else {
			int[] counts = new int[high - low + 1]; // this will hold all possible values, from low to high	 
		    for (int x : nums)
		        counts[x - low]++; // - low so the lowest possible value is always 0

		    int current = 0;
		    for (int i = 0; i < counts.length; i++) {
		        Arrays.fill(nums, current, current + counts[i], i + low); // fills counts[i] elements of value i + low in current
		        current += counts[i]; // leap forward by counts[i] steps
		    }
		}
	    
	}
}
