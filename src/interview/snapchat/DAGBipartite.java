package interview.snapchat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DAGBipartite {
	public static void main(String[] args) {
	    DirectedGraphNode e = new DirectedGraphNode(5);
	    DirectedGraphNode f = new DirectedGraphNode(6);
	    DirectedGraphNode g = new DirectedGraphNode(7);
	    DirectedGraphNode h = new DirectedGraphNode(8);
	    e.neighbors.add(f);
	    e.neighbors.add(g);
	    f.neighbors.add(h);
	    g.neighbors.add(h);

	    // f.neighbors.add(g);
	    // c.neighbors.add(b);
	    // b.neighbors.add(d);
	    ArrayList<DirectedGraphNode> list1 = new ArrayList<DirectedGraphNode>();
	    list1.add(e);
	    list1.add(f);
	    list1.add(g);
	    list1.add(h);
	    boolean res2 = isBipartite(list1);
	    System.out.println(res2);
	}
	
	
	public static boolean isBipartite(ArrayList<DirectedGraphNode> graph) {
        if (graph == null || graph.size() < 3) return true;
        Set<DirectedGraphNode> color1 = new HashSet<>();    // always point to 
        Set<DirectedGraphNode> color2 = new HashSet<>();
        for (DirectedGraphNode node : graph) {
            if (!color1.contains(node) && !color2.contains(node)) {
                if (!paint(node, color1, color2)) return false;
            }
        }
        return true;
    }
    
    static boolean paint(DirectedGraphNode node, Set<DirectedGraphNode> color1, Set<DirectedGraphNode> color2){
        if (color2.contains(node)) return false;//染色错误 返回false
        color1.add(node); //染色
        for (DirectedGraphNode child : node.neighbors) {
            if (!color2.contains(child)) { // 判断是否已染色
                if (!paint(child, color2, color1)) return false; //dfs邻居节点 交换颜色
            }
        }
        return true;
    }
}

class DirectedGraphNode {
    int label;
    ArrayList<DirectedGraphNode> neighbors;
    DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
}
