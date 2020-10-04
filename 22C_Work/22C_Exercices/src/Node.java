/*
 * @Author: Aaron Wong
 * CIS 22C - Exercise 1
 * Class Node example
 * Node.java
 */
public class Node<E> {
	
	/* Variables */
	private E data; // this Node stores generic E as its datatype
	private Node<E> next;
	
	/* Constructors */
	private Node(E d) {
		data = d;
		next = null; // by default, when a node is created, it does not point to anything. This line isn't necessary
	}
	
	private Node(E d, Node<E> n) {
		data = d;
		next = n;
	}
	
	/* Getter Methods */
	E getData() {
		return data;
	}
	
	Node<E> getNextNode() {
		return next;
	}
	
	/* Setter Methods */
	void setData(E d) {
		data = d;
	}
	
	void setNextNode(Node<E> n) {
		next = n;
	}
}
