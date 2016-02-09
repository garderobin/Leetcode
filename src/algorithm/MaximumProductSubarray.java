package algorithm;

public class MaximumProductSubarray {
	
	/**
	 * O(1) space, O(n) time.
	 * 极其美妙, 需要反复多看！
	 * @param nums
	 * @return
	 */
	public int maxProductDiscussion(int[] nums) {
	    if (nums.length == 0) { return 0; }
	    int preMax = nums[0], preMin = nums[0], maxProduct = nums[0], curMax, curMin;

	    for (int i = 1; i < nums.length; i++) {
	        curMax = Math.max(Math.max(preMax * nums[i], preMin * nums[i]), nums[i]);
	        curMin = Math.min(Math.min(preMax * nums[i], preMin * nums[i]), nums[i]);
	        maxProduct = Math.max(curMax, maxProduct);
	        preMax = curMax;
	        preMin = curMin;
	    }
	    return maxProduct;
	}
	
	/**
	 * 我都忘了这是我什么时候做的奇葩答案了= =
	 * 思路也不记得了T_T
	 * @param nums
	 * @return
	 */
	public static int maxProductV1(int[] nums) {
		int maxp = nums[0], p = 1, pleft = 1, pright = 1, count = 0;
        boolean flag = false;
        for(int i: nums){
            if(i == 0){
                if(count == 0){
                    maxp = Math.max(maxp, 0);
                }else if(count == 1){
                    p = Math.max(p, 0);
                    maxp = Math.max(maxp, p);
                }else{
                    if(flag){
                        pright = p / pright;
                        pleft = p / pleft;
                        pleft = Math.max(pleft, pright);
                        p = Math.max(pleft, p);
                   }
                   maxp = Math.max(maxp, p);
                }
                flag = false;   
                pleft = pright = p = 1;
                count = 0;
            } else if (i < 0){
                ++count;
                if (!flag) {
                    flag = true;
                    pleft = pleft * i;
                }
                p *= (pright = i);
            } else {
                ++count;
                if (flag) { 
                	pright *= i; 
                }
                else { 
                	pleft *= i; 
                }
                p *= i;
            }
        }
        if (count == 0) { return Math.max(maxp, 0); }
        if (count == 1) { return Math.max(maxp, p); }
        if (flag) {
            pleft = p / pleft;
            pright = p / pright;
            pleft = pleft > pright? pleft: pright;
            p = p > pleft? p: pleft;
        }
        return Math.max(maxp, p);
    
    }
	
	/**
	 * 我都忘了这是我什么时候做的奇葩答案了= =
	 * 思路也不记得了T_T
	 * @param nums
	 * @return
	 */
	public static int maxProductV1ForTest(int[] nums) {
		int maxp = nums[0];
        int p = 1;
        int pleft = 1;
        int pright = 1;
        boolean flag = false;
        int count = 0;
        for(int i: nums){
            if(i == 0){
                if(count == 0){
                    maxp = Math.max(maxp, 0);
                }else if(count == 1){
                    p = Math.max(p, 0);
                    maxp = Math.max(maxp, p);
                }else{
                    if(flag){
                        pright = p / pright;
                        pleft = p / pleft;
                        pleft = Math.max(pleft, pright);
                        p = Math.max(pleft, p);
                   }
                   maxp = Math.max(maxp, p);
                }
                flag = false;   
                pleft = 1;
                pright = 1;
                p = 1;
                count = 0;
            }else if(i < 0){
                ++count;
                if(!flag){
                    flag = true;
                    pleft = pleft * i;
                }
                pright = i;
                p = p * i;
            }else{
                ++count;
                if(flag){
                    pright = pright * i;
                }else{
                    pleft  = pleft * i;
                }
                p = p * i;
            }
        }
        if(count == 0){
            return Math.max(maxp, 0);
        }
        if(count == 1){
            return Math.max(maxp, p);
        }
        if(flag){
            pleft = p / pleft;
            pright = p / pright;
            pleft = pleft > pright? pleft: pright;
            p = p > pleft? p: pleft;
        }
        return Math.max(maxp, p);
    
    }
	
}
