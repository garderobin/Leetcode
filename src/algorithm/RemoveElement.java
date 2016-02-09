package algorithm;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * 
 * @author jasmineliu
 *
 */
public class RemoveElement {
	public static int removeElement(int[] nums, int val) {
		int len = nums.length;
		//int temp;
		for (int i = 0; i < len; i++) {
			if (nums[i] == val) {
				//temp = nums[i];
				nums[i] = nums[len - 1];
				//nums[len] = temp;
				len--;
				i--;
			}
		}

		return len;
    }
}
