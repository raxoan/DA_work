package lists;
/*
 * @Author: Aaron Wong
 * CIS 22C - Exercise 3
 * Linked List class
 * LList.java
 */
public class LList<T> implements ListInterface<T> {

	/////////////////
	/* Inner Class */
	/////////////////
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

	// * End of Inner Class *//

	/* Member Variables */
	private Node<T> firstNode;
	private int numberOfEntries;

	/* Constructors */
	public LList() {
		firstNode = null;
		numberOfEntries = 0;
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
//		need to iterate through list to reach the end, use a 'temp' variable;
		Node<T> newNode = new Node<T>(newEntry); // create a new Node from the object newEntry
		if (numberOfEntries == 0) { // checking if there are any Nodes in the LList
			firstNode = newNode;
		} else { // go through linked list until the end is reached
			Node<T> temp = firstNode;
			while (temp.getNextNode() != null) {
				temp = temp.getNextNode();
			}
			temp.setNextNode(newNode); // attach the newNode to the end of the list
		}
		numberOfEntries++; // increase number of entries in LList
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
		try {
			Node<T> newNode = new Node<T>(newEntry); // create new node with the input
			if (firstNode == null) { // checking if LList is empty
				firstNode = newNode;
			} else if (newPosition == 1){ // add to the beginning of the list
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			} else {
				Node<T> current = firstNode;
				int pos = 1;
				while (pos + 1 < newPosition) {
					current = current.getNextNode();
					pos++;
				}
				Node<T> next = current.getNextNode();
				current.setNextNode(newNode);
				newNode.setNextNode(next);
			}
			numberOfEntries++; // regardless of position in LList, increment numberOfEntries
		} catch (NullPointerException e) {
			throw new IndexOutOfBoundsException();			
		}
	}

	/**
	 * Removes the entry at a given position from this list. Entries originally at
	 * positions higher than the given position are at the next lower position
	 * within the list, and the list's size is decreased by 1.
	 * 
	 * @param givenPosition An integer that indicates the position of the entry to
	 *                      be removed.
	 * @return A reference to the removed entry.
	 * @throws IndexOutOfBoundsException if either givenPosition less than 1, or
	 *                                   givenPosition greater than getLength()+1.
	 */
	public T remove(int givenPosition) {
		try {
			Node<T> removed; // create a node to hold object being removed
			if (firstNode == null) { // if list is empty
				throw new Error();
			} else if (givenPosition == 1) { // removing from the start of the list
				removed = firstNode;
				firstNode = firstNode.getNextNode();
			} else { // iterate through list to reach givenPosition
				Node<T> current = firstNode;
				int pos = 1;
				while (pos + 1 < givenPosition) { // if the next position is givenPosition, exit while loop to insert
					current = current.getNextNode();
					pos++;
				}
				removed = current.getNextNode();
				current.setNextNode(current.getNextNode().getNextNode());
			}
			numberOfEntries--;
			return removed.getData();
		} catch (Exception e) {
			throw new Error("Error: Index is out of bounds for current list.");
		}
	}

	public void clear() {
		firstNode = null; // by setting firstNode to null, it drops all other nodes in the list.
		numberOfEntries = 0;

	}
	
	/**
	 * Replaces the entry at a given position in this list.
	 * 
	 * @param givenPosition An integer that indicates the position of the entry to
	 *                      be replaced.
	 * @param newEntry      The object that will replace the entry at the position
	 *                      givenPosition.
	 * @return The original entry that was replaced.
	 * @throws IndexOutOfBoundsException if either givenPosition less than 1, or
	 *                                   givenPosition greater than getLength()+1.
	 */
	public T replace(int givenPosition, T newEntry) { // Replaces node with a new node, rather than changing the data variable.
		try {
			Node<T> replace = new Node<T>(newEntry);
			if (givenPosition == 1) { // replacing the first item in the list
				replace.setNextNode(firstNode.getNextNode());
				firstNode = replace;
				if (numberOfEntries == 0) { // if replacing an empty list with an object, increment numberOfEntries
					numberOfEntries++; 
				}
			} else { // if there is more than one item in the list
				Node<T> current = firstNode;
				int pos = 1;
				while (pos + 1 < givenPosition) {
					current = current.getNextNode();
					pos++;
				}
				Node<T> next = current.getNextNode().getNextNode();
				current.setNextNode(replace);
				replace.setNextNode(next);
			}
			return replace.getData();
		} catch (Exception e) {
			throw new Error();
		}
	}

	/**
	 * Retrieves the entry at a given position in this list.
	 * 
	 * @param givenPosition An integer that indicates the position of the desired
	 *                      entry.
	 * @return A reference to the indicated entry.
	 * @throws IndexOutOfBoundsException if either givenPosition less than 1, or
	 *                                   givenPosition greater than getLength()+1.
	 */
	public T getEntry(int givenPosition) {
		try {
			Node<T> current = firstNode;
			int pos = 1;
			while (pos < givenPosition) {
				current = current.getNextNode();
				pos++;
			}
			return current.getData();
		} catch (Exception e) {
			throw new Error();
		}
	}

	/**
	 * Sees whether this list contains a given entry.
	 * 
	 * @param anEntry The object that is the desired entry.
	 * @return True if the list contains anEntry, or false if not.
	 */
	public boolean contains(T anEntry) {
		try {
			Node<T> check = new Node<T>(anEntry);
			Node<T> current = firstNode;
			boolean contain = false;
			int pos = 1;
			while (pos <= numberOfEntries) {
				if (current.getData().equals(check.getData())) {
					contain = true;
				}
				current = current.getNextNode();
				pos++;
			}
			return contain;
		} catch (Exception e) {
			throw new Error();
		}
	}


	public int getLength() {
		return numberOfEntries;
	}

	public boolean isEmpty() {
		return (numberOfEntries == 0);
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] newArr = (T[]) new Object[numberOfEntries]; // creates a new array with the size being the exact number of
														// entries in the current list
		Node<T> temp = firstNode;
		for (int i = 0; i < numberOfEntries; i++) {
			newArr[i] = temp.getData();
			temp = temp.getNextNode();
		}
		return newArr;
	}
	
	/* Instance Methods */
	//////////////////////
	

}
