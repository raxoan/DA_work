import java.util.Arrays;

/*
 * @Author: Aaron Wong
 * CIS 22C - Exercise 2
 * ArrayList class
 * AList.java
 */

public class AList<T> implements ListInterface<T> {
	/* Member Variables */
	private T[] list;
	private int numberOfEntries;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 100;

	/* Constructors */
	public AList() {
		this(DEFAULT_CAPACITY);
	}

	public AList(int initialCapacity) {
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[initialCapacity];
		list = temp;
		numberOfEntries = 0;
		initialized = true;

	}
	/* Inherited Methods */

	/**
	 * Adds a new entry to the end of this list. Entries currently in the list are
	 * unaffected. The list's size is increased by 1.
	 * 
	 * @param newEntry The object to be added as a new entry.
	 */
	public void add(T newEntry) {
		increaseByOne(); // run method that increases list length by 1 if not at max capacity
		list[list.length] = newEntry; // addnew entry to end of temp list
		numberOfEntries++; // increase number of entries in the AList
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
	public void add(int newPosition, T newEntry) throws IndexOutOfBoundsException {
		increaseByOne();
		makeRoom(newPosition);
		list[newPosition] = newEntry;
		numberOfEntries++; // increase number of entries in the AList
	}

//		if (newPosition < 1 || newPosition > list.length + 1) {
//			throw new IndexOutOfBoundsException("Index out of bounds");
//		} else { // add new entry
//			increaseByOne(); // increase list size by 1
//			for (int i = list.length; i > newPosition; i--) { // shift elements from the end of the list down towards
//																// the newPosition
//				list[i] = list[i - 1];
//			}
//			list[newPosition] = newEntry;
//			numberOfEntries++; // increase number of entries in the AList
//		}

	

	///////////////////
	/* New Methods */
	///////////////////

	/*
	 * Method increases the size of current T[] array by 1
	 * 
	 */
	private void increaseByOne() {
		checkInitialization();
		checkCapacity(numberOfEntries);
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[list.length + 1];
		for (int i = 0; i < list.length; i++) { // Using for loop instead ofensureCapacity() method because it doubles
												// list size instead of increasing by one.
			temp[i] = list[i]; // copy items in current list into temp
		}
		list = temp; // copy temp into list; temp should have one empty space at the end of the list

	}

	// Throws an exception if this object is not initialized.
	private void checkInitialization() {
		if (!initialized)
			throw new SecurityException("AList object is not initialized properly.");
	} // end checkInitialization

	// Throws an exception if the client requests a capacity that is too large.
	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY)
			throw new IllegalStateException(
					"Attempt to create a list " + "whose capacity exceeds " + "allowed maximum.");
	} // end checkCapacity

	// Doubles the capacity of the array list if it is full.
	// Precondition: checkInitialization has been called.
	private void ensureCapacity() {
		int capacity = list.length - 1;
		if (numberOfEntries >= capacity) {
			int newCapacity = 2 * capacity;
			checkCapacity(newCapacity); // Is capacity too big?
			list = Arrays.copyOf(list, newCapacity + 1);
		} // end if
	} // end ensureCapacity

	/*
	 * Makes room for a new entry at newPosition. Precondition: 1 <= newPosition <=
	 * numberOfEntries + 1; numberOfEntries is list's length before addition;
	 * checkInitialization has been called.
	 * 
	 */
	private void makeRoom(int newPosition) {
		assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);

		int newIndex = newPosition;
		int lastIndex = numberOfEntries;

		// Move each entry to next higher index, starting at end of
		// list and continuing until the entry at newIndex is moved
		for (int index = lastIndex; index >= newIndex; index--)
			list[index + 1] = list[index];
	} // end makeRoom

	// Shifts entries that are beyond the entry to be removed to the
	// next lower position.
	// Precondition: 1 <= givenPosition < numberOfEntries;
	// numberOfEntries is list's length before removal;
	// checkInitialization has been called.
	private void removeGap(int givenPosition) {
		assert (givenPosition >= 1) && (givenPosition < numberOfEntries);

		int removedIndex = givenPosition;
		int lastIndex = numberOfEntries;

		for (int index = removedIndex; index < lastIndex; index++)
			list[index] = list[index + 1];
	} // end removeGap

}
