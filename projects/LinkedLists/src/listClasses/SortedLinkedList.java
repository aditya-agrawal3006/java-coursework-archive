package listClasses;

import java.util.*;

/**
 * Implements a generic sorted list using a provided Comparator. It extends BasicLinkedList class.
 */
public class SortedLinkedList<T> extends BasicLinkedList<T> {
	
    private Comparator<T> comp;

    /**
     * Creates an empty list that is associated with the specified comparator.
     *
     * @param comparator the comparator to use for maintaining the sorted order.
     */
    public SortedLinkedList(Comparator<T> comp) {
        super();
        this.comp = comp;
    }

    /**
     * Inserts the specified element at the correct position in the sorted list.
     * The method traverses the list only once (without using iterators).
     *
     * @param element the element to insert.
     * @return a reference to the current SortedLinkedList.
     */
    public SortedLinkedList<T> add(T element) {
        BasicLinkedList<T> temp = new BasicLinkedList<>();
        boolean inserted = false;
        
        // Iterate over the current list
        for (T i : this) {
            if (!inserted && comp.compare(element, i) <= 0) {
                temp.addToEnd(element);
                inserted = true;
            }
            temp.addToEnd(i);
        }
        
        // Edge case: element is biggest or list was empty
        if (!inserted) {
            temp.addToEnd(element);
        }
        
        // Rebuild curr list with temp one
        while (this.getSize() > 0) {
            this.retrieveFirstElement();
        }
        for (T data : temp) {
            super.addToEnd(data);
        }
        
        return this;
    }

    /**
     * Removes ALL instances of targetData from the sorted list.
     * Since the list is sorted, the traversal can stop once a node's data is greater than targetData.
     *
     * @param targetData the data to remove.
     * @return a reference to the current SortedLinkedList.
     */
    public SortedLinkedList<T> remove(T targetData) {
        super.remove(targetData, comp);
        return this;
    }
    
    /* This operation is invalid for a sorted list. An 
     * UnsupportedOperationException will be generated using the message 
     * "Invalid operation for sorted list."
     */
    @Override
    public BasicLinkedList<T> addToEnd(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list.");
    }

    /* This operation is invalid for a sorted list. An 
     * UnsupportedOperationException will be generated using the message 
     * "Invalid operation for sorted list."
     */
    @Override
    public BasicLinkedList<T> addToFront(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list.");
    }
}