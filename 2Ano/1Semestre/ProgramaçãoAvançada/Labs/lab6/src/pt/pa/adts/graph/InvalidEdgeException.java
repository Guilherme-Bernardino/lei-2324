/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.pa.adts.graph;

/**
 *
 * @author PM-Uninova
 */
public class InvalidEdgeException extends RuntimeException {
    public InvalidEdgeException() {
        super("Invalid Edge Exception");
    }

    /**
     * Creates a new instance of
     * <code>InvalidEdgeException</code> without detail message.
     */
    public InvalidEdgeException(String msg) {
    super(msg);
    }

    /**
     * Constructs an instance of
     * <code>InvalidEdgeException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
   
}
