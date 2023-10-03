/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa.adts.stack;

/**
 *
 * @author PM-Uninova
 */
public class EmptyStackException extends RuntimeException {

    public EmptyStackException(String message) {
        super(message);
    }
    
    public EmptyStackException() {
        super("Stack is Empty");
    }
}
