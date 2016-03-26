package interview.snapchat;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * Coding：有一个Person类，里面有这个Person的friends，List<Person> friends. 面试之前在地里看过这道题的面经。。
 * 面试官举了一个例子，输入是一个person，输出是这个person的所有的朋友。朋友的朋友也是朋友。就是图的遍历。
 * 马上dfs写完了，面试官说要求先输出和这个person关系近的朋友，然后就改成的bfs，但是处理重复的时候有个小bug，改了一会才改好。应该挂在这了。
 */
public class PersonProblem {
	public static void main(String[] args) {
		Person Mike = new Person(1,"Mike");
		Person Mitch = new Person(2,"Mitch");
		Person Ryan = new Person(3,"Ryan");
		Person Lila = new Person(4,"Lila");
		Mike.friends.add(Mitch);
		Mike.friends.add(Ryan);
	    
		Mitch.friends.add(Mike);
		Mike.friends.add(Lila);
	    
		System.out.println(Mike.getFriendNames());
	}
	
}

class Person{
	 int id;
	 String name;
	 List<Person> friends;
	 
	 
	 public Person(int id, String name){
		 this.id = id;
	     this.name = name;
	     this.friends = new ArrayList<Person>();
	 }
	 
	 /*
	  * BFS generate a person's friends from close to not close.
	  */
	 public List<String> getFriendNames(){
		 List<String> res = new ArrayList<String>();
		 Set<Integer> visited = new HashSet<Integer>();
		 Queue<Person> friendGraph = new ArrayDeque<Person>();
		 
		 visited.add(this.id);
		 friendGraph.offer(this);
		 
		 while (!friendGraph.isEmpty()) {
			 Person person = friendGraph.poll();       
			 for (Person friend : person.friends) {
				 if (!visited.contains(friend.id)) {
					 res.add(friend.name);
					 visited.add(friend.id);
					 friendGraph.offer(friend);
				 }
			 }
		 }
	    
		 return res;
	}
	 
	 
}
