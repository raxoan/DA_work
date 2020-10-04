/*
 * @Author: Aaron Wong
 * CIS 22C - Exercise 3
 * LList.java
 */
public class LList<T> implements ListInterface<T> {

	/////////////////
	/* Inner Class */
	/////////////////
	public class Node<T> {

		/* Variables */
		private T data; // this Node stores generic E as its datatype
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

	// * End of Inner Class *//

	/* Member Variables */
	private Node firstNode;
	private int numberOfEntries;

	/* Constructors */
	public LList() {
		firstNode = null;
		numberOfEntries = 0;
	}

	public LList(Node node, int entries) {
		firstNode = node;
		numberOfEntries = entries;
	}

	//////////////////////
	/* Instance Methods */
	//////////////////////

	/**
	 * @Method: initializeDataFields() Initializes the class' data fields to
	 *          indicate an empty list.
	 */
	private void initializeDataFields() {
		firstNode = null;
		numberOfEntries = 0;
	}

	/**
	 * @Method: getNodeAt(int)
	 * @param: givenPosition an integer for the position at which to return a node
	 * @preconditions: the chain is not empty, 1 <= givenPosition <= numberOfEntries
	 */
	private Node getNodeAt(int givenPosition) {
		assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = firstNode;

		// Traverse the chain to locate the desired node
		// (skipped if givenPosition is 1)
		for (int counter = 1; counter < givenPosition; counter++)
			currentNode = currentNode.getNextNode();

		assert currentNode != null;

		return currentNode;
	} // end getNodeAt
	
	///////////////////////
	/* Inherited Methods */
	///////////////////////
	
	/**
	 * Adds a new entry to the end of this list. Entries currently in the list are
	 * unaffected. The list's size is increased by 1.
	 * 
	 * @param newEntry The object to be added as a new entry.
	 */
	public void add(T newEntry) { 
		need to iterate through list to reach the end, use a 'temp' variable
		Node newNode = new Node(newEntry); // create a new Node from the object newEntry
		firstNode.setNextNode(newNode);
		numberOfEntries++;
	}

	/**
	 * Adds a new entry at a specified position within this list. Entries originally
	 * at and above the specified position are at the next higher position within
	 * the list. The list's size is increased by 1.
	 * 
	 * @param newPosition An integer that specifies the desired position of the new
	 *                    entry.
	 * @param newEntry    The object to be added as a new entry.
	 * @throws IndexOutOfBoundsException if either newPosition less than 1, or
	 *                                   newPosition greater than getLength()+1.
	 */
	public void add(int newPosition, T newEntry) {
		
		
	}

	@Override
	public T remove(int givenPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T replace(int givenPosition, T newEntry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getEntry(int givenPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(T anEntry) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

}
