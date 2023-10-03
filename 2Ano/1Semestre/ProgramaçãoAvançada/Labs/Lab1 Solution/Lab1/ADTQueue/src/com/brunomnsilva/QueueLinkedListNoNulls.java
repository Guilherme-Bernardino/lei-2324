package com.brunomnsilva;

public class QueueLinkedListNoNulls<T> extends QueueLinkedList<T> {

    @Override
    public void enqueue(T elem) throws QueueFullException {
        if(elem == null) throw new NullNotAllowedException();

        super.enqueue(elem);
    }
}
