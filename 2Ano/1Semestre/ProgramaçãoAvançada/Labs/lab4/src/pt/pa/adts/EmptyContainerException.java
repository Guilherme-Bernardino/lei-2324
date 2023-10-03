/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.pa.adts;

/**
 *
 * @author brunomnsilva
 */
public class EmptyContainerException extends RuntimeException {

    public EmptyContainerException() {
        super("The container is empty.");
    }

    public EmptyContainerException(String string) {
        super(string);
    }
    
}
