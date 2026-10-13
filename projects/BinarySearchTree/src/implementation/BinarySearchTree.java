package implementation;

import java.util.*;
import java.util.TreeSet;

public class BinarySearchTree<K, V> {

	private class Node {
		private K key;
		private V value;
        private Node left;
		private Node right;
		private Object next;

		private Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private Node root;
	private int treeSize = 0;
	private int maxEntries;
	private Comparator<K> comparator;

	public BinarySearchTree(Comparator<K> comparator, int maxEntries) {
		if(comparator == null || maxEntries < 1) {
			throw new IllegalArgumentException("You gave the wrong parameters.");
		}
		
		this.comparator = comparator;
		this.maxEntries = maxEntries;
	}

	public BinarySearchTree<K, V> add(K key, V value) throws TreeIsFullException {
	    if (treeSize == maxEntries) {
	        throw new TreeIsFullException("Your tree is full.");
	    }
	    root = addRecursive(root, key, value);
	    return this;
	}

	private Node addRecursive(Node current, K key, V value) {
	    if (current == null) {
	        treeSize++;
	        return new Node(key, value);
	    }

	    int holdCompare = comparator.compare(key, current.key);
	    if (holdCompare < 0) {	  
	        current.left = addRecursive(current.left, key, value);
	    } 
	    else if (holdCompare > 0) {	 
	        current.right = addRecursive(current.right, key, value);
	    } 
	    else {	      
	        current.value = value;
	    }
	    return current;
	}

	public String toString() {
		if (treeSize == 0) {
			return "EMPTY TREE";
		}
		StringBuilder hold = new StringBuilder();
		setPrint(root, hold);
		return hold.toString();
	}
	
	private void setPrint(Node current, StringBuilder hold) {
		if(current == null) {
			return;
		}
		
	    setPrint(current.left, hold);
	    hold.append("{" + current.key + ":" + current.value + "}");
	    setPrint(current.right, hold);
	    
	}

	/* Provided: */
	public boolean isEmpty() {
		return root == null;
	}

	/* Provided: */
	public int size() {
		return treeSize;
	}

	/* Provided: */
	public boolean isFull() {
		return treeSize == maxEntries;
	}

	public KeyValuePair<K, V> getMinimumKeyValue() throws TreeIsEmptyException {
		if (treeSize == 0) {
			throw new TreeIsEmptyException("Your tree is empty.");
		}
		Node minNode = findSmall(root);
		return new KeyValuePair<>(minNode.key, minNode.value);
	}
	
	private Node findSmall(Node current) {
		if(current.left == null) {
			return current;
		}
		return findSmall(current.left);
		
	}

	public KeyValuePair<K, V> getMaximumKeyValue() throws TreeIsEmptyException {
		if (treeSize == 0) {
			throw new TreeIsEmptyException("Your tree is empty.");
		}
		Node maxNode = findLarge(root);
		return new KeyValuePair<>(maxNode.key, maxNode.value);
	}
	
	private Node findLarge(Node current) {
		if(current.right == null) {
			return current;
		}
		return findLarge(current.right);
	}

	public KeyValuePair<K, V> find(K key) {
		Node holdSample = findPair(root, key);
		if (holdSample == null) {
			return null;
		}
		return new KeyValuePair<>(holdSample.key, holdSample.value);
	}
	
	private Node findPair(Node current, K key) {
		if (current == null) {
			return null;
		}
		int holdCompare = comparator.compare(key, current.key);
		if (holdCompare < 0) {
			return findPair(current.left, key);
		}
		else if (holdCompare > 0) {
			return findPair(current.right, key);
		}
		else {
			return current;
		}
	}

	public BinarySearchTree<K, V> delete(K key) throws TreeIsEmptyException {
		if(key == null) {
			throw new IllegalArgumentException("Your key is null.");
		}
		if (treeSize == 0) {
			throw new TreeIsEmptyException("Your tree is empty.");
		}
		root = deleteRecursive(root, key);
		treeSize--;
		return this;
		
	}
	
	private Node deleteRecursive(Node current, K key) {
		 if (current == null) {
		        return null;
		    }

		    int holdCompare = ((String) key).compareTo((String) current.key);
		    if (holdCompare < 0) {	  
		        current.left = deleteRecursive(current.left, key);
		    } 
		    else if (holdCompare > 0) {	 
		        current.right = deleteRecursive(current.right, key);
		    } 
		    else {	      
		        if (current.left == null && current.right == null) {
		        	return null;
		        }
		        else if (current.left == null) {
		        	return current.right;
		        }
		        else if (current.right == null){
		        	return current.left;
		        }
		        else {
		        	Node holdChildren = findSmall(current.right);
		        	current.key = holdChildren.key;
		        	current.value = holdChildren.value;
		        	current.right = deleteRecursive(current.right, holdChildren.key);
		        }
		    }
		    return current;
	}

	public void processInorder(Callback<K, V> callback) {
		if (callback == null) {
			throw new IllegalArgumentException("Your callback is null.");
		}
		inOrderHelper(root, callback);
	}
	
	private void inOrderHelper(Node current, Callback<K, V> callback) {
		if (current == null) {
			return;
		}
		inOrderHelper(current.left, callback);
		callback.process(current.key, current.value);
		inOrderHelper(current.right, callback);
		
	}

	public BinarySearchTree<K, V> subTree(K lowerLimit, K upperLimit) {
		if (lowerLimit == null || upperLimit == null || comparator.compare(lowerLimit, upperLimit) > 0) {
			throw new IllegalArgumentException("Invalid range bounds.");
		}
		BinarySearchTree<K, V> hold = new BinarySearchTree<>(this.comparator, this.maxEntries);
		try {
			buildSubtree(root, lowerLimit, upperLimit, hold);
		} catch (TreeIsFullException e) {
			e.printStackTrace();
		}
		return hold;
	}

	private void buildSubtree(Node current, K low, K high, BinarySearchTree<K, V> result) throws TreeIsFullException {
		if (current == null) return;

		if (comparator.compare(current.key, low) > 0) {
			buildSubtree(current.left, low, high, result);
		}

		if (comparator.compare(current.key, low) >= 0 && comparator.compare(current.key, high) <= 0) {
			result.add(current.key, current.value);
		}

		if (comparator.compare(current.key, high) < 0) {
			buildSubtree(current.right, low, high, result);
		}
	}

	public TreeSet<V> getLeavesValues() {
		TreeSet<V> holdValues = new TreeSet<>();
		addValues(root, holdValues);
		return holdValues;
	}
	
	private void addValues(Node current, TreeSet<V> hold) {
	    if (current == null) {
	        return;
	    }

	    if (current.left == null && current.right == null) {
	    	hold.add(current.value);
	    	return;
	    }
	    
	    addValues(current.left, hold);
	    addValues(current.right, hold);
	}
}

