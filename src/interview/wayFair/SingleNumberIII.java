package interview.wayFair;

import java.util.HashSet;
import java.util.Iterator;

public class SingleNumberIII {
	/**
	 * O(1) space.
	 * 需要问。
	 * @param nums
	 * @return
	 */
	public int[] singleNumberDiscussion(int[] nums) {
        // Pass 1 : 
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        // Get its last set bit
        diff &= -diff;

        // Pass 2 :
        int[] rets = {0, 0}; // this array stores the two numbers we will return
        for (int num : nums)
        {
            if ((num & diff) == 0) // the bit is not set
            {
                rets[0] ^= num;
            }
            else // the bit is set
            {
                rets[1] ^= num;
            }
        }
        return rets;
    }
	
	public static int[] singleNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        int[] rst = new int[2];
        for(int e: nums) {
        	if (!set.remove(e)) {
        		set.add(e);
        	}
        }
        Iterator<Integer> it = set.iterator();
        for (int i = 0; i < 2; i++) {
            rst[i] = it.next();
        }
        return rst;
        
    }
	
	public static void main(String[] args) {
		int[] nums = {1, 2, 1, 3, 2, 5};
		int[] rst = singleNumber(nums);
		System.out.println(rst[0] + "\t" + rst[1]);
	}
}
