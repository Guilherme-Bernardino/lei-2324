/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa.adts.stack;

/**
 *
 * @author PM-Uninova
 */
public class FullStackException extends RuntimeException {

    public FullStackException(String message) {
        super(message);
    }
    
     public FullStackException() {
        super("Stack is Full");
    }
}
