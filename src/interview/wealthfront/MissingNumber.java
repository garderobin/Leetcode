package interview.wealthfront;

public class MissingNumber {
	public int missingNumber(int[] nums) {
        int actual = 0;
        int expect = 0;
        for (int i = 0; i < nums.length; i++) {
            actual += nums[i];
            expect += i + 1;
        }
        return expect - actual;
    }
}
