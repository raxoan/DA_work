/**
 * @author Aaron Wong
 * CIS 22C - Bags Exercise 1
 * LinkedBag.java
 */

package bags;

import lists.Node;

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

	/* End of Inner Class */
	////////////////////////
	
	/* Member Variables */
	private Node firstNode;
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
	public int getCurrentSize() {
		return numberOfEntries;
	}

	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	public boolean add(T newEntry) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(T anEntry) {
		// TODO Auto-generated method stub
		return false;
	}

	public void clear() {
		firstNode = null;
		numberOfEntries = 0;
		
	}

	@Override
	public int getFrequencyOf(T anEntry) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(T anEntry) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

}
