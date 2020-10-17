/**
 * @author Aaron Wong
 * CIS 22C - Stacks Exercise 1
 * LinkedStack.java
 */

package stack;

public final class LinkedStack<T> implements StackInterface<T> {
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

	/* End of Inner Class */
	////////////////////////

	/* Instance variables */
	private Node<T> topNode;
	
	/* Constructors */
	public LinkedStack(){
		topNode = null;
	}
	
	/* Inherited Methods */
	/**
	 * Adds a new entry to the top of this stack.
	 * @param newEntry  An object to be added to the stack.
	 */
	public void push(T newEntry) {
		Node<T> n = new Node<T>(newEntry);
		n.setNextNode(topNode);
		topNode = n;
	}

	/**
	 * Removes and returns this stack's top entry.
	 * @return  The object at the top of the stack. 
	 * @throws  EmptyStackException if the stack is empty before the operation.
	 */
	public T pop() throws EmptyStackException {
		try {
			Node<T> n = topNode;
			topNode = topNode.getNextNode();
			return n.getData();
		} catch (EmptyStackException e) {
			throw new EmptyStackException();
		}
	}

	/**
	 * Retrieves this stack's top entry.
	 * @return  The object at the top of the stack.
	 * @throws  EmptyStackException if the stack is empty.  
	 */
	public T peek() throws EmptyStackException {
		try {
			return topNode.getData();
		} catch (EmptyStackException e) {
			throw new EmptyStackException();
		}
	}

	/**
	 * Detects whether this stack is empty.
	 * @return  True if the stack is empty.
	 */
	public boolean isEmpty() {
		return topNode == null;
	}

	/** 
	 * Removes all entries from this stack. 
	 */
	public void clear() {
		topNode = null;
	}

}
