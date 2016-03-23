package interview.wealthfront;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSumAll {
	
	/**
	 * Results are 1 based.
	 * @param nums
	 * @param target
	 * @return
	 */
	public List<List<Integer>> twoSumForSortedArray(int[] nums, int target) {
		Arrays.sort(nums);
	    List<List<Integer>> rst = new ArrayList<>();
	    if (nums == null || nums.length < 2) return rst;
	    
	    int left = 0, right = nums.length - 1;
	    while (left < right) {
	        int v = nums[left] + nums[right];
	        if (v == target) {
//	        	rst.add(Arrays.asList(left, right)); // 0-based
	            rst.add(Arrays.asList(left+1, right+1)); // 1-based
	            break;
	        } else if (v > target) {
	            right --;
	        } else {
	            left ++;
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
