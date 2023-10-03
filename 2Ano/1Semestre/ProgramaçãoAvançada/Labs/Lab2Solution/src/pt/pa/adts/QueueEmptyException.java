package pt.pa.adts;

/**
 * An exception that signals that a queue is empty.
 *
 * This exception should be thrown whenever an access is attempted on an empty queue.
 */
public class QueueEmptyException extends RuntimeException {

    public QueueEmptyException() {
        super("The queue is empty.");
    }

    public QueueEmptyException(String message) {
        super(message);
    }
}