package pt.pa.adts;

/**
 * Specifies the behavior of a queue.
 *
 * A queue is a container which provides a first-in-first-out access model.
 *
 * @param <T> type of elements stored in the queue.
 */
public interface Queue<T> {

    /**
     * Stores a new element in the rear of the queue.
     *
     * @param elem element to store
     *
     * @throws QueueFullException thrown is there is no more capacity or memory for this element.
     * @throws NullPointerException thrown if <i>elem</i> is <b>null</b> and the implementation does not allow nulls.
     */
    public void enqueue(T elem) throws QueueFullException, NullPointerException;

    /**
     * Removes the element at the front of the queue.
     *
     * @return element at the front
     *
     * @throws QueueEmptyException thrown is the queue is empty.
     */
    public T dequeue() throws QueueEmptyException;

    /**
     * Returns a reference to the element at the front of the queue.
     *
     * @return element at the front
     *
     * @throws QueueEmptyException thrown is the queue is empty.
     */
    public T front() throws QueueEmptyException;

    /**
     * The number of elements stored in the queue.
     *
     * @return element count.
     */
    public int size();

    /**
     * Whether the queue is empty or not.
     *
     * @return <i>true</i> if que queue is empty; <i>false</i> otherwise.
     */
    public boolean isEmpty();

    /**
     * Clears the contents of the queue, returning to an empty state.
     */
    public void clear();
}
