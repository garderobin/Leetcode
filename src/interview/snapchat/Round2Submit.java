package interview.snapchat;

public class Round2Submit {

	// This is the text editor interface. 
	// Anything you type or change here will be seen by the other person in real time.

	// implement a map using binary search tree

	//V get(K key)
	//void put(K key, V value)
	//int size()
	
	public static void main(String[] args) {
		MapUseBST<String, String> map = new MapUseBST<String, String>();
	        // empty cases
	        // map.put(null, null);
	        // System.out.println("NULL case : " + map.get(null));
	        
	        map.put("Country", "US");
	        map.put("App", "Snapchat");
	        map.put("Fruit", "Apple");
	        map.put("Fruit", "Mango");
	        
	        String fruit = map.get("Fruit");
	        String country = map.get("Country");
	        String app = map.get("App");
	        String notExist = map.get("Market");
	        
	        System.out.println(fruit);
	        System.out.println(country);
	        System.out.println(app);
	        System.out.println(notExist); 
	        System.out.println(map.size());
	    }

}	
	class MapUseBST<K extends Comparable<K>, V>{
	    // private Node[] nodes;
	    // private static final int MAX_CAPACITY = 41;
	    Node<K, V> root;
	    int size;
	    
	    public MapUseBST() {
	        root = null;
	        size = 0;
	    }
	    
	    public V get(K key) {
	        return getValueFromBST(root, key);
	    }
	    
	    public void put(K key, V val) {
	        addToBST(new Node<K, V>(key, val));
	    }
	    
	    public int size() {
	        return size;
	        // return getBSTSize(root, 0);
	    }
	    
	    private Node<K, V> addToBST(Node<K, V> newNode) {
	        if (this.root == null) {
	            // System.out.println("enter addToBST: "+ newNode.val);
	            ++size;
	            this.root = newNode;
	            return newNode;
	        }
	        Node<K, V> currentNode = root, parentNode = root;
	        
	        while (true) {
	            parentNode = currentNode;
	            int cmp = newNode.key.compareTo(currentNode.key);
	            if (cmp == 0) {
	                currentNode.val = newNode.val;
	                return root;
	            } else if (cmp < 0) {
	                currentNode = currentNode.left;
	                if (currentNode == null) {
	                    parentNode.left = newNode;
	                    ++size;
	                    return root;
	                } else {}
	               
	            } else { // cmp > 0
	                currentNode = currentNode.right;
	                if (currentNode == null) {
	                    parentNode.right = newNode;
	                    ++size;
	                    return root;
	                }
	                
	            }
	        }
	    }
	    
	    private V getValueFromBST(Node<K, V> root, K key) {
	        if (key == null || root == null) return null;
	        while (root != null) {
	            int cmp = key.compareTo(root.key);
	            if (cmp == 0)       return root.val;
	            else if (cmp < 0)   root = root.left;
	            else                root = root.right;
	        }
	        return null;
	    }
	    
	    @SuppressWarnings({ "unused" })
		private int getBSTSize(Node<K, V> root, int curSize) {
	        if (root == null) return curSize;
	        int leftSize = getBSTSize(root.left, curSize + 1);
	        int rightSize = getBSTSize(root.right, curSize + 1);
	        return curSize + leftSize + rightSize;
	    }
	    
	    private static class Node<K extends Comparable<K>, V> {
	    // private static class Node {
	        K key;
	        V val;
	        Node<K, V> left;
	        Node<K, V> right;
	        
	        public Node(K key, V val) {
	            this.key = key;
	            this.val = val;
	        }
	    }
	}
