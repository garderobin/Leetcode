package interview.snapchat;

import java.util.*;

/*
 * 没看懂输入是怎么回事
 * 面试中不可能出这么难的题目。两种方法：树和状态机
 * The first approach that can be used when parsing XML is to treat the entire document as a tree of nodes. This type of parser is called a DOM parser. I used a DOM parser on {insert relevant project experience}. We used a DOM parser because we needed to access different parts of the document at the same time.
 * The second approach that can be used when parsing XML is to treat the document as a stream of events or nodes. This type of approach is called a SAX parser, I used a SAX parser on {insert relevant project experience}. We used a SAX parser because we couldn't fit the entire document in memory.
 * http://stackoverflow.com/questions/6325436/algorithm-for-parsing-an-xml
 * http://www.cs.nmsu.edu/~epontell/courses/XML/material/xmlparsers.html
 */
public class XMLParser {
	public static void main(String[] args) {
		ArrayList<String> XML = new ArrayList<>();
		XML.add("start/p");
		XML.add("start/q");
		XML.add("inner/a");
		XML.add("end/q");
		XML.add("start/m");
		XML.add("inner/b");
		XML.add("end/m");
		XML.add("end/p");
		Iterator<String> tokenizer = XML.iterator();
		XMLnode treeXML = deserialize(tokenizer);
		print(treeXML);
		print2(treeXML,"");
		
	}

	   
    static class XMLnode {
	    String element;
	    String text;
	    ArrayList<XMLnode> content;
	    public XMLnode(String element, String text){
	        this.element = element;
	        this.text = text;
	        this.content = new  ArrayList<XMLnode>();
	    }
	}
	
	
    static XMLnode deserialize(Iterator<String> tokenizer) {		
  		String next = tokenizer.next();

  		String[] type = next.split("/");  		
        if (type[0].equals("end")) return null;
        String element = type[0].equals("start")? type[1] : "";
        String text = type[0].equals("inner")? type[1] : "";
        
        XMLnode root = new XMLnode(element,text);
        
        while (tokenizer.hasNext()) {
        	XMLnode node = deserialize(tokenizer);
            if(node!=null) {
            	root.content.add(node);
            	//XML的text都是叶节点 所以遇到text直接返回上层 text非空即是text节点
            	// System.out.println(node.text);
            	if(!node.text.equals("")) break;
            }
            else break;
		}
        return root;    
    }


    public static void print(XMLnode root) {
    	Queue<XMLnode> q = new LinkedList<XMLnode>();
    	q.offer(root);
    	while(!q.isEmpty()){
    		int size = q.size();
    		System.out.println("  ");
    		for(int i=0; i<size; i++ ){
    			XMLnode node = q.poll();
    			System.out.println(node.element+" "+node.text);
    			for(XMLnode n : node.content){
    				q.offer(n);
    			}
    		}
    	}
    }

    static void print2(XMLnode root, String prefix) {
    	System.out.println(prefix + "#"+root.element + " " + root.text);
		int n = root.content.size()-1;
		for(int i = 0; i <= n; i++){
			print2(root.content.get(i), prefix+"++");
		}		
	}
    
}
