package WayFair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WayFariOA {
	
	public static List<List<String>> wordCombinations2(List<List<String>> words) {
		if (words == null || words.size() == 0) { return Collections.emptyList(); }
		// Do not allow empty sub-list
		for (List<String> wl: words) { 
			if (wl == null || wl.size() == 0) { return Collections.emptyList(); } 
		}
		
		Set<List<String>> prev = new HashSet<List<String>>(), cur;
		Iterator<List<String>> iter = words.iterator();
		for (String s: iter.next()) { prev.add(Collections.singletonList(s)); }
		
		while (iter.hasNext()) {
			List<String> next = iter.next();
			cur =  new HashSet<>();
			for (List<String> l1: prev) {
				for (String s2 : next)  { 
					List<String> nl = new ArrayList<String>(l1);
					nl.add(s2);
					cur.add(nl); 
				}        
		    }
		    prev = cur;
		 }
		 List<List<String>> rst = new ArrayList<>();
		 rst.addAll(prev);
		 return rst;
	}
	
	public static List<String> wordCombinations3(List<List<String>> words) {
		if (words == null || words.size() == 0) { return Collections.emptyList(); }
		// Do not allow empty sub-list
		for (List<String> wl: words) { 
			if (wl == null || wl.size() == 0) { return Collections.emptyList(); } 
		}
		Set<String> prev = new HashSet<String>(), cur;
		Iterator<List<String>> iter = words.iterator();
		prev.addAll(iter.next());

		while (iter.hasNext()) {
			List<String> next = iter.next();
			cur =  new HashSet<String>();
			for (String s1: prev) {
				for (String s2 : next)  { cur.add(s1 + " " + s2); }        
		    }
		    prev = cur;
		 }
		 List<String> rst = new ArrayList<String>();
		 rst.addAll(prev);
		 return rst;
	}
	
	public static List<String> wordCombinations(List<List<String>> words) {
		 Set<String> prev = new HashSet<String>();
		 Set<String> cur;
		 LinkedList<List<String>> lists = new LinkedList<>();
		 lists.addAll(words);
//		 for (String s: lists.removeFirst()) { prev.add(s); }
		 prev.addAll(words.get(0));
		 lists.remove(0);

		 while (!lists.isEmpty()) {
		        List<String> next = lists.removeFirst();
		        cur =  new HashSet<String>();
		        for (String s1: prev) 
		            for (String s2 : next) 
		              cur.add(s1 + " " + s2);               

		        prev = cur;
		 }
		 List<String> rst = new ArrayList<String>();
		 rst.addAll(prev);
		 return rst;
	}
	
	public static void main(String[] args) {
		List<List<String>> words = new ArrayList<List<String>>();
		words.add(Arrays.asList("quick", "lazy"));
		words.add(Arrays.asList("brown", "black", "grey"));
		words.add(Arrays.asList("fox", "dog"));
		List<String> rst = wordCombinations3(words);
		System.out.println(rst);
	}
	
//	public static List<List<String>> combinationWords(List<List<String>> words) {
//		if (words == null) { return Collections.emptyList(); }
//		int n = words.size(), maxSubLen = 0;
//		for (List<String> wl: words) { maxSubLen = Math.max(maxSubLen, wl.size()); }
//		Set<List<String>> set = new HashSet<List<String>>();
//		@SuppressWarnings("unchecked")
//		PriorityQueue<String>[][] dp = new PriorityQueue[n][maxSubLen];
//		PriorityQueue<PriorityQueue<String>> prev = new PriorityQueue<>();
//		List<String> wl = words.get(0);
//		for (int j = 0, len = wl.size(); j < len; ++j) {
//			dp[0][j] = new PriorityQueue<String>();
//			dp[0][j].offer(wl.get(j));
//			prev.offer(dp[0][j]);
//		}
//		for (int i = 0; i < n; ++i) {
//			
//		}
//	}
	
	public static List<List<Integer>> combination(int k, int[] nums) {
		if (nums == null || k == 0 || nums.length < k) { return Collections.emptyList(); }
		else if (k == nums.length) {
			List<Integer> list = new ArrayList<Integer>();
			for (int e: nums) { list.add(e); }
			return Collections.singletonList(list);
		} else { 
			Arrays.sort(nums);
			Set<List<Integer>> set = dfs(k, nums, 0, new ArrayList<Integer>(), new HashSet<List<Integer>>());
			List<List<Integer>> rst = new ArrayList<List<Integer>>();
			rst.addAll(set);
			return rst;
		}
		
	}
	
	/**
	 * Backtracking.
	 */
	private static Set<List<Integer>> dfs(int k, int[] nums, int startIndex, List<Integer> list, Set<List<Integer>> set) {
		if (list.size() == k) {
			set.add(list);
			return set;
		}else {
			for (int i = startIndex; i < nums.length; ++i) {
				List<Integer> cur = new ArrayList<Integer>(list);
				cur.add(nums[i]);
				dfs(k, nums, i+1, cur, set);
			}
			return set;
		}
	}
	
	
	public static List<List<Integer>> combinationIterative(int k, int[] nums) {
		if (nums == null || k == 0 || nums.length < k) { return Collections.emptyList(); }
		else if (k == nums.length) {
			List<Integer> list = new ArrayList<Integer>();
			for (int e: nums) { list.add(e); }
			return Collections.singletonList(list);
		} else {
			Arrays.sort(nums);
			List<List<Integer>> posComb = combinePos(nums.length, k), rst = new ArrayList<>();
			Set<List<Integer>> set = new HashSet<>();
			for (List<Integer> posList: posComb) {
				List<Integer> vList = new ArrayList<Integer>();
				for (int pos: posList) { vList.add(nums[pos-1]); }
				set.add(vList); // use set to filter duplicate value combinations 
			}
			rst.addAll(set);
			return rst;
		}
		
	}
	
	/**
	 * Get combinations of positions
	 */
	private static List<List<Integer>> combinePos(int n, int k) {
	    if(n < 1 || k < 1 || k > n)
	        return Collections.emptyList();

	    /** c(n, k) = c(n-1, k) + f(n, c(n-1, k-1)) **/
	    @SuppressWarnings("unchecked")
	    List<List<Integer>>[][] cache = new List[n + 1][k + 1];
	    /** init cache of k == 0 **/
	    for(int i = 0; i <= n; i++){
	        cache[i][0] = new ArrayList<>();
	        cache[i][0].add(new ArrayList<Integer>());
	    }

	    for(int i = 1; i <= n; i++){
	        for(int j = 1; j <= i && j <= k; j++){
	            /** combine(i, j) **/
	            cache[i][j] = new ArrayList<>();
	            if(i - 1 >= j)
	                cache[i][j].addAll(cache[i - 1][j]);
	            for(List<Integer> list: cache[i - 1][j - 1]){
	                List<Integer> tmpList = new LinkedList<>(list);
	                tmpList.add(i);
	                cache[i][j].add(tmpList);
	            }
	        }
	    }

	    return cache[n][k];
	}
	
	/**
	 * By Pengpeng.
	 */
	public static List<List<Integer>> threeSum(int[] nums, int n) {
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		int length = nums.length;
		if(length < 3){
			return result;
		}
		for(int i = 0; i < length-2;i++){
			if(i!=0&&nums[i] == nums[i-1]) continue;
		    int target = n - nums[i];
		    int l = i+1;
		    int r = length-1;
		    while(l<r){
		        if(nums[l]+nums[r] == target){
                    result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    while(l<r&&nums[l]==nums[l-1]) l++;
                    while(l<r&&nums[r]==nums[r+1]) r--;
                    
		        }else if(nums[l]+nums[r]<target){
		            l++;
		        }else{
		            r--;
		        }
		    }
		    
		}
		
		return result;

	}
	
	
	
	    public List<List<Integer>> combinationSum3(int k, int n) {
	        List<List<Integer>> lists = new ArrayList<List<Integer>>();
	        
	        int[] nums = new int[k];
	        // Stack<Integer> stack2 = new Stack<Integer>();
	        int sum = 0;
	        int count = 0;
	        for(int i=1; i<=k; i++){
	            nums[i-1] = i;
	            count ++;
	            sum += i;
	            if(sum>n)
	                return lists;
	        }
	        if(sum == n){
	            List<Integer> list = new ArrayList<Integer>();
	            int i=0;
	            while(i<k){
	                list.add(nums[i]);
	                i++;
	            }
	            lists.add(list);
	            return lists;
	        }
	        while(count!=0){
	            if(count==k){
	                if(sum<n){
	                    if(nums[k-1]<9){
	                        nums[k-1] ++;
	                        sum ++;
	                    }
	                    else{
	                        sum -= nums[k-1];
	                        count--;
	                    }
	                }
	                else if(sum>n){
	                    sum -= nums[k-1];
	                    count--;
	                }
	                else{
	                    List<Integer> list = new ArrayList<Integer>();
	                    int i=0;
	                    while(i<k){
	                        list.add(nums[i]);
	                        i++;
	                    }
	                    lists.add(list);
	                    sum -= nums[k-1];
	                    count--;
	                }
	            }
	            else{
	                // int temp = stack.pop();
	                if(nums[count-1]<9-k+count){
	                    nums[count-1] ++;
	                    sum ++;
	                    if(sum>=n){
	                        sum -= nums[count-1];
	                        count--;
	                    }
	                    else{
	                        if(count==k-1){
	                            // stack.push(temp+2);
	                            nums[count] = nums[count-1]+1;
	                            sum += nums[count];
	                            count ++;
	                        }
	                        else{
	                            // stack.push(temp+1);
	                            nums[count] = nums[count-1];
	                            sum += nums[count];
	                            count ++;
	                        }
	                    }
	                }
	                else{
	                    sum -= nums[count-1];
	                    count--;
	                }
	            }
	        }
	        return lists;
	        
	    }
	    
}
