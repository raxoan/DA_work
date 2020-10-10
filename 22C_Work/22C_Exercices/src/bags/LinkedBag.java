/**
 * @author Aaron Wong
 * CIS 22C - Bags Exercise 1
 * LinkedBag.java
 */

package bags;

public class LinkedBag<T> implements BagInterface<T> {
	/////////////////
	/* Inner Class */
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

	/* Member Variables */
	private Node<T> firstNode;
	private int numberOfEntries;

	/* Constructors */
	public LinkedBag() {
		firstNode = null;
		numberOfEntries = 0;
	}

	public LinkedBag(Node<T> n) {
		firstNode = n;
		numberOfEntries = 1;
	}

	/* Inherited Methods */

	/**
	 * Gets the current number of entries in this bag.
	 * 
	 * @return The integer number of entries currently in the bag.
	 */
	public int getCurrentSize() {
		return numberOfEntries;
	}

	/**
	 * Sees whether this bag is empty.
	 * 
	 * @return True if the bag is empty, or false if not.
	 */
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	/**
	 * Adds a new entry to this bag.
	 * 
	 * @param newEntry The object to be added as a new entry.
	 * @return True if the addition is successful, or false if not.
	 */
	public boolean add(T newEntry) {
		try {
			Node<T> n = new Node<T>(newEntry);
			n.setNextNode(firstNode);
			firstNode = n;
			numberOfEntries++;
			return true;
		} catch (Exception e) {
			throw new Error();	
		}
	}

	/**
	 * Removes one unspecified entry from this bag, if possible.
	 * 
	 * @return Either the removed entry, if the removal. was successful, or null.
	 */
	public T remove() {
		try {
			Node<T> n = firstNode;
			firstNode = firstNode.getNextNode();
			numberOfEntries--;
			return n.getData();
		} catch (NullPointerException e) {
			System.out.println("Null Pointer Exception error.");
			return null;
		}
	}

	/**
	 * Removes one occurrence of a given entry from this bag.
	 * 
	 * @param anEntry The entry to be removed.
	 * @return True if the removal was successful, or false if not.
	 */
	public boolean remove(T anEntry) {
		try {
			Node<T> n = new Node<T>(anEntry);
			boolean found = false;
			if (firstNode == null) { // if list is empty
				throw new NullPointerException();
			} else if (n.getData().equals(firstNode.getData())) { // removing from the start of the list
				firstNode = firstNode.getNextNode();
				found = true;
			} else { // iterate through list to reach entry being removed
				Node<T> current = firstNode;

				while (found == false && current != null) {
					if (n.getData().equals(current.getNextNode().getData())) { // if the next object is equal to the entry, set the next
															// node to next.next;
						current.setNextNode(current.getNextNode().getNextNode());
						found = true;
					} else {
						current = current.getNextNode();
					}
				}
			}
			if (found == true) {
				numberOfEntries--;
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
//			throw new Error("Object not found.");
			return false;
		}
	}

	/** Removes all entries from this bag. */
	public void clear() {
		firstNode = null;
		numberOfEntries = 0;

	}

	/**
	 * Counts the number of times a given entry appears in this bag.
	 * 
	 * @param anEntry The entry to be counted.
	 * @return The number of times anEntry appears in the bag.
	 */
	public int getFrequencyOf(T anEntry) {
		int num = 0;
		Node<T> n = new Node<T>(anEntry);
		Node<T> current = firstNode;
		while (current != null) {
			if (current.getData().equals(n.getData())) {
				num++;
			}
			current = current.getNextNode();
		}
		return num;
	}

	/**
	 * Tests whether this bag contains a given entry.
	 * 
	 * @param anEntry The entry to locate.
	 * @return True if the bag contains anEntry, or false if not.
	 */
	public boolean contains(T anEntry) {
		Node<T> n = new Node<T>(anEntry);
		Node<T> current = firstNode;
		boolean found = false;
		while (found == false && current != null) {
			if (current.getData().equals(n.getData())) {
				found = true;
			}
			current = current.getNextNode();
		}
		return found;

	}

	/**
	 * Retrieves all entries that are in this bag.
	 * 
	 * @return A newly allocated array of all the entries in the bag. Note: If the
	 *         bag is empty, the returned array is empty.
	 */
	@SuppressWarnings("unchecked")
	public T[] toArray() {
		T[] newArr = (T[]) new Object[numberOfEntries]; // creates a new array with the size being the exact number of
														// entries in the current list
		Node<T> temp = firstNode;
		for (int i = 0; i < numberOfEntries; i++) {
			newArr[i] = temp.getData();
			temp = temp.getNextNode();
		}
		return newArr;
	}

	// Locates a given entry within this bag.
	// Returns a reference to the node containing the entry, if located,
	// or null otherwise.
	private Node getReferenceTo(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;

		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.data))
				found = true;
			else
				currentNode = currentNode.next;
		} // end while

		return currentNode;
	} // end getReferenceTo

}
