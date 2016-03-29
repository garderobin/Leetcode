package interview;
//Implement a Map using Binary Search Tree. Implement the following methods:
//a) V get(K key)
//b) void put(K key, V value)
//c) int size()
public class ImplementHashMapUsingBST {
	public static void main(String[] args) {
        Map<String, String> map = new Map<String, String>();
        
        // null test
        // map.put(null, null);
        // System.out.println("Null value is: " + map.get(null));
        
        System.out.println(map.size());
        map.put("Airport", "LAX");
        System.out.println(map.size());
        map.put("Story", "My Story");

        System.out.println(map.size());
        map.put("Story", "Los Angeles");

        System.out.println(map.size());
        map.put("Friend", "Simon");

        System.out.println(map.size());
        map.put("App", "Snapchat");

        System.out.println(map.size());
        
        String fruit = map.get("Airport");
        System.out.println("Airport value is: " + fruit);
        
        String country = map.get("Story");
        System.out.println("Story value is: " + country);
        
        String planet = map.get("Friend");
        System.out.println("Friend value is: " + planet);
        
        String app = map.get("App");
        System.out.println("App value is: " + app);
        
    }

    public static class Map<K extends Comparable<K>, V>{
        MapNode<K, V> root;
        Map() {
        	root = new MapNode<K, V>(null, null);
        }
        public void put(K key, V value) {
            insertNode(root, key, value);   
        }
        public V get(K key){
            return getValueFromBST(root,key);
        }
        public int size(){
            return root.count - 1; //root是dummy node, 不应该算在内
        }
        
        private class MapNode<Key, Value> {
	        private Key key;
	        private Value value;
	        private int count;
	        private MapNode<Key, Value> left;
	        private MapNode<Key, Value> right;
	        
	        public MapNode(Key key, Value value) {
	            this.count = 1;
	            this.key = key;
	            this.value = value;
	        }
	
	    }

	    private V getValueFromBST(MapNode<K, V> root, K key) {
	        if (key == null) return null;
	        while (root != null) {
	            if 		(key.equals(root.key)) 			return root.value;
	            else if (key.compareTo(root.key) < 0) 	root = root.left;
	            else 									root = root.right;
	        }
	        return null;    
	    }
	
	    private MapNode<K, V> insertNode(MapNode<K, V> root, K key, V value) {       
	        if (root == null) {
	            root = new MapNode<K, V>(key, value);
	            return root;
	        }
	        int cmp = root.key.compareTo(key);
	        if 		(cmp > 0) 	root.left = insertNode(root.left, key, value);
	        else if (cmp < 0) 	root.right = insertNode(root.right, key, value);
	        else 				root.value = value;
	         
	        root.count = 1 + (root.left==null?0:root.left.count) + (root.right==null?0:root.right.count);
	        return root;
	    }
    
	 
    }
    
    
}
