package algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueues {
	Queue<Integer> queue;

    public ImplementStackUsingQueues()
    {
        this.queue=new LinkedList<Integer>(); //这里只能用LinkedList不能用PriorityQueue
    } //因为priorityQueue会根据插入值进行排序。

    // Push element x onto stack.
    public void push(int x) 
    {
       queue.add(x);
       for(int i=0;i<queue.size()-1;i++)
       {
           queue.add(queue.poll()); 
       }
    }

    // Removes the element on top of the stack.
    public void pop() 
    {
        queue.poll();
    }

    // Get the top element.
    public int top() 
    {
        return queue.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() 
    {
        return queue.isEmpty();
    }
    
    public static void main(String[] args) {
    	ImplementStackUsingQueues stack = new ImplementStackUsingQueues();
    	stack.push(10);
    	stack.push(2);
    	System.out.println(stack.top());
    }
}
