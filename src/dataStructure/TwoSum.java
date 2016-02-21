package dataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * The big data test only have the condition that lots of add and few find. In fact, 
 * there has to be one operation's time complexity is O(n) and the other is O(1), no matter add or find. 
 * So clearly there's trade off when solve this problem, prefer quick find or quick add.
 */
public class TwoSum {
	
	/**
	 * Solution 1: O(1) add, O(n) find.
	 * 巧妙之处就是list的使用免得find的时候对map进行遍历。因为对list遍历的效率总归是比对map遍历的效率高。
	 */
	private List<Integer> list = new ArrayList<Integer>();
    private Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    /* Add the number to an internal data structure. */
    public void add(int number) {
        if (map.containsKey(number)) map.put(number, map.get(number) + 1);
        else {
            map.put(number, 1);
            list.add(number);
        }
    }

    /* Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (int i = 0; i < list.size(); i++){
            int num1 = list.get(i), num2 = value - num1;
            if ((num1 == num2 && map.get(num1) > 1) || (num1 != num2 && map.containsKey(num2))) return true;
        }
        return false;
    }
}

/**
 * Solution 2: O(n) add, O(1) find.
 * @author jasmineliu
 *
 */
class TwoSumSolution2 {
	
}