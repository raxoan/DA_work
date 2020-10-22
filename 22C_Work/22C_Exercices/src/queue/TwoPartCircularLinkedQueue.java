/**
 * @author Aaron Wong
 * CIS 22C - Queue Exercise 2
 * TwoPartCircularLinkedQueue.java
 */

package queue;

public class TwoPartCircularLinkedQueue<T> implements QueueInterface<T> {

	/////////////////
	/* Inner Class */
	@SuppressWarnings("hiding")
	public class Node<T> {

		/* Variables */
		private T data; // this Node stores generic T as its datatype
		private Node<T> next;

		/* Constructors */
		private Node() {
			data = null;
			next = null;
		}
		
		private Node(T d) {
			data = d;
			next = null; // by default, when a node is created, it does not point to anything. This line
							// isn't necessary
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
	private Node<T> queueNode; // References first node in queue
	private Node<T> freeNode; // References node after back of queue

	public TwoPartCircularLinkedQueue() {
		freeNode = new Node<T>(null, null);
		freeNode.setNextNode(freeNode);
		queueNode = freeNode;
	} // end default constructor

	public void clear() {
		while (!isEmpty())
			dequeue();
	} // end clear

	 /** Adds a new entry to the back of this queue.
    @param newEntry  An object to be added. */
	public void enqueue(T newEntry) {
		Node<T> newNode = new Node<T>(newEntry);
		if (isEmpty()) { // if queue is empty
			queueNode = newNode;
			queueNode.setNextNode(freeNode);
			freeNode.setNextNode(queueNode);
		} else if (freeNode.getNextNode().equals(null)) { // no new nodes need to be allocated
			freeNode.setData(newNode.getData());
			freeNode = freeNode.getNextNode();
		} else { // add new node to end of queue
			Node<T> queueCheck = queueNode;
			while (!queueCheck.getNextNode().equals(freeNode)) { // queue has more than 1 node
				queueCheck = queueCheck.getNextNode();
			}
			queueCheck.setNextNode(newNode);
			newNode.setNextNode(freeNode);
		} 
		
	}

	/** Removes and returns the entry at the front of this queue.
    @return  The object at the front of the queue. 
    @throws  EmptyQueueException if the queue is empty before the operation. */
	public T dequeue() {
		if (!isEmpty()) {
			T deQ = queueNode.getData(); // store queueNode's information to be returned
			queueNode.setData(null); // clear data from the first node
			queueNode = queueNode.getNextNode(); // set queueNode as the next Node in the chain
			
			return deQ;
		} else {
			throw new EmptyQueueException();
		}
	}

	/**  Retrieves the entry at the front of this queue.
    @return  The object at the front of the queue.
    @throws  EmptyQueueException if the queue is empty. */
	public T getFront() {
		if (!isEmpty()) {
			return queueNode.getData();
		} else {
			throw new EmptyQueueException();
		}
	}

	/** Detects whether this queue is empty.
    @return  True if the queue is empty, or false otherwise. */
	public boolean isEmpty() {
		if (queueNode == freeNode) { // case of brand new queue
			return true;
		} else { 
			return (queueNode == null);
		}
	}
}
