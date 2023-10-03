/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa.adts.stack;


import java.util.Iterator;

/**
 *
 * @author PM-Uninova
 */
public interface Stack<E> extends Iterable<E> {

	/**
	 * Return the number of elements in the stack
	 * @return
	 */

	public int size( );

	/**
	 *  @return  true if the stack is empty false otherwise
	 */

	public boolean isEmpty( );

	/**
	 *
	 * @return the element on the top of the stack
	 * @throws EmptyStackException if stack is empty
	 */

	public E peek( ) throws EmptyStackException;

	/**
	 *
	 * @param element to insert on the top of the stack (FIFO)
	 * @throws FullStackException if the stack is already reach its maximum capacity
	 */
	public void push(E element) throws FullStackException;
	/**
	 *
	 * @return the element on the top of the stack
	 * @throws EmptyStackException if stack is empty
	 */
	public E pop()   	   	
		throws EmptyStackException;  
        

	Iterator<E> iteratorReversed();
}

