package com.brunomnsilva;

/**
 *
 * @author brunomnsilva
 */
public class QueueLinkedList<E> implements Queue<E> {

    private final ListNode header, trailer;
    private int size;

    public QueueLinkedList() {
        this.trailer = new ListNode(null, null, null); //header not instantiated yet!

        this.header = new ListNode(null, null, this.trailer);

        this.trailer.prev = this.header;
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }


    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }

    @Override
    public void clear() {
        /* return to empty state */
        this.header.next = this.trailer;
        this.trailer.prev = this.header;
        this.size = 0;
    }

    @Override
    public void enqueue(E elem) throws QueueFullException {
        try {
            ListNode prevNode = this.trailer.prev;
            ListNode newNode = new ListNode(elem, prevNode, this.trailer);
            this.trailer.prev=newNode;
            prevNode.next=newNode;
            this.size++;
        } catch (OutOfMemoryError e) {
            throw new QueueFullException();
        }
    }

    @Override
    public E dequeue() throws QueueEmptyException {
        if( size==0) throw new QueueEmptyException();
        ListNode removeNode= this.header.next;
        removeNode.next.prev=header;
        header.next=removeNode.next;
        this.size--;
        return removeNode.elem;
    }

    @Override
    public E front() throws QueueEmptyException {
        if( size==0) throw new QueueEmptyException();

        return this.header.next.elem;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("QueueLinkedList{size=" + this.size() + ", front|[");

        ListNode node = this.header.next;

        while(node != this.trailer) {
            sb.append( node.elem );
            if(node != this.trailer.prev) { //don't place last comma
                sb.append( "," );
            }
            node = node.next;
        }

        sb.append("]|end}");
        return sb.toString();
    }

    private class ListNode {
        E elem;
        ListNode next, prev;

        public ListNode(E elem, ListNode prev, ListNode next) {
            this.elem = elem;
            this.next = next;
            this.prev = prev;
        }

    }
}
