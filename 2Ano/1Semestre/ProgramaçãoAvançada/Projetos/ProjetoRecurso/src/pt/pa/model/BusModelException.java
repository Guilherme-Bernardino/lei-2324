package pt.pa.model;

/**
 * Exception regarding BusModel runtime errors and exceptions.
 *
 * @author Guilherme B. 202001870
 */
public class BusModelException extends RuntimeException{

    public BusModelException(){}

    public BusModelException(String message) {
        super(message);
    }
}
