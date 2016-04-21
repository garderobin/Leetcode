package interview.snapchat;
import java.util.*;

public class AlienDictionary {
	
	
	public String alienOrder(String[] words) {
	    Map<Character, List<Character>> graph = new HashMap<>();
	    String ans = null;
	    Set<Character> charSet = new HashSet<>(); //Maintain a set of chars that are not in the graph.
	    buildGraph(words, graph, charSet);
	    ans = toposortDFS(graph, charSet);
	    return ans;
	}

	private Set<Character> getCharSet(String[] words, Set<Character> charSet) {
	    for (String word : words) {
	        for (char c : word.toCharArray()) {
	            charSet.add(c);
	        }
	    }
	    return charSet;
	}

	private String toposortDFS(Map<Character, List<Character>> graph, Set<Character> charSet) {
	    StringBuffer ans = new StringBuffer();
	    boolean[] visited = new boolean[26]; //permanent marker.
	    boolean[] tempMark = new boolean[26]; //temporary marker

	    for (Character v : graph.keySet()) {
	        if (!visited[v - 'a']) {
	            if (!visitDFS(ans, graph, visited, v, tempMark)) {
	                return "";
	            }               
	        }   
	    }

	    for (Character c : charSet) { //insert orphan chars.
	        ans.insert(0, c);
	    }
	    return ans.reverse().toString();
	}

	private boolean visitDFS(StringBuffer ans, Map<Character, List<Character>> graph, boolean[] visited,
	        Character node, boolean[] marked) {
	    if (marked[node - 'a']) {  //if temporally marked already, it is not DAG. return failure(false);
	        return false;
	    } 
	    if (!visited[node - 'a']) {
	        marked[node - 'a'] = true; //mark the current node temporally.
	        List<Character> adjList = graph.get(node);
	        if (adjList != null) {
	            for (Character m : graph.get(node)) {
	                if (!visitDFS(ans, graph, visited, m, marked))
	                    return false;
	            }
	        }
	        //there is no adjacent node, it is a leaf, output the node(char).
	        visited[node - 'a'] = true; //mark the node permanently.
	        marked[node - 'a'] = false; //remove the temp mark.
	        ans.append(node);
	    }
	    return true;
	}

	private void buildGraph(String[] words, Map<Character, List<Character>> graph, Set<Character> charSet) {

	    getCharSet(words, charSet); //Find all chars in the words.

	    for (int i = 0; i < words.length - 1; i++) {
	        for (int j = i + 1; j < words.length; j++) {
	            for (int k = 0; k < Math.min(words[i].length(), words[j].length()); k++) {
	                if (words[i].charAt(k) != words[j].charAt(k)) {

	                    charSet.remove(words[i].charAt(k)); //Remove the chars that are in the graph from charSet.
	                    charSet.remove(words[j].charAt(k));

	                    if (graph.containsKey(words[i].charAt(k))) {
	                        List<Character> adjList = graph.get(words[i].charAt(k));
	                        if (!adjList.contains(words[j].charAt(k)))
	                            graph.get(words[i].charAt(k)).add(words[j].charAt(k));
	                    } else {
	                        List<Character> adjList = new ArrayList<Character>();
	                        adjList.add(words[j].charAt(k));
	                        graph.put(words[i].charAt(k), adjList);
	                    }
	                    break;
	                }
	            }
	        }
	    }
	}
	
	//bfs
	public String alienOrderBFS(String[] words) {
	    Map<Character, Set<Character>> map=new HashMap<Character, Set<Character>>();
	    Map<Character, Integer> degree=new HashMap<Character, Integer>();
	    String result="";
	    if(words==null || words.length==0) return result;
	    for(String s: words){
	        for(char c: s.toCharArray()){
	            degree.put(c,0);
	        }
	    }
	    for(int i=0; i<words.length-1; i++){
	        String cur=words[i];
	        String next=words[i+1];
	        int length=Math.min(cur.length(), next.length());
	        for(int j=0; j<length; j++){
	            char c1=cur.charAt(j);
	            char c2=next.charAt(j);
	            if(c1!=c2){
	                Set<Character> set=new HashSet<Character>();
	                if(map.containsKey(c1)) set=map.get(c1);
	                if(!set.contains(c2)){
	                    set.add(c2);
	                    map.put(c1, set);
	                    degree.put(c2, degree.get(c2)+1);
	                }
	                break;
	            }
	        }
	    }
	    Queue<Character> q=new LinkedList<Character>();
	    for(char c: degree.keySet()){
	        if(degree.get(c)==0) q.add(c);
	    }
	    while(!q.isEmpty()){
	        char c=q.remove();
	        result+=c;
	        if(map.containsKey(c)){
	            for(char c2: map.get(c)){
	                degree.put(c2,degree.get(c2)-1);
	                if(degree.get(c2)==0) q.add(c2);
	            }
	        }
	    }
	    if(result.length()!=degree.size()) return "";
	    return result;
	}
	
	
	
	
	
//	public String alienOrder(String[] words) {
//        int[] pos = new int[26]; 
//        int unmergedSub = 0;
//        for (String s: words) {
//        	int start = unmergedSub * 26 + 1;
//        	boolean metFamiliar = false;
//        	for (int i = 0, len = s.length(); i < len; ++i) {
//        		int index = s.charAt(i) - 'a';
//        		if (pos[index] == 0) {
//        			
//        		} else {
//        			if (metFamiliar) {
//        				
//        			} else {
//        				
//        				metFamiliar = true;
//        			}
//        			
//        		}
//        	}
//        }
//        
//        // counting sort
//        
//        return "";
//    }
}
