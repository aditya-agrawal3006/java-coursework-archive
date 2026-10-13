package listClasses;

import java.util.*;

public class BasicLinkedList<T> implements Iterable<T> {
	
	private Node head;
    private Node tail;
    private int size;
	

    // Inner class: Node
	private class Node {
        private T data;
        private Node next;
        
        private Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

	/* Defines an empty linked list. No nodes are created. We do not use dummy 
	 * nodes for this list (if you don't know what a dummy list is don't worry 
	 * about it).
	 */
    public BasicLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /* Notice you must not traverse the list to compute the size. 
     * This method just returns the value of the instance variable you use to 
     * keep track of size.
     */
    public int getSize() {
        return size;
    }

    /* Adds element to the end of the list. Do not use iterators to implement 
     * this method.
     */
    public BasicLinkedList<T> addToEnd(T data) {
        Node newNode = new Node(data);
        
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        
        return this;
    }

    /* Adds element to the front of the list. Do not use iterators to implement 
     * this method.
     */
    public BasicLinkedList<T> addToFront(T data) {
        Node newNode = new Node(data);
        
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        
        size++;
        
        return this;
    }

    /* Returns but does not remove the first element from the list. If there are
     *  no elements the method returns null. Do not implement this method using 
     *  iterators.
     */
    public T getFirst() {
        if (head == null) {
            return null;
        }
        
        return head.data;
    }

    /* Returns but does not remove the last element from the list. If there are
     * no elements the method returns null. Do not implement this method using 
     * iterators.
     */
    public T getLast() {
        if (tail == null) {
            return null;
        }
        return tail.data;
    }

    /* Removes and returns the first element from the list. If there are no 
     * elements the method returns null. Do not implement this method using 
     * iterators.
     */
    public T retrieveFirstElement() {
        if (head == null) {
            return null;
        }
        
        T firstElement = head.data;
        head = head.next;
        size--;
        
        if (head == null) {
            tail = null;
        }
        
        return firstElement;
    }

    /* Removes and returns the last element from the list. If there are no 
     * elements the method returns null. Do not implement implement this method 
     * using iterators.
     */
    public T retrieveLastElement() {
        if (head == null) {
            return null;
        }
        
        if (head == tail) {
            T lastElement = head.data;
            head = null;
            tail = null;
            size--;
            return lastElement;
        }
        
        Node curr = head;
        while (curr.next != tail) {
            curr = curr.next;
        }
        T lastElement = tail.data;
        tail = curr;
        tail.next = null;
        size--;
        
        return lastElement;
    }

    /* Removes ALL instances of targetData from the list. Notice that you must 
     * remove the elements by performing a single traversal over the list. You 
     * may not use any of the other retrieval methods associated with the class 
     * in order to complete the removal process. You must use the provided 
     * comparator (do not use equals) to find those elements that match the 
     *  target. Do not implement implement this method using iterators.
     */
    public BasicLinkedList<T> remove(T targetData, Comparator<T> comparator) {
        while (head != null && comparator.compare(head.data, targetData) == 0) {
            head = head.next;
            size--;
        }
        
        if (head == null) {
            tail = null;
            return this;
        }
        
        Node current = head;
        while (current.next != null) {
            if (comparator.compare(current.next.data, targetData) == 0) {
                current.next = current.next.next;
                size--;
                
                if (current.next == null) {
                    tail = current;
                }
            } else {
                current = current.next;
            }
        }
        
        return this;
    }

    /* This method must be implemented using an anonymous inner class that 
     * defines the iterator. Remember that we should be able to call the 
     * hasNext() method as many times as we want without changing what is 
     * considered the next element. Only students in the honor section will 
     * implement the iterator's remove() method. If you don't need to implement 
     * the iterator's remove() method, throw UnsupportedOperationException.
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node curr = head;
            
            public boolean hasNext() {
                return curr != null;
            }
            
            public T next() {
                if (!hasNext()) {
                    throw new UnsupportedOperationException();
                }
                
                T nextElement = curr.data;
                curr = curr.next;
                return nextElement;
            }
            
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /* Returns an ArrayList with the element of the linked list in reverse 
     * order. This method must be implemented using recursion.
     */
    public ArrayList<T> getReverseArrayList() {
        ArrayList<T> list = new ArrayList<>();
        getReverseArrayListHelper(head, list);
        return list;
    }
    
    // Helper method for getReverseArrayList
    private void getReverseArrayListHelper(Node curr, ArrayList<T> list) {
        if (curr == null) {
            return;
        }
        
        getReverseArrayListHelper(curr.next, list);
        list.add(curr.data);
    }
    

    /* Returns a new list with the elements of the current list in reverse 
     * order. You can assume sharing of data of each node is fine. This method 
     * must be implemented using recursion.
     */
    public BasicLinkedList<T> getReverseList() {
        BasicLinkedList<T> reversedList = new BasicLinkedList<>();
        getReverseListHelper(head, reversedList);
        
        return reversedList;
    }
    
    // Helper method for getReverseList
    private void getReverseListHelper(Node curr, BasicLinkedList<T> reversedList) {
        if (curr == null) {
            return;
        }
        
        getReverseListHelper(curr.next, reversedList);
        reversedList.addToEnd(curr.data);
    }

}