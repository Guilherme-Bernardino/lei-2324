package pt.pa.adts;

/**
 * An exception that signals that a queue is full.
 *
 * The implementation may impose a capacity restriction or
 * there is simply no more memory for more elements.
 */
public class QueueFullException extends RuntimeException {

    public QueueFullException() {
        super("The queue is full.");
    }

    public QueueFullException(String message) {
        super(message);
    }
}
