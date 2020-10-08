package lists;
/*
 * @Author: Aaron Wong
 * CIS 22C - Exercise 1
 * Class Node example
 * Node.java
 */
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
