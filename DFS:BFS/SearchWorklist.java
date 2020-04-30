package cse12pa3student;

import java.util.*;

/*
 * Class to implement SearchWorklist as a Stack and a Queue.
 * You can use any built-in Java collections for this class.
 */

class StackWorklist<E> implements SearchWorklist<E> {

    Stack<E> stacklist;

	
	public StackWorklist() {
		this.stacklist = (Stack<E>)(new Stack<Object>());
	}

	public void add(E c) {
		this.stacklist.push(c);
	
	}

	public E remove() {
		return this.stacklist.pop();
	}

	public boolean isEmpty() {
		return this.stacklist.isEmpty();
	}
}

class QueueWorklist<E> implements SearchWorklist<E> {

	ArrayList<E> qlist;

	public QueueWorklist() {
		this.qlist = (ArrayList<E>)(new ArrayList<Object>());
	}

	public void add(E c) {
		this.qlist.add(c);
	}

	public E remove() {
		return this.qlist.remove(0);
	}

	public boolean isEmpty() {
		return this.qlist.isEmpty();
	}
}

public interface SearchWorklist<E>{
	void add(E c);

	E remove();

	boolean isEmpty();
}
