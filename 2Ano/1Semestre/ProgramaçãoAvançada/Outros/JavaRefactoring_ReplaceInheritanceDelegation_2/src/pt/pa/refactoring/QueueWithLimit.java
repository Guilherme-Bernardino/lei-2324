package pt.pa.refactoring;

import java.util.ArrayList;

public class QueueWithLimit<T>  implements Queue<T> {

    private static final int MAX_CAPACITY = 10;

    private ArrayList<T> adaptee = new ArrayList<>();

    @Override
    public void enqueue(T elem) throws QueueFullException, NullPointerException {
        if(elem == null) throw new NullPointerException("Null not allowed.");

        if(size() >= MAX_CAPACITY) throw new QueueFullException("Queue reached its limit (is full).");

        adaptee.add(elem);
    }

    @Override
    public T dequeue() throws QueueEmptyException {
        if(isEmpty()) throw new QueueEmptyException();

        return adaptee.remove(0);
    }

    @Override
    public T front() throws QueueEmptyException {
        if(isEmpty()) throw new QueueEmptyException();

        return adaptee.get(0);
    }

    @Override
    public int size() {
        return adaptee.size();
    }

    @Override
    public boolean isEmpty() {
        return adaptee.isEmpty();
    }

    @Override
    public void clear() {
        adaptee.clear();
    }
}
