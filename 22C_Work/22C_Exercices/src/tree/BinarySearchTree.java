/**
 * @author Aaron Wong
 * CIS 22C - Tree Exercise 2
 * BinarySearchTree.java
 */
package tree;

public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T> implements SearchTreeInterface<T> {
	public BinarySearchTree() {
		super();
	} // end default constructor

	public BinarySearchTree(T rootEntry) {
		super();
		setRootNode(new BinaryNode<>(rootEntry));
	} // end constructor

	public void setRootNode(BinaryNode<T> binaryNode) {
		// TODO Auto-generated method stub
		super.setRootNode(binaryNode);
	}

	public void setTree(T rootData) // Disable setTree (see Segment 25.6)
	{
		throw new UnsupportedOperationException();
	} // end setTree

	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
		throw new UnsupportedOperationException();
	} // end setTree

//...

	public T remove(T entry) {
		ReturnObject oldEntry = new ReturnObject(null);
		BinaryNode<T> newRoot = removeEntry(getRootNode(), entry, oldEntry);
		setRootNode(newRoot);

		return oldEntry.get();
	} // end remove

//...

	private BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T entry, BinarySearchTree<T>.ReturnObject oldEntry) {
		// TODO Auto-generated method stub
		if (rootNode != null) {
			T rootData = rootNode.getData();
			int compare = entry.compareTo(rootData);
			if (compare == 0) {
				// entry == root entry
				oldEntry.set(rootData);
				rootNode = removeFromRoot(rootNode);
			} else if (compare < 0) {
				// entry < root entry
				BinaryNode<T> leftChild = rootNode.getLeftChild();
				BinaryNode<T> subtreeRoot = removeEntry(leftChild, entry, oldEntry);
				rootNode.setLeftChild(subtreeRoot);
			} else {
				// entry > root entry
				BinaryNode<T> rightChild = rootNode.getRightChild();
				BinaryNode<T> subtreeRoot = removeEntry(rightChild, entry, oldEntry);
				rootNode.setRightChild(subtreeRoot);
			}
		}
		return rootNode;
	}

	/**
	 * Removes an entry in a given root node of a subtree. 
	 * @param rootNode	the rootNode of a given subtree
	 * @return			BinaryNode<T>, which is the node being removed
	 */
	private BinaryNode<T> removeFromRoot(BinaryNode<T> rootNode) {
		// TODO Auto-generated method stub
		if (rootNode.hasLeftChild() && rootNode.hasRightChild()) {
			// if rootNode has 2 children, find largest node in left subtree
			BinaryNode<T> leftSubtreeRoot = rootNode.getLeftChild();
			BinaryNode<T> largestNode = findLargest(leftSubtreeRoot);
			// replace entry in root
			rootNode.setData(largestNode.getData());
			// remove the largest entry node in subtree
			rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
		} else if (rootNode.hasRightChild()) {
			// if rootNode only has a right child
			rootNode = rootNode.getRightChild();
		} else {
			// if rootNode only has a left child
			rootNode = rootNode.getLeftChild();
		}
		return rootNode;
	}

	/**
	 * Removes the largest node in a given tree. Searches for the rightmost node in the subtrees based
	 * off of a given rootNode
	 * @param rootNode	the node to be removed
	 * @return 			a BinaryNode<T>, which is the largest node in the subtree of rootNode
	 */
	private BinaryNode<T> removeLargest(BinaryNode<T> rootNode) {
		if (rootNode.hasRightChild()) {
			BinaryNode<T> rightChild = rootNode.getRightChild();
			rightChild = removeLargest(rightChild);
			rootNode.setRightChild(rightChild);
		} else {
			rootNode = rootNode.getLeftChild();
		}
		return rootNode;
	}

	private BinaryNode<T> findLargest(BinaryNode<T> rootNode) {
		// look for right-most (largest) node in this subtree
		if (rootNode.hasRightChild()) {
			rootNode = findLargest(rootNode.getRightChild());
		}
		return rootNode;
	}

	public BinaryNode<T> getRootNode() {
		return super.getRootNode();
	}

	private class ReturnObject {
		private T item;

		private ReturnObject(T entry) {
			item = entry;
		} // end constructor

		public T get() {
			return item;
		} // end get

		public void set(T entry) {
			item = entry;
		} // end set
	} // end ReturnObject

	/**
	 * Searches for a specific entry in this tree.
	 * 
	 * @param entry An object to be found.
	 * @return True if the object was found in the tree.
	 */
	@Override
	public boolean contains(T entry) {
		return findEntry(getRootNode(), entry) != null;
	}

	/**
	 * Retrieves a specific entry in this tree.
	 * 
	 * @param entry An object to be found.
	 * @return Either the object that was found in the tree or null if no such
	 *         object exists.
	 */
	@Override
	public T getEntry(T entry) {
		// TODO Auto-generated method stub
		return findEntry(getRootNode(), entry);
	}

	private T findEntry(BinaryNode<T> rootNode, T anEntry) {
		T result = null;
		if (rootNode != null) {
			T rootEntry = rootNode.getData();
			if (anEntry.equals(rootEntry)) {
				result = rootEntry; // anEntry is found
			} else if (anEntry.compareTo(rootEntry) < 0) {
				// search the leftChild subtree
				result = findEntry(rootNode.getLeftChild(), anEntry);
			} else {
				result = findEntry(rootNode.getRightChild(), anEntry);
			}
		}
		return result;
	}

	/**
	 * Adds a new entry to this tree, if it does not match an existing object in the
	 * tree. Otherwise, replaces the existing object with the new entry.
	 * 
	 * @param newEntry An object to be added to the tree.
	 * @return Either null if newEntry was not in the tree already, or the existing
	 *         entry that matched the parameter newEntry and has been replaced in
	 *         the tree.
	 */
	@Override
	public T add(T newEntry) {
		// TODO Auto-generated method stub
		T result = null;
		if (getRootNode() == null) {
			setRootNode(new BinaryNode<T>(newEntry));
		} else {
			result = addEntry(getRootNode(), newEntry);
		}
		return result;
	}

	private T addEntry(BinaryNode<T> rootNode, T anEntry) {
		T result = null;
		int compare = anEntry.compareTo(rootNode.getData());
		if (compare == 0) {
			// this node and anEntry are the same
			result = rootNode.getData();
			rootNode.setData(anEntry);
		} else if (compare < 0) {
			// if anEntry is less than this node
			if (rootNode.hasLeftChild()) {
				// check if this node has a left child or not
				result = addEntry(rootNode.getLeftChild(), anEntry);
			} else {
				// if there is no left child, set it to anEntry
				rootNode.setLeftChild(new BinaryNode<T>(anEntry));
			}
		} else {
			if (rootNode.hasRightChild()) {
				// check to see if this node has a right child
				result = addEntry(rootNode.getRightChild(), anEntry);
			} else {
				// if there is no right child, set it to an Entry
				rootNode.setRightChild(new BinaryNode<T>(anEntry));
			}
		}

		return result;
	}
} // end BinarySearchTree