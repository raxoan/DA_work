import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class that implements the ADT dictionary by using hashing and linear
 * probing to resolve collisions.The dictionary is unsorted and has distinct
 * search keys.
 * 
 * This code is based on HashedDictionary from Chapter 22 of Data Structures and
 * Abstractions with Java. 4/e by Carrano
 * 
 * @version 4.0
 * 
 *          It adds protocol that allows the client to change the load factor.
 * 
 * @param <K> Generic Key type.
 * @param <V> Generic Value type.
 */
public class HashedDictionaryOpenAddressingDoubleInstrumented<K, V> implements DictionaryInterface<K, V> {

	// The dictionary:
	private int numberOfEntries;
	private static final int DEFAULT_CAPACITY = 5; // Must be prime
	private static final int MAX_CAPACITY = 10000;
	private int primeSize;

	// The hash table:
	private TableEntry<K, V>[] hashTable; // Dictionary entries
	private int tableSize; // Must be prime
	private static final int MAX_SIZE = 2 * MAX_CAPACITY;
	private boolean initialized = false;

	// fraction of hash table that can be filled
	// In the original code this was static final, but we want to be able
	// to change it for our timings.
	private double MAX_LOAD_FACTOR = 0.5;

	public HashedDictionaryOpenAddressingDoubleInstrumented() {
		this(DEFAULT_CAPACITY); // Call next constructor
	} // end default constructor

	public HashedDictionaryOpenAddressingDoubleInstrumented(int initialCapacity) {
		checkCapacity(initialCapacity);
		numberOfEntries = 0;
		// Set up hash table:
		// Initial size of hash table is same as initialCapacity if it is prime
		// otherwise increase it until it is prime size
		tableSize = getNextPrime(initialCapacity);

		checkSize(tableSize); // Check for max array size

		// The case is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		TableEntry<K, V>[] temp = (TableEntry<K, V>[]) new TableEntry[tableSize];
		hashTable = temp;
		initialized = true;
		primeSize = getPrevPrime(tableSize);
	} // end constructor

	/**
	 * Throws an exception if this object is not initialized.
	 * 
	 */
	private void checkInitialization() {
		if (!initialized)
			throw new SecurityException("ArrayBag object is not initialized " + "properly.");
	}

	/**
	 * Throws an exception if the desired capacity is too large.
	 * 
	 */
	private void checkCapacity(int desiredCapacity) {
		if (desiredCapacity > MAX_CAPACITY)
			throw new IllegalStateException(
					"Attempt to create a hash table " + "whose capacity exceeds " + "allowed maximum.");
	}

	/**
	 * Throws an exception if the desired array size is too large.
	 * 
	 */
	private void checkSize(int desiredSize) {
		if (desiredSize > MAX_SIZE)
			throw new IllegalStateException(
					"Attempt to create a hash table " + "array whose size exceeds " + "allowed maximum.");
	}

	// New method to change the load factor.
	public void setMaxLoadFactor(double loadFactor) {
		MAX_LOAD_FACTOR = loadFactor;
	} // end setMaxLoadFactor

	private int getNextPrime(int t) {
		t = getNextOdd(t); // get the next odd

		while (!isOddPrime(t)) {
			t += 2; // try odds until a prime is found
		}

		return t;
	} // end getNextPrime

	private int getPrevPrime(int t) {
		t = getPrevOdd(t); // get the previous odd
		while (!smallPrimeCheck(t)) {
			t -= 2;
		}

		return t;
	} // end getPrevPrime

	private int getPrime() {
		for (int i = hashTable.length - 1; i >= 1; i--) {
			int fact = 0;
			for (int j = 2; j <= (int) Math.sqrt(i); j++) {
				if (i % j == 0) {
					fact++;
				}
				if (fact == 0) {
					return i;
				}
			}
		}
		return 3;
	}

	private boolean smallPrimeCheck(int t) {
		BigInteger b = new BigInteger(String.valueOf(t));
		return b.isProbablePrime(1);
	}

	private int getNextOdd(int t) {
		if (t % 2 == 0)
			return t + 1;
		else
			return t;
	} // end getNextOdd

	private int getPrevOdd(int t) {
		if (t % 2 == 0)
			return t - 1;
		else
			return t - 2;
	} // end getPrevOdd

	// Precondition: t is an odd value
	private boolean isOddPrime(int t) // Not the most efficient method, but it will serve
	{
		int test = 3;
		boolean foundFactor = false;
		while (test * test < t && !foundFactor) {
			foundFactor = (t % test == 0); // is it divisible by test
			test += 2;
		}

		return !foundFactor;
	} // end getNextPrime

	private int getHashIndex(K key) {
		int val = key.toString().hashCode();
		val = val % hashTable.length;
		if (val < 0) {
			val += hashTable.length;
		}
		return val;
	} // end getHashIndex

	private int getSecondHashIndex(K key) {
		int val = key.toString().hashCode();
		val %= hashTable.length;
		if (val < 0) {
			val += hashTable.length;
		}
		return primeSize - val % primeSize;
	}

	@Override
	public V getValue(K key) {
		checkInitialization();

		V result = null;
		int index = getHashIndex(key);
		index = locate(index, key);

		if (index != -1)
			result = hashTable[index].getValue(); // Key found; get value
		// Else key not found; return null

		return result;
	} // end getValue

	@Override
	public V remove(K key) {
		checkInitialization();
		if (key == null) {
			throw new IllegalArgumentException();
		} else {
			int hash1 = getHashIndex(key);
			int hash2 = getSecondHashIndex(key);

			while (hashTable[hash1] != null && !(hashTable[hash1].getKey().equals(key))) { 
				// looking for a spot in hashTable that is not null
				hash1 += hash2;
				hash1 %= hashTable.length;
			} // end of while; either the key is found at hash1, or the value at key hash1 is
				// null

			V value = null; // return value
			if (hashTable[hash1] != null) {
				value = hashTable[hash1].getValue();
				hashTable[hash1].setToRemoved();
				numberOfEntries--;
			}

			return value;
		}
	} // end remove

	// Precondition: checkInitialization has been called.
	private int locate(int foundIndex, K searchKey) {
		if (searchKey == null) {
			throw new IllegalArgumentException();
		} else {
			int hash1 = getHashIndex(searchKey);
			int hash2 = getSecondHashIndex(searchKey);
			boolean found = false;
			while (!found && (hashTable[hash1] != null)) {
				if (hashTable[hash1].isIn() && searchKey.equals(hashTable[hash1].getKey())) {
					found = true; // key found
				} else {
					hash1 += hash2;
					hash1 %= hashTable.length; // double hash
				}
			} // end while
			if (hashTable[hash1] == null) {
				hash1 = -1;
			}
			return hash1;
		}

	} // end locate

	private boolean isHashTableTooFull() {
		return numberOfEntries + 1 > MAX_LOAD_FACTOR * hashTable.length;
	}

	@Override
	public V add(K key, V value) {
		checkInitialization();

		if ((key == null) || (value == null)) {
			throw new IllegalArgumentException();
		} else {
			// Ensure that hash table is large enough for another add
			if (isHashTableTooFull()) {
				enlargeHashTable();
			}
			int hash1 = getHashIndex(key);
			int hash2 = getSecondHashIndex(key);

			// Assertion: index is within legal range for hashTable
			assert (hash1 >= 0) && (hash1 < hashTable.length);

			while (hashTable[hash1] != null && !(hashTable[hash1].getKey().equals(key))) {
				hash1 += hash2; // looking for a spot in hashTable that is not null
				hash1 %= hashTable.length;
			}

			TableEntry temp = hashTable[hash1];
			hashTable[hash1] = new TableEntry(key, value);

			if (temp == null) {
				numberOfEntries++;
			}
			return hashTable[hash1].getValue();
		} // end if
	} // end add

	// Precondition: checkInitialization has been called.
	private int probe(int index, K key) {
		boolean found = false;
		int removedStateIndex = -1; // Index of first location in
		// removed state
		int itr = 0;
		while (!found && (hashTable[index] != null)) {
			if (hashTable[index].isIn()) {
				if (key.equals(hashTable[index].getKey())) {
					found = true; // Key found
				} else { // Follow probe sequence
					index = (index + (++itr * getSecondHashIndex(key))) % hashTable.length;// DoubleHash
				}
			} else {// Skip entries that were removed
				// Save index of first location in removed state
				if (removedStateIndex == -1) {
					removedStateIndex = index;
				}
				index = (index + (++itr * getSecondHashIndex(key))) % hashTable.length; // DoubleHash
			} // end if
		} // end while

		// Assertion: Either key or null is found at hashTable[index]
		if (found || (removedStateIndex == -1))
			return index; // Index of either key or null
		else
			return removedStateIndex; // Index of an available location
	} // end probe

	// Precondition: checkInitialization has been called.
	private void enlargeHashTable() {
		TableEntry<K, V>[] oldTable = hashTable;
		int oldSize = hashTable.length;
		int newSize = getNextPrime(oldSize + oldSize);

		// The case is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		TableEntry<K, V>[] temp = (TableEntry<K, V>[]) new TableEntry[newSize];
		hashTable = temp;
		tableSize = newSize;
		primeSize = getPrevPrime(tableSize);
		numberOfEntries = 0; // Reset size of dictionary, since it will be
		// incremented by add during rehash

		// Rehash dictionary entries from old array to the new and bigger
		// array; skip both null locations and removed entries
		for (int index = 0; index < oldSize; index++) {
			if ((oldTable[index] != null) && oldTable[index].isIn())
				add(oldTable[index].getKey(), oldTable[index].getValue());
		} // end for
	} // end enlargeHashTable

	@Override
	public boolean contains(K key) {
		boolean result = false;

		int index = getHashIndex(key);
		index = locate(index, key);

		if (index != -1)
			result = true; // key found; return true
		// else key not found; return false
		return result;
	} // end contains

	@Override
	public Iterator<K> getKeyIterator() {
		return new KeyIterator();
	} // end getKeyIterator

	@Override
	public Iterator<V> getValueIterator() {
		return new ValueIterator();
	} // end getValueIterator

	@Override
	public boolean isEmpty() {
		return numberOfEntries == 0;
	} // end isEmpty

	@Override
	public int getSize() {
		return numberOfEntries;
	} // end getSize

	@Override
	public void clear() {
		hashTable = new TableEntry[hashTable.length]; // use the old table size
		numberOfEntries = 0;
	}

	private class TableEntry<S, T> {
		private S key;
		private T value;
		private boolean inTable; // true if entry is in hash table

		private TableEntry(S searchKey, T dataValue) {
			key = searchKey;
			value = dataValue;
			inTable = true;
		} // end constructor

		private T getValue() {
			return value;
		} // end getValue

		private void setValue(T x) {
			value = x;
		} // end setValue

		private S getKey() {
			return key;
		} // end getKey

		private void setKey(S k) {
			key = k;
		} // end setKey

		private void setToRemoved() {
			inTable = false;
		} // end setToRemoved

		private boolean isIn() {
			return inTable;
		} // end isIn

		private boolean isRemoved() {
			return !inTable;
		} // end isRemoved
	} // end TableEntry

	private class KeyIterator implements Iterator<K> {
		private int currentIndex; // Current position in hash table
		private int numberLeft; // Number of entries left in iteration

		private KeyIterator() {
			currentIndex = 0;
			numberLeft = numberOfEntries;
		} // end default constructor

		@Override
		public boolean hasNext() {
			return numberLeft > 0;
		} // end hasNext

		@Override
		public K next() {
			K result = null;
			if (hasNext()) {
				// Skip table locations that do not contain a current entry
				while ((hashTable[currentIndex] == null) || hashTable[currentIndex].isRemoved()) {
					currentIndex++;
				} // end while

				result = hashTable[currentIndex].getKey();
				numberLeft--;
				currentIndex++;
			} else
				throw new NoSuchElementException();

			return result;
		} // end next

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		} // end remove

	} // end KeyIterator

	private class ValueIterator implements Iterator<V> {
		private int currentIndex; // current position in hash table
		private int numberLeft; // number of entries left in iteration

		private ValueIterator() {
			currentIndex = 0;
			numberLeft = numberOfEntries;
		} // end default constructor

		@Override
		public boolean hasNext() {
			return numberLeft > 0;
		} // end hasNext

		@Override
		public V next() {
			V result = null;
			if (hasNext()) {
				// Skip table locations that do not contain a current entry
				while ((hashTable[currentIndex] == null) || hashTable[currentIndex].isRemoved()) {
					currentIndex++;
				} // end while

				result = hashTable[currentIndex].getValue();
				numberLeft--;
				currentIndex++;
			} else
				throw new NoSuchElementException();

			return result;
		} // end next

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		} // end remove

	} // end ValueIterator

}
