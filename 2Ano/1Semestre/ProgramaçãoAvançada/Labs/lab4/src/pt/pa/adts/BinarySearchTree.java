/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.pa.adts;

/**
 * Interface that describes the behavior of the adt Binary Search Tree (BST).
 * @author brunomnsilva
 * @param <T> type of element to store in the BST
 */
public interface BinarySearchTree<T extends Comparable<T>> {
    
    /**
     * Checks whether the tree is empty.
     * @return true if empty, false otherwise.
     */    
    public boolean isEmpty();
    
    /**
     * Returns the size (number of elements) of the tree.
     * @return element count
     */
    public int size();
    
     /**
     * Returns the height of the tree.
     * @return tree height
     */
    public int height();
    
    /**
     * Checks whether an element is present/exists within the tree.
     * @param elem element to check
     * @return true if elem exists, false otherwise
     */
    public boolean exists(T elem);
    
    /**
     * Inserts the given element into the tree. If the element is a duplicate, 
     * no change is made.
     * @param elem element to insert in the BST
     */
    public void insert(T elem);
    
    /**
     * Removes the given element from the tree. Equality is given by the Comparable criterion.
     * @param elem element to remove. 
     * @throws EmptyContainerException if the BST is empty
     */
    public void remove(T elem) throws EmptyContainerException;
    
    /**
     * Returns the minimum element of the tree by the Comparable criterion.
     * @return the minimum element
     * @throws EmptyContainerException if the BST is empty
     */
    public T minimum() throws EmptyContainerException;
    
    /**
     * Returns the maximum element of the tree by the Comparable criterion.
     * @return the maximum element
     * @throws EmptyContainerException if the BST is empty
     */
    public T maximum() throws EmptyContainerException;
       
    /**
     * Returns the existing elements of the tree in in-order fashion.
     * @return an iterable collection of the elements
     */
    public Iterable<T> inOrder();
    
    /**
     * Returns the existing elements of the tree in pre-order fashion.
     * @return an iterable collection of the elements
     */
    public Iterable<T> preOrder();
    
    /**
     * Returns the existing elements of the tree in post-order fashion.
     * @return an iterable collection of the elements
     */
    public Iterable<T> posOrder();
    
    /**
     * Returns the existing elements of the tree in breadth-order fashion.
     * @return an iterable collection of the elements
     */
    public Iterable<T> breadthOrder();
    
}
