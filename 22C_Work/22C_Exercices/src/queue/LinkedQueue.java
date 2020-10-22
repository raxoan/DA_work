/**
 * @author Aaron Wong
 * CIS 22C - Queue Exercise 1
 * LinkedQueue.java
 */
package queue;

public class LinkedQueue<T> implements QueueInterface<T> {
	/////////////////
	/* Inner Class */
	@SuppressWarnings("hiding")
	public class Node<T> {
		
		/* Variables */
		private T data; // this Node stores generic T as its datatype
		private Node<T> next;
		
		/* Constructors */
		private Node(T d) {
			data = d;
			next = null; // by default, when a node is created, it does not point to anything. This line isn't necessary
		}
		
		private Node(T d, Node<T> n) {
			data = d;
			next = n;
		}
		
		/* Getter Methods */
		T getData() {
			return data;
		}
		
		Node<T> getNextNode() {
			return next;
		}
		
		/* Setter Methods */
		void setData(T d) {
			data = d;
		}
		
		void setNextNode(Node<T> n) {
			next = n;
		}
	}
	/* end of Inner Class */
	////////////////////////

	/* Member Variables */
	private Node<T> firstNode;
	private Node<T> lastNode;
	
	
	/* Inherited methods */
	
	/** 
	 * Adds a new entry to the back of this queue.
	 * @param newEntry  An object to be added. 
	 */
	public void enqueue(T newEntry) {
		Node<T> newNode = new Node<T>(newEntry);
		if (isEmpty()) { // if queue is empty
			firstNode = newNode;
			lastNode = newNode; // newNode is the only node, therefore it is both the first and last node
		} else {
			lastNode.setNextNode(newNode); // attach the newNode after lastNode
			lastNode = newNode; // reassign lastNode to newNode
		}
	}

	 /** Removes and returns the entry at the front of this queue.
    	@return  The object at the front of the queue. 
    	@throws  EmptyQueueException if the queue is empty before the operation. */
	public T dequeue() {
		if (firstNode != null) {
			Node<T> deQ = firstNode;
			firstNode = firstNode.getNextNode();
			return deQ.getData();
		} else {
			throw new EmptyQueueException();
		}
	}

	/**  Retrieves the entry at the front of this queue.
    	@return  The object at the front of the queue.
    	@throws  EmptyQueueException if the queue is empty. */
	public T getFront() {
		if (firstNode != null) {
			return firstNode.getData();
		} else {
			throw new EmptyQueueException();
		}
	}

	/** Detects whether this queue is empty.
    	@return  True if the queue is empty, or false otherwise. */
	public boolean isEmpty() {
		return (firstNode == null);
	}

	 /** Removes all entries from this queue. */
	public void clear() {
		firstNode = null;
		lastNode = null;
	}
	
	
}
