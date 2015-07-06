package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
	
	class Node {
		boolean toLeft;
		int val;
		
		public Node() {
		}
		
		public Node(int val) {
			toLeft = true;
			this.val = val;
		}
		
		public Node(boolean toLeft, int val) {
			this.toLeft = toLeft;
			this.val = val;
		}
		
		public Node(Node node) {
			this.toLeft = node.toLeft;
			this.val = node.val;
		}
	}
	
	public List<List<Integer>> permuteByJohnsons(int[] nums) {
		List<List<Integer>> rst = new ArrayList<List<Integer>>();

        if (nums == null || nums.length == 0) {
        	return rst;
        }
        
        List<Node> nlist = new ArrayList<Node>(nums.length);        
        
        Node cmm = new Node(nums[0]);
        nlist.add(new Node(cmm));  
        //int maxVal = cmm.val;
        // Initialize the nodes with all to left direction and find the max mobile node for the first time.
        for (int i = 1; i < nums.length; i++) {
        	Node cur = new Node(nums[i]);
        	nlist.add(cur);
        	if (nums[i] > cmm.val) {
        		cmm = cur;
        		//maxVal = cur.val;
        	}
        }            
        rst.add(nodeListToIntList(nlist));
        
        Node max;
        List<Integer> ilist;
        do {
        	nlist = move(cmm, nlist);
        	ilist = nodeListToIntList(nlist);
        	rst.add(ilist);

        	// Change weathervane for all nodes that are larger than current cmm.
        	max = findMax(cmm, nlist);        	
        	if (max.val > cmm.val) {
        		cmm = max;
        	} else if (!nodeMovable(cmm, nlist)) {
        		cmm = findCMM(nlist);  
        	} // else , cmm is max and movable, continue;
        	
        } while (cmm != null);

		return rst;
	}
	
	private boolean nodeMovable(Node cmm, List<Node> nlist) {
		int index = -1;
		Node node;
		for (int i = 0; i < nlist.size(); i++) {
			node = nlist.get(i);
			if (node.val == cmm.val && node.toLeft == cmm.toLeft) {
				index = i;
			}
		}
		if (cmm.toLeft) {
			return (index > 0) && nlist.get(index - 1).val < cmm.val;
		} else {
			return (index < nlist.size() - 1) && nlist.get(index + 1).val < cmm.val;
		}
		
	}
	
	private Node findCMM(List<Node> nlist) {
		Node cmm = new Node(Integer.MIN_VALUE);
		for (Node element: nlist) {
			if (nodeMovable(element, nlist) && element.val > cmm.val) {
				cmm = element;
			}
		}
		if (cmm.val < 0) {
			cmm = null;
		}
		return cmm;
	}
	
	private Node findMax(Node cmm, List<Node> nlist) {
		Node mnode = new Node(Integer.MIN_VALUE);
		for (Node element: nlist) {
			if (element.val > cmm.val) {
				element.toLeft = !(element.toLeft);
			}
			if (element.val > mnode.val) {
				mnode = element;
			}
		}
		return mnode;
	}
	
	private List<Node> move(Node cmm, List<Node> nlist) {
		int index = -1;
		Node node;
		for (int i = 0; i < nlist.size(); i++) {
			node = nlist.get(i);
			if (node.val == cmm.val && node.toLeft == cmm.toLeft) {
				index = i;
			}
		}
		Node tmp;
		if (cmm.toLeft) {
			tmp = new Node(nlist.get(index - 1));			
			nlist.set(index - 1, new Node(cmm));
			nlist.set(index, tmp);
		} else {
			tmp = new Node(nlist.get(index + 1));			
			nlist.set(index + 1, new Node(cmm));
			nlist.set(index, tmp);
		}
		return nlist;
	}
	
	private List<Integer> nodeListToIntList(List<Node> nlist) {
		List<Integer> ilist = new ArrayList<Integer>(nlist.size());
		for (Node element: nlist) {
			ilist.add(element.val);
		}		
		return ilist;
	}

	public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if (nums == null || nums.length == 0) {
        	return res;
        }
        
        List<Integer> row0 = new ArrayList<Integer>(1);
        row0.add(nums[0]);
        
        if (nums.length == 1) {        	
        	res.add(row0);
        	return res;
        }

		return permuteSub(nums, nums.length - 1);

    }
	
	public static List<List<Integer>> permuteSub(int[] nums, int end) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (end == 1) {
			List<Integer> row1 = new ArrayList<Integer>(2);
		    row1.add(nums[0]);        
		    row1.add(nums[1]);
		    
		    List<Integer> row2 = new ArrayList<Integer>(2);
		    row2.add(nums[1]);        
		    row2.add(nums[0]);
		    
		    res.add(row1);
		    res.add(row2);
		    return res;
		}
		List<List<Integer>> prev = permuteSub(nums, end - 1);
		int len, i, j;
		int size = prev.size();
		for (i = 0; i < size; i++) { // for each row in f(n-1)
			List<Integer> r = prev.get(i);
			len = r.size();
			for (j = 0; j <= len; j++) {
				List<Integer> r2 = new ArrayList<Integer>(r);
				r2.add(j, nums[end]);
				res.add(r2);
			}
		}
		return res;
		
	}
	
	public static void main(String[] args) {
		int[] test = {1,2,3,4};
		Permutations p = new Permutations();
		List<List<Integer>> triangle = p.permuteByJohnsons(test);
		for (int i = 0; i < triangle.size(); i++) {
			ArrayList<Integer> cur = (ArrayList<Integer>) triangle.get(i);
			System.out.print("[");
			for (int j = 0; j < cur.size(); j++) {
				System.out.print(cur.get(j) + ",\t");
			}
			System.out.println("]");
		}
	}
	
	
	
//	public static List<List<Integer>> helper(List<List<Integer>> rst, List<Integer> list, int[] num){
//        if(list.size() == num.length) {        	
//            rst.add(new ArrayList<Integer>(list));
//            return rst;
//        }
//        
//        for(int i = 0; i < num.length; i++){
//            if(list.contains(num[i])){
//                continue;
//            }
//            list.add(num[i]);
//            
//            System.out.print("\nbefore another helper(),  i = " + i + ", list = ");
//            for (int k = 0; k < list.size(); k++) {
//            	System.out.print(list.get(k) + "\t");
//            }
//            System.out.print("\nrst = ");
//    		for (int m = 0; m < rst.size(); m++) {
//				ArrayList<Integer> cur = (ArrayList<Integer>) rst.get(m);
//				System.out.print("[");
//				for (int j = 0; j < cur.size(); j++) {
//					System.out.print(cur.get(j) + ",\t");
//				}
//				System.out.println("]");
//			}
//            
//            
//            helper(rst, list, num);
//            
//            System.out.print("\nAfter helper before remove,  i = " + i + ", list = ");
//            for (int k = 0; k < list.size(); k++) {
//            	System.out.print(list.get(k) + "\t");
//            }
//
//            
//            list.remove(list.size() - 1);
//            
//            System.out.print("\nRemove done,  i = " + i + ", list = ");
//            for (int k = 0; k < list.size(); k++) {
//            	System.out.print(list.get(k) + "\t");
//            }
//        }
//        
//        return rst;
//    }
	
	public static List<List<Integer>> permuteII(int[] nums) {
		List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return rst; 
        }

        ArrayList<Integer> list = new ArrayList<Integer>();
        return helper(rst, list, nums);

    }
	
	public static List<List<Integer>> helper(List<List<Integer>> rst, List<Integer> list, int[] num){
        if(list.size() == num.length) {        	
            rst.add(new ArrayList<Integer>(list));
            return rst;
        }
        
        for(int i = 0; i < num.length; i++){
            if(list.contains(num[i])){
                continue;
            }
            list.add(num[i]);
                   
            helper(rst, list, num);
            
            list.remove(list.size() - 1);
            
        }
        
        return rst;
    }
	
	
}
