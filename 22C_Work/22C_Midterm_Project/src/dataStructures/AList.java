package dataStructures;
import java.util.Arrays;

/**
 * @Author: Aaron Wong
 * CIS 22C - Midterm Project
 * ArrayList class
 * AList.java
 */

public class AList<T> implements ListInterface<T> {
	/* Member Variables */
	private T[] list;
	private int numberOfEntries; // aka 'count' variable
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 100;

	/* Constructors */
	public AList() {
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[DEFAULT_CAPACITY]; // create a generic array at default capacity
		list = temp;
		numberOfEntries = 0;
		initialized = true; // always set initialize to true when an array is created
	}

	public AList(int initialCapacity) { // creates an object at the desired input capacity
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[initialCapacity];
		list = temp;
		numberOfEntries = 0;
		initialized = true;

	}

	public AList(T[] list, int numberOfEntries) {
		this.list = list;
		this.numberOfEntries = numberOfEntries;
		initialized = true;
	}

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
		checkInitialization();
		ensureCapacity();
		list[numberOfEntries] = newEntry; // addnew entry to end of temp list. Using index positions, the variable
											// numberOfEntries should be at the end of the list
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
		checkInitialization();
		ensureCapacity();
		makeRoom(newPosition - 1);
		list[newPosition - 1] = newEntry;
		numberOfEntries++; // increase number of entries in the AList
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
			T removed = list[givenPosition - 1]; // Save the T object at the givenPosition in a temp file to return after
												// removing it from the list
			@SuppressWarnings("unchecked")
			T[] temp = (T[]) new Object[list.length - 1]; // create a temp T[] that is one size smaller than list
			removeGap(givenPosition - 1); // remove the object at the givenPosition in the array
			list[numberOfEntries] = null;
			numberOfEntries--;
			for (int i = 0; i < temp.length; i++) {
				temp[i] = list[i]; // copy list into temp since list was adjusted in the makeRoom() method;
			}
			list = temp; // copy temp into list to keep new, cleaner array
			return removed;
		} catch (IndexOutOfBoundsException e) {
			throw new Error("Error: Index is out of bounds for current array.");
		}
	}
	
	public int getPos(T anEntry) {
		for (int i = 0; i < numberOfEntries; i++) {
			if (list[i].equals(anEntry)) {
				return i;
			}
		}
		return -1; // if not found
	}

	/** Removes all entries from this list. */
	public void clear() {
		for (int i = 0; i < numberOfEntries; i++) { // set all entries to null in the list
			list[i] = null;
		} // does not reduce the size of the list
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
	public T replace(int givenPosition, T newEntry) {
		try {
			list[givenPosition - 1] = newEntry;
			return newEntry;
		} catch (IndexOutOfBoundsException e) {
			throw new Error("Error: Index is out of bounds for current array.");
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
			return list[givenPosition - 1];
		} catch (IndexOutOfBoundsException e) {
			throw new Error("Error: Index is out of bounds for current array.");
		}
	}

	/**
	 * Sees whether this list contains a given entry.
	 * 
	 * @param anEntry The object that is the desired entry.
	 * @return True if the list contains anEntry, or false if not.
	 */
	public boolean contains(T anEntry) {
		for (int i = 0; i < numberOfEntries; i++) {
			if (list[i].equals(anEntry)) {
				return true;
			}
		}
		return false; // if not found
	}

	/**
	 * Gets the length of this list.
	 * 
	 * @return The integer number of entries currently in the list.
	 */
	public int getLength() {
		return numberOfEntries;
	}

	/**
	 * Sees whether this list is empty.
	 * 
	 * @return True if the list is empty, or false if not.
	 */
	public boolean isEmpty() {
		return (numberOfEntries == 0);
	}

	/**
	 * Retrieves all entries that are in this list in the order in which they occur
	 * in the list.
	 * 
	 * @return A newly allocated array of all the entries in the list.
	 */
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] newArr = (T[]) new Object[numberOfEntries]; // creates a new array with the size being the exact number of
														// entries in the current list
		
		for (int i = 0; i < numberOfEntries; i++) {
			newArr[i] = list[i];
		}
		return newArr;
	}

	//////////////////////
	/* Instance Methods */
	//////////////////////

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
