package algorithm;

public class IncreasingTripletSequence {
	
	public boolean increasingTripletDiscussion(int[] nums) {
        // start with two largest values, as soon as we find a number bigger than both, while both have been updated, return true.
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= small) { small = n; } // update small if n is smaller than both
            else if (n <= big) { big = n; } // update big only if greater than small but smaller than big
            else return true; // return if you find a number bigger than both
        }
        return false;
    }
	
	/**
	 * 这题用左右加逼的方法反而很难做
	 * 这种判断递增或者递减类型的题，还是简化思维，只从左到右，或者只从右到左比较方便。
	 * @param nums
	 * @return
	 */
	public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) { return false; }
        int i = 0, j = -1, k = 1;
        for (; k < nums.length; k++) {
            if (j < 0) { 
                if (nums[k] > nums[i]) { j = k; }
                else { i = k;  }
            } else if (nums[k] > nums[j]) {  return true; }
            else if (nums[k] > nums[i]) { j = k; }
            else { i = k; }
        }
        
        return false;
    }
}
