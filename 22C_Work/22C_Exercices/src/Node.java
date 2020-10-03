/*
 * @Author: Aaron Wong
 * CIS 22C - Exercise 1
 * Class Node example
 * Node.java
 */
public class Node {
	
	/* Variables */
	int data; // this Node stores integers as its datatype
	Node next;
	
	/* Constructors */
	Node(int d) {
		data = d;
		next = null; // by default, when a node is created, it does not point to anything. This line isn't necessary
	}
	
	/* Getter Methods */
	int getData() {
		return data;
	}
	
	Node getNextNode() {
		return next;
	}
	
	/* Setter Methods */
	void setData(int d) {
		data = d;
	}
	
	void setNextNode(Node n) {
		next = n;
	}
}
