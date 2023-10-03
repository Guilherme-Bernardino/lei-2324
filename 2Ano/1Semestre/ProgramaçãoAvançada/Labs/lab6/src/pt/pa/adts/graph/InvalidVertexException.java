/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.pa.adts.graph;

/**
 *
 * @author PM-Uninova
 */
public class InvalidVertexException extends RuntimeException {

    /**
     * Creates a new instance of
     * <code>InvalidEdgeException</code> without detail message.
     */
    public InvalidVertexException(String msg) {
    super(msg);
    }

    public InvalidVertexException() {
        super("Invalid Vertex Exception");
    }

    /**
     * Constructs an instance of
     * <code>InvalidEdgeException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
   
}
