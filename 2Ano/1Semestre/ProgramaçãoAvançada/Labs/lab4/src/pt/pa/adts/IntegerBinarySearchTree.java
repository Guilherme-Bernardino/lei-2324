/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.pa.adts;

import java.util.Set;

/**
 *
 * @author brunomnsilva
 */
public interface IntegerBinarySearchTree extends BinarySearchTree<Integer> {
    
    /**
     * Returns the sum of all the odd elements of the tree.
     * @return the sum
     * @throws EmptyContainerException if the BST is empty. 
     */
    public int sumOdd() throws EmptyContainerException;
    
    /**
     * Returns the sum of all the elements that are internal to the BST.
     * @return the sum
     * @throws EmptyContainerException EmptyContainerException if the BST is empty.
     */
    public int sumInternals() throws EmptyContainerException;
    
    /**
     * Returns the number of elements that are strictly greater than <i>value</i>.
     * @param value the threshold
     * @return the count
     * @throws EmptyContainerException EmptyContainerException if the BST is empty.
     */
    public int countGreaterThan(Integer value) throws EmptyContainerException;
    
    /**
     * Returns a set containing all tree values greater than <i>value</i>
     * @param value the threshold value
     * @return the set
     */
    public Set<Integer> greaterThan(Integer value);
}
